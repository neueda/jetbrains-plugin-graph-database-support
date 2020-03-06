package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphMetadata;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.query.Neo4jBoltQueryResult;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.ClientException;

import java.nio.channels.UnresolvedAddressException;
import java.util.Collections;
import java.util.Map;

/**
 * Communicates with Neo4j 3.0+ database using Bolt driver.
 */
public class Neo4jBoltDatabase implements GraphDatabaseApi {

    private final String url;
    private final AuthToken auth;

    public Neo4jBoltDatabase(Map<String, String> configuration) {
        this(new Neo4jBoltConfiguration(configuration));
    }

    public Neo4jBoltDatabase(Neo4jBoltConfiguration configuration) {
        String host = configuration.getHost();
        Integer port = configuration.getPort();
        String username = configuration.getUser();
        String password = configuration.getPassword();
        if (host.startsWith("bolt://") || host.startsWith("bolt+routing://")) {
            this.url = String.format("%s:%s", host, port);
        } else {
            this.url = String.format("bolt://%s:%s", host, port);
        }
        if (username != null && password != null) {
            auth = AuthTokens.basic(username, password);
        } else {
            auth = AuthTokens.none();
        }
    }

    @Override
    public GraphQueryResult execute(String query) {
        return execute(query, Collections.emptyMap());
    }

    @Override
    public GraphQueryResult execute(String query, Map<String, Object> statementParameters) {
        try {
            Driver driver = GraphDatabase.driver(url, auth);
            try {
                try (Session session = driver.session()) {

                    Neo4jBoltBuffer buffer = new Neo4jBoltBuffer();

                    long startTime = System.currentTimeMillis();
                    Result statementResult = session.run(query, statementParameters);
                    buffer.addColumns(statementResult.keys());

                    for (Record record : statementResult.list()) {
                        // Add row
                        buffer.addRow(record.asMap());
                    }
                    buffer.addResultSummary(statementResult.consume());
                    long endTime = System.currentTimeMillis();

                    return new Neo4jBoltQueryResult(endTime - startTime, buffer);
                }
            } finally {
                driver.closeAsync();
            }
        } catch (UnresolvedAddressException e) {
            throw new ClientException(e.getMessage());
        }
    }

    @Override
    public GraphMetadata metadata() {
        throw new IllegalStateException("Not implemented");
    }
}
