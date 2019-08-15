package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.model;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.NoIdGraphEntity;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jEntityViewNodeType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.NodeType;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.util.Optional;

public class ObjectModel extends RootObjectAwareModel implements NoIdGraphEntity {

    private NodeType type = Neo4jEntityViewNodeType.OTHER;
    private Object object;
    private String text;
    private String description;

    public ObjectModel(Object object, String text, String description, DataSourceApi dataSourceApi, Object rootObject) {
        super(dataSourceApi, rootObject);
        this.object = object;
        this.text = text;
        this.description = description;
    }

    @Override
    public NodeType getType() {
        return type;
    }

    @Override
    public Optional<Icon> getIcon() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getText() {
        return Optional.ofNullable(text);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @Override
    public Optional<Object> getValue() {
        return Optional.ofNullable(object);
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean isTypesSingle() {
        return true;
    }

    @Override
    public String getRepresentation() {
        return StringUtils.capitalize(getText().orElse(getType().toString()));
    }

}
