package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.NodeType;

public class ContextMenu {

    private NodeType metadataType;
    private String datasourceUuid;
    private String data;

    public ContextMenu(NodeType metadataType, String datasourceUuid, String data) {
        this.metadataType = metadataType;
        this.datasourceUuid = datasourceUuid;
        this.data = data;
    }

    public NodeType getMetadataType() {
        return metadataType;
    }

    public String getDatasourceUuid() {
        return datasourceUuid;
    }

    public String getData() {
        return data;
    }

}
