package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import javax.swing.*;

public class MetadataLabelAction extends MetadataAction {

    private static final String LABEL_QUERY_START = "MATCH (n:";
    private static final String LABEL_QUERY_END = ") RETURN n LIMIT 25";

    public MetadataLabelAction(String data, String dataSourceUuid, String title, String description, Icon icon) {
        super(data, dataSourceUuid, title, description, icon);
    }

    @Override
    protected String getQuery(String data) {
        return LABEL_QUERY_START + data + LABEL_QUERY_END;
    }
}
