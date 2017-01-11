package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

public enum Neo4jTreeNodeType implements NodeType {
    ROOT,
    DATASOURCE,
    LABELS,
    LABEL,
    RELATIONSHIPS,
    RELATIONSHIP,
    PROPERTY_KEYS,
    PROPERTY_KEY,
    STORED_PROCEDURES,
    STORED_PROCEDURE,
    USER_FUNCTIONS,
    USER_FUNCTION;

    Neo4jTreeNodeType() {
    }

}
