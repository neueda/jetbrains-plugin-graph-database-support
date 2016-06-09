package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPropertyContainer;

import java.util.Map;

public class Neo4jBoltPropertyContainer implements GraphPropertyContainer {

    private final Map<String, Object> properties;

    public Neo4jBoltPropertyContainer(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }
}
