package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.List;

public interface GraphRelationship extends GraphEntity {

    boolean hasStartAndEndNode();

    String getStartNodeId();

    String getEndNodeId();

    GraphNode getStartNode();

    GraphNode getEndNode();

    List<String> getTypes();

    default String getRepresentation() {
        return "Relationship[" + getId() + "]";
    }
}
