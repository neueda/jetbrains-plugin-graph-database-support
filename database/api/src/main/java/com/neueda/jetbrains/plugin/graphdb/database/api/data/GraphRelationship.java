package com.neueda.jetbrains.plugin.graphdb.database.api.data;

public interface GraphRelationship extends GraphEntity {

    boolean hasStartAndEndNode();

    String getStartNodeId();

    String getEndNodeId();

    GraphNode getStartNode();

    GraphNode getEndNode();

    default String getRepresentation() {
        return "Relationship[" + getId() + "]";
    }
}
