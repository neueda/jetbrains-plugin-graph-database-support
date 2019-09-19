package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphMetadata;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltBuffer;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.query.OpenCypherGremlinQueryResult;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.exceptions.ClientException;
import org.opencypher.gremlin.neo4j.driver.GremlinServerDriverWithClient;

import java.net.URI;
import java.nio.channels.UnresolvedAddressException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Communicates with TinkerPop database by translating Cypher to Gremlin
 */
public class OpenCypherGremlinDatabase implements GraphDatabaseApi {

    private final String url;
    private final Driver driver;
    private final Client client;

    public OpenCypherGremlinDatabase(Map<String, String> configuration) {
        this(new OpenCypherGremlinConfiguration(configuration));
    }

    public OpenCypherGremlinDatabase(OpenCypherGremlinConfiguration configuration) {
        String host = configuration.getHost();
        Integer port = configuration.getPort();
        String username = configuration.getUser();
        String password = configuration.getPassword();

        this.url = String.format("gremlin://%s:%s", host, port);
        URI uri = URI.create(this.url);

        Cluster cluster = Cluster.build()
                .addContactPoint(uri.getHost())
                .credentials(username, password)
                .serializer(configuration.getSerializer())
                .enableSsl(configuration.getSSL())
                .port(uri.getPort())
                .create();

        client = cluster.connect();
        GremlinFlavor flavor = configuration.getFlavor();
        driver = new GremlinServerDriverWithClient(cluster, flavor::client, false);
    }

    @Override
    public GraphQueryResult execute(String query) {
        return execute(query, Collections.emptyMap());
    }

    @Override
    public GraphQueryResult execute(String query, Map<String, Object> statementParameters) {
        try {
            if (query.toUpperCase().startsWith("PROFILE")) {
                return notSupported();
            }

            try (Session session = driver.session()) {
                Neo4jBoltBuffer buffer = new Neo4jBoltBuffer();

                long startTime = System.currentTimeMillis();
                StatementResult statementResult = session.run(query, statementParameters);

                buffer.addColumns(getKeysWorkaround(statementResult));

                for (Record record : statementResult.list()) {
                    buffer.addRow(record.asMap());
                }
                buffer.addResultSummary(statementResult.consume());
                long endTime = System.currentTimeMillis();

                return new OpenCypherGremlinQueryResult(endTime - startTime, buffer);
            }
        } catch (UnresolvedAddressException e) {
            throw new ClientException(e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public GraphMetadata metadata() {
        try {
            List<Result> labels = client.submit("g.V().label().groupCount().unfold()").all().get();
            Map<String, Number> labelResult = labels.stream()
                    .map(r -> r.get(Map.class))
                    .map(r -> (Map<String, Number>) r)
                    .flatMap(m -> m.entrySet().stream())
                    .collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

            List<Result> rels = client.submit("g.E().label().groupCount().unfold()").all().get();
            Map<String, Number> relResult = rels.stream()
                    .map(r -> r.get(Map.class))
                    .map(r -> (Map<String, Number>) r)
                    .flatMap(m -> m.entrySet().stream())
                    .collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

            List<Result> vertexProp = client.submit("g.V().properties().key().dedup()").all().get();

            List<String> vertexPropResult = vertexProp.stream()
                    .map(Result::getString)
                    .collect(toList());

            List<Result> edgeProp = client.submit("g.E().properties().key().dedup()").all().get();

            List<String> edgePropResult = edgeProp.stream()
                    .map(Result::getString)
                    .collect(toList());

            return new OpenCypherGremlinGraphMetadata(labelResult, relResult, vertexPropResult, edgePropResult);
        } catch (Exception e) {
            return GraphMetadata.EMPTY;
        }
    }

    private GraphQueryResult notSupported() {
        String resultColumn = "result";
        Neo4jBoltBuffer buffer = new Neo4jBoltBuffer();
        buffer.addColumns(Collections.singletonList(resultColumn));
        buffer.addRow(Collections.singletonMap(resultColumn, "Currently no supported."));
        return new OpenCypherGremlinQueryResult(0, buffer);
    }

    private List<String> getKeysWorkaround(StatementResult statementResult) {
        try {
            return statementResult.keys();
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }
}
