package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.List;

public interface GraphPath {

    /**
     * Return nodes and relationships.
     */
    List<Object> getComponents();
}
