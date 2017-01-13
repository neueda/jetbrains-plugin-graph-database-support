package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.query.Neo4jBoltQueryResult;

import org.neo4j.driver.v1.*;

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
        this.url = String.format("bolt://%s:%s", host, port);
        if (username != null && password != null) {
            auth = AuthTokens.basic(username, password);
        } else {
            auth = AuthTokens.none();
        }
    }

    @Override
    public GraphQueryResult execute(String query) {
        return execute(query, null);
    }

    @Override
    public GraphQueryResult execute(String query, Map<String, Object> statementParameters) {
        try (Driver driver = GraphDatabase.driver(url, auth);
            Session session = driver.session()) {

            Neo4jBoltBuffer buffer = new Neo4jBoltBuffer();

            long startTime = System.currentTimeMillis();
            StatementResult statementResult;
            if (statementParameters == null) {
                statementResult = session.run(query);
            } else {
                statementResult = session.run(query, statementParameters);
            }
            buffer.addColumns(statementResult.keys());

            for (Record record : statementResult.list()) {
                // Add row
                buffer.addRow(record.asMap());
            }
            buffer.addResultSummary(statementResult.consume());
            long endTime = System.currentTimeMillis();

            return new Neo4jBoltQueryResult(endTime - startTime, buffer);
        }
    }
}
