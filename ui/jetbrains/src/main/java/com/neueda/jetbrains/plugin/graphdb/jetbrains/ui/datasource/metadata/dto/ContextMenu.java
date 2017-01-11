package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.NodeType;

public class ContextMenu {

    private NodeType metadataType;
    private DataSourceApi dataSourceApi;
    private String data;

    public ContextMenu(NodeType metadataType, DataSourceApi dataSourceApi) {
        this.metadataType = metadataType;
        this.dataSourceApi = dataSourceApi;
    }

    public ContextMenu(NodeType metadataType, DataSourceApi dataSourceApi, String data) {
        this.metadataType = metadataType;
        this.dataSourceApi = dataSourceApi;
        this.data = data;
    }

    public NodeType getMetadataType() {
        return metadataType;
    }

    public DataSourceApi getDataSourceApi() {
        return dataSourceApi;
    }

    public String getData() {
        return data;
    }

}
