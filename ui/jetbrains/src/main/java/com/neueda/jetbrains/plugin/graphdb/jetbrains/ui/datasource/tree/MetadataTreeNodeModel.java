package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.MetadataContextMenu;

import javax.swing.*;
import java.util.Optional;

public class MetadataTreeNodeModel implements TreeNodeModelApi {

    private MetadataContextMenu metadataContextMenu;
    private NodeType type;
    private Icon icon;
    private String value;
    private DataSourceApi dataSourceApi;

    public MetadataTreeNodeModel(Neo4jTreeNodeType type, DataSourceApi dataSourceApi, String value) {
        this(type, dataSourceApi, value, null);
    }

    public MetadataTreeNodeModel(Neo4jTreeNodeType type, DataSourceApi dataSourceApi, String value, Icon icon) {
        this.type = type;
        this.value = value;
        this.dataSourceApi = dataSourceApi;
        this.icon = icon;
        prepareContextMenu();
    }

    private void prepareContextMenu() {
        if (type == Neo4jTreeNodeType.LABEL
            || type == Neo4jTreeNodeType.RELATIONSHIP
            || type == Neo4jTreeNodeType.PROPERTY_KEY) {
                metadataContextMenu = new MetadataContextMenu(type, getDataSourceApi(), value);
        }
    }

    public Optional<ContextMenu> getContextMenu() {
        return Optional.ofNullable(metadataContextMenu);
    }

    @Override
    public NodeType getType() {
        return type;
    }

    public void setType(Neo4jTreeNodeType type) {
        this.type = type;
    }

    @Override
    public Optional<Icon> getIcon() {
        return Optional.ofNullable(icon);
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public Optional<String> getText() {
        return Optional.ofNullable(value);
    }

    @Override
    public Optional<String> getValue() {
        return Optional.ofNullable(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public DataSourceApi getDataSourceApi() {
        return dataSourceApi;
    }
}
