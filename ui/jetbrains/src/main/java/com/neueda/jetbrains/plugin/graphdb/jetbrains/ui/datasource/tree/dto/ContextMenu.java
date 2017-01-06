package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto;

public class ContextMenu {
    private String metadataType;
    private String datasourceUuid;
    private String data;

    public ContextMenu(String metadataType, String datasourceUuid, String data) {
        this.metadataType = metadataType;
        this.datasourceUuid = datasourceUuid;
        this.data = data;
    }

    public String getMetadataType() {
        return metadataType;
    }

    public String getDatasourceUuid() {
        return datasourceUuid;
    }

    public String getData() {
        return data;
    }

}
