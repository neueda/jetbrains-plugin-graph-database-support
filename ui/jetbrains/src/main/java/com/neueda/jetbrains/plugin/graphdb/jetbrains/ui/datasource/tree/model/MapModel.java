package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.model;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jEntityViewNodeType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.NodeType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeNodeModelApi;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MapModel implements TreeNodeModelApi {

    private NodeType type = Neo4jEntityViewNodeType.NODE_MAP;
    private String text;
    private String description = "map";
    private DataSourceApi dataSourceApi;

    public MapModel(String text, DataSourceApi dataSourceApi) {
        this.text = text;
        this.dataSourceApi = dataSourceApi;
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

    @Nullable
    @Override
    public DataSourceApi getDataSourceApi() {
        return dataSourceApi;
    }

    @Override
    public String toString() {
        return text;
    }
}
