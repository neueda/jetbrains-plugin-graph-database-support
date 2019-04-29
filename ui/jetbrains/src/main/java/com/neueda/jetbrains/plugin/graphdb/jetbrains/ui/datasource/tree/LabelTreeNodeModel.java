package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;

import java.util.Optional;

public class LabelTreeNodeModel extends MetadataTreeNodeModel {

    private static final String NAME_WITH_COUNT = "%s (%d)";
    private Long count;

    public LabelTreeNodeModel(Neo4jTreeNodeType type, DataSourceApi dataSourceApi, String value, Long count) {
        super(type, dataSourceApi, value);
        this.count = count;
    }

    @Override
    public Optional<String> getText() {
        return Optional.of(String.format(NAME_WITH_COUNT, getValue().orElse(""), count));
    }
}
