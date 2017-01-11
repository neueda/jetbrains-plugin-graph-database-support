package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;

import javax.swing.*;
import java.util.Optional;

public interface TreeNodeModelApi {

    Optional<ContextMenu> getContextMenu();

    NodeType getType();

    Optional<Icon> getIcon();

    Optional<String> getValue();

    DataSourceApi getDataSourceApi();
}
