package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.DataSourceContextMenu;

import javax.swing.*;
import java.util.Optional;

public class DataSourceTreeNodeModel implements TreeNodeModelApi {

    private DataSourceApi dataSourceApi;
    private DataSourceContextMenu dataSourceContextMenu;

    public DataSourceTreeNodeModel(DataSourceApi dataSourceApi) {
        this.dataSourceApi = dataSourceApi;
        this.dataSourceContextMenu = new DataSourceContextMenu(dataSourceApi);
    }

    public Optional<ContextMenu> getContextMenu() {
        return Optional.of(dataSourceContextMenu);
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
    public Optional<Object> getValue() {
        return Optional.empty();
    }

    @Override
    public DataSourceApi getDataSourceApi() {
        return dataSourceApi;
    }
}
