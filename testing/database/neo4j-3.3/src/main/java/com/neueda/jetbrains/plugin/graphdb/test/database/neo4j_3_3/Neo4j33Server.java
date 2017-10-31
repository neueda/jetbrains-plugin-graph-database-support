package com.neueda.jetbrains.plugin.graphdb.test.database.neo4j_3_3;

import com.neueda.jetbrains.plugin.graphdb.test.database.neo4j.common.Neo4jServer;
import org.neo4j.harness.ServerControls;
import org.neo4j.harness.internal.InProcessServerBuilder;

@SuppressWarnings("Duplicates")
public class Neo4j33Server implements Neo4jServer {

    private ServerControls serverControls;

    @Override
    public void start() {
        if (serverControls == null) {
            serverControls = new InProcessServerBuilder()
                    .withFunction(Neo4jTestUserFunction.class)
                    .newServer();
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
