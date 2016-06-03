package com.neueda.jetbrains.plugin.graphdb.database.api;

public interface GraphRelationship extends GraphEntity {

    GraphNode getStart();

    GraphNode getEnd();

    default String getRepresentation() {
        return "Relationship[" + getId() + "]";
    }
}
