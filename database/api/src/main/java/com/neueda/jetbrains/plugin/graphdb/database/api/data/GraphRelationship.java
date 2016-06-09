package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.List;
import java.util.StringJoiner;

public interface GraphRelationship extends GraphEntity {

    boolean hasStartAndEndNode();

    String getStartNodeId();

    String getEndNodeId();

    GraphNode getStartNode();

    GraphNode getEndNode();

    List<String> getTypes();

    default String getRepresentation() {
        StringJoiner stringJoiner = new StringJoiner(":", ":", "");
        getTypes().forEach(stringJoiner::add);

        return "Relationship[" + getId() + "]" + stringJoiner.toString();
    }
}
