package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import javax.swing.*;

public class MetadataPropertyKeyAction extends MetadataAction {

    private static final String QUERY = "MATCH (n) WHERE EXISTS(n.%1$s) " +
            "RETURN DISTINCT \"node\" as element, n.%1$s AS %1$s LIMIT 25 " +
            "UNION ALL MATCH ()-[r]-() WHERE EXISTS(r.%1$s) " +
            "RETURN DISTINCT \"relationship\" AS element, r.%1$s AS %1$s LIMIT 25";

    public MetadataPropertyKeyAction(String data, String dataSourceUuid, String title, String description, Icon icon) {
        super(data, dataSourceUuid, title, description, icon);
    }

    @Override
    protected String getQuery(String data) {
        return String.format(QUERY, data);
    }
}
