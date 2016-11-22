package com.neueda.jetbrains.plugin.graphdb.test.database.neo4j.common;

public interface Neo4jServer {

    void start();

    String getBoltHost();

    String getBoltPort();
}
