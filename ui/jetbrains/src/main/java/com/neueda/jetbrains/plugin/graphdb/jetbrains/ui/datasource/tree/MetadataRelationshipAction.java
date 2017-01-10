package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import javax.swing.*;

public class MetadataRelationshipAction extends MetadataAction {
    private static final String REL_QUERY_START = "MATCH p=()-[r:";
    private static final String REL_QUERY_END = "]->() RETURN p LIMIT 25";

    public MetadataRelationshipAction(String data, String dataSourceUuid, String title, String description, Icon icon) {
        super(data, dataSourceUuid, title, description, icon);
    }

    @Override
    protected String getQuery(String data) {
        return REL_QUERY_START + data + REL_QUERY_END;
    }
}
