package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.List;
import java.util.StringJoiner;

public interface GraphNode extends GraphEntity {

    List<String> getTypes();

    default String getRepresentation() {
        StringJoiner stringJoiner = new StringJoiner(":", ":", "");
        getTypes().forEach(stringJoiner::add);

        return "Node[" + getId() + "]" + stringJoiner.toString();
    }
}
