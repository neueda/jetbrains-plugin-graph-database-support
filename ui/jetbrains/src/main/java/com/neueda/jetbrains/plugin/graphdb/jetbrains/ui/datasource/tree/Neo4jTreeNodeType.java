package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

import javax.swing.*;
import java.util.Optional;

public enum Neo4jTreeNodeType implements NodeType {
    ROOT,
    DATASOURCE(GraphIcons.Database.NEO4J),
    LABELS(GraphIcons.Nodes.LABEL),
    LABEL,
    RELATIONSHIPS(GraphIcons.Nodes.RELATIONSHIP_TYPE),
    RELATIONSHIP,
    PROPERTY_KEYS(GraphIcons.Nodes.PROPERTY_KEY),
    PROPERTY_KEY,
    STORED_PROCEDURES(GraphIcons.Nodes.STORED_PROCEDURE),
    STORED_PROCEDURE,
    USER_FUNCTIONS(GraphIcons.Nodes.USER_FUNCTION),
    USER_FUNCTION;

    private Icon icon;

    Neo4jTreeNodeType() {
    }

    Neo4jTreeNodeType(Icon icon) {
        this.icon = icon;
    }

    @Override
    public Optional<Icon> getIcon() {
        return Optional.ofNullable(icon);
    }
}
