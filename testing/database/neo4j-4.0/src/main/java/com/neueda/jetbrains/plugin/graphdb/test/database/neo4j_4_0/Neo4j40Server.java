package com.neueda.jetbrains.plugin.graphdb.test.database.neo4j_4_0;

import com.neueda.jetbrains.plugin.graphdb.test.database.neo4j.common.Neo4jServer;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.internal.InProcessNeo4jBuilder;

@SuppressWarnings("Duplicates")
public class Neo4j40Server implements Neo4jServer {

    private Neo4j serverControls;

    @Override
    public void start() {
        if (serverControls == null) {
            serverControls = new InProcessNeo4jBuilder()
                    .withFunction(Neo4jTestUserFunction.class)
                    .build();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    serverControls.close();
                } catch (Exception ignored) {
                }
            }));
        }
    }

    @Override
    public String getBoltHost() {
        return serverControls.boltURI().getHost();
    }

    @Override
    public String getBoltPort() {
        return String.valueOf(serverControls.boltURI().getPort());
    }
}
