package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.List;

public interface GraphNode extends GraphEntity {

    List<String> getTypes();

    default String getRepresentation() {
        return "Node[" + getId() + "]";
    }
}
