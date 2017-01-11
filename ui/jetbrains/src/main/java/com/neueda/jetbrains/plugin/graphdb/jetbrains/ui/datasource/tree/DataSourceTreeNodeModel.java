package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;

import javax.swing.*;
import java.util.Optional;

public class DataSourceTreeNodeModel implements TreeNodeModelApi {

    private DataSourceApi dataSourceApi;

    public DataSourceTreeNodeModel(DataSourceApi dataSourceApi) {
        this.dataSourceApi = dataSourceApi;
    }

    @Override
    public Optional<ContextMenu> getContextMenu() {
        return Optional.of(new ContextMenu(getType(), dataSourceApi));
    }

    @Override
    public NodeType getType() {
        return Neo4jTreeNodeType.DATASOURCE;
    }

    @Override
    public Optional<Icon> getIcon() {
        return Optional.ofNullable(dataSourceApi.getDescription().getIcon());
    }

    @Override
    public Optional<String> getText() {
        return Optional.ofNullable(dataSourceApi.getName());
    }

    @Override
    public DataSourceApi getDataSourceApi() {
        return dataSourceApi;
    }
}
