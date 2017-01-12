package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.actions;

import javax.swing.*;

public class MetadataLabelAction extends MetadataAction {

    private static final String QUERY = "MATCH (n:%s) RETURN n LIMIT 25";

    MetadataLabelAction(String data, String dataSourceUuid, String title, String description, Icon icon) {
        super(data, dataSourceUuid, title, description, icon);
    }

    @Override
    protected String getQuery(String data) {
        return String.format(QUERY, data);
    }
}
