package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.model;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jEntityViewNodeType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.NodeType;

import java.util.Optional;

public class PropertiesModel extends RootObjectAwareModel {

    private NodeType type = Neo4jEntityViewNodeType.NODE_PROPERIES;
    private String text = "properties";
    private String description = "map";

    public PropertiesModel(DataSourceApi dataSourceApi, Object rootObject) {
        super(dataSourceApi, rootObject);
    }

    @Override
    public NodeType getType() {
        return type;
    }

    @Override
    public Optional<String> getText() {
        return Optional.of(text);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of(description);
    }

    @Override
    public String toString() {
        return text;
    }
}
