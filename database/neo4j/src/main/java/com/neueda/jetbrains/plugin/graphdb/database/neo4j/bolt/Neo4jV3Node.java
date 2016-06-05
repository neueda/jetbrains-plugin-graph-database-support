package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import org.neo4j.driver.v1.types.Node;

import java.util.Map;

public class Neo4jV3Node implements GraphNode {

    private final long id;
    private final Map<String, Object> properties;

    public Neo4jV3Node(Node value) {
        this.id = value.id();
        this.properties = value.asMap();
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }
}
