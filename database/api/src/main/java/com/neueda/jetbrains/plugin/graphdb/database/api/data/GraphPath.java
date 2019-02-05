package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.List;

public interface GraphPath extends NoIdGraphEntity {

    /**
     * Return nodes and relationships.
     */
    List<Object> getComponents();

    default boolean isTypesSingle() {
        return true;
    }

    default String getRepresentation() {
        return "Path";
    }
}
