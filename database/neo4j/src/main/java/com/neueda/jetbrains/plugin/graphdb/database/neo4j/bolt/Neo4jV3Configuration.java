package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt;

import java.util.Map;

public class Neo4jV3Configuration {

    public static final String HOST = "host";
    public static final String PORT = "port";
    public static final String USER = "user";
    public static final String PASSWORD = "password";

    private final Map<String, String> configuration;

    public Neo4jV3Configuration(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    public String getHost() {
        return configuration.get(HOST);
    }

    public Integer getPort() {
        return Integer.valueOf(configuration.getOrDefault(PORT, "7687"));
    }

    public String getUser() {
        return configuration.get(USER);
    }

    public String getPassword() {
        return configuration.get(PASSWORD);
    }
}
