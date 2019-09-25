package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphMetadata;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltQueryResultRow;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.query.OpenCypherGremlinQueryResult;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.neo4j.driver.v1.exceptions.ClientException;
import org.opencypher.gremlin.client.CypherGremlinClient;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;

/**
 * Communicates with TinkerPop database by translating Cypher to Gremlin
 */
public class OpenCypherGremlinDatabase implements GraphDatabaseApi {
    private final OpenCypherGremlinValueConverter converter;
    private final Cluster cluster;
    private final GremlinFlavor flavor;

    public OpenCypherGremlinDatabase(Map<String, String> configuration) {
        this(new OpenCypherGremlinConfiguration(configuration));
    }

    public OpenCypherGremlinDatabase(OpenCypherGremlinConfiguration configuration) {
        String host = configuration.getHost();
        Integer port = configuration.getPort();
        String username = configuration.getUser();
        String password = configuration.getPassword();

        String url = String.format("gremlin://%s:%s", host, port);
        URI uri = URI.create(url);

        cluster = Cluster.build()
                .addContactPoint(uri.getHost())
                .credentials(username, password)
                .serializer(configuration.getSerializer())
                .enableSsl(configuration.getSSL())
                .port(uri.getPort())
                .create();

        flavor = configuration.getFlavor();

        converter = new OpenCypherGremlinValueConverter();
    }

    @Override
    public GraphQueryResult execute(String query) {
        return execute(query, Collections.emptyMap());
    }

    @Override
    public GraphQueryResult execute(String query, Map<String, Object> statementParameters) {
        Client gremlinClient = cluster.connect();

        try (CypherGremlinClient client = flavor.client(gremlinClient)) {
            if (query.toUpperCase().startsWith("PROFILE")) {
                return notSupported();
            }

            HashMap<String, Object> serializableMap = new HashMap<>(statementParameters);
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result = client.submit(query, serializableMap).all();
            long endTime = System.currentTimeMillis();

            List<GraphQueryResultColumn> headers = getHeaders(result);

            List<GraphQueryResultRow> rows = result.stream()
                    .map(converter::toRecord)
                    .collect(toList());

            List<GraphNode> nodes = rows.stream().flatMap(e -> e.getNodes().stream()).distinct().collect(toList());
            List<GraphRelationship> relationships = rows.stream().flatMap(e -> e.getRelationships().stream()).distinct().collect(toList());

            return new OpenCypherGremlinQueryResult(endTime - startTime,
                    headers,
                    rows,
                    nodes,
                    relationships);
        } catch (RuntimeException e) {
            if (query.toUpperCase().startsWith("EXPLAIN")) {
                return new OpenCypherGremlinQueryResult(0, emptyList(), emptyList(), emptyList(), emptyList());
            } else {
                throw new ClientException(e.getMessage());
            }
        }
    }

    private List<GraphQueryResultColumn> getHeaders(List<Map<String, Object>> result) {
        if (result.isEmpty()) {
            return emptyList();
        } else {
            return result.get(0).keySet().stream()
                    .map(Neo4jBoltQueryResultColumn::new)
                    .collect(toList());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public GraphMetadata metadata() {
        Client gremlinClient = cluster.connect();
        try {
            List<Result> labels = gremlinClient.submit("g.V().label().groupCount()").all().get();
            Map<String, Number> labelResult = labels.stream()
                    .map(r -> r.get(Map.class))
                    .map(r -> (Map<String, Number>) r)
                    .flatMap(m -> m.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            List<Result> rels = gremlinClient.submit("g.E().label().groupCount()").all().get();
            Map<String, Number> relResult = rels.stream()
                    .map(r -> r.get(Map.class))
                    .map(r -> (Map<String, Number>) r)
                    .flatMap(m -> m.entrySet().stream())
                    .collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

            List<Result> vertexProp = gremlinClient.submit("g.V().properties().key().dedup()").all().get();

            List<String> vertexPropResult = vertexProp.stream()
                    .map(Result::getString)
                    .collect(toList());

            List<Result> edgeProp = gremlinClient.submit("g.E().properties().key().dedup()").all().get();

            List<String> edgePropResult = edgeProp.stream()
                    .map(Result::getString)
                    .collect(toList());

            return new OpenCypherGremlinGraphMetadata(labelResult, relResult, vertexPropResult, edgePropResult);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            gremlinClient.close();
        }
    }

    private GraphQueryResult notSupported() {
        String resultColumn = "result";
        return new OpenCypherGremlinQueryResult(
                0,
                singletonList(new Neo4jBoltQueryResultColumn(resultColumn)),
                singletonList(new Neo4jBoltQueryResultRow(singletonMap(resultColumn, "Currently no supported."))),
                emptyList(),
                emptyList()
        );
    }
}
