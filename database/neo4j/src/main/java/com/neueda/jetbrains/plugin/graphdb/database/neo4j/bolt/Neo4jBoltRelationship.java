package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;
import org.neo4j.driver.v1.types.Relationship;

import java.util.Map;

public class Neo4jBoltRelationship implements GraphRelationship {

    private final long id;
    private final GraphNode startNode;
    private final GraphNode endNode;
    private final Map<String, Object> properties;

    public Neo4jBoltRelationship(Relationship relationship,
                                 GraphNode startNode, GraphNode endNode) {
        this.id = relationship.id();
        this.startNode = startNode;
        this.endNode = endNode;
        this.properties = relationship.asMap();
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public GraphNode getStart() {
        return startNode;
    }

    @Override
    public GraphNode getEnd() {
        return endNode;
    }
}
