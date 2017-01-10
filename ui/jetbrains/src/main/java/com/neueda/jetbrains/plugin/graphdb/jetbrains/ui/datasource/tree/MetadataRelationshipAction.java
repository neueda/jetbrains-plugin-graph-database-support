package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import javax.swing.*;

public class MetadataRelationshipAction extends MetadataAction {

    private static final String QUERY = "MATCH p=()-[r:%s]->() RETURN p LIMIT 25";

    public MetadataRelationshipAction(String data, String dataSourceUuid, String title, String description, Icon icon) {
        super(data, dataSourceUuid, title, description, icon);
    }

    @Override
    protected String getQuery(String relationship) {
        return String.format(QUERY, relationship);
    }
}
