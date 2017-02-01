package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Optional;

public interface TreeNodeModelApi {

    Optional<ContextMenu> getContextMenu();

    NodeType getType();

    Optional<Icon> getIcon();

    Optional<String> getText();

    Optional<String> getValue();

    @Nullable
    DataSourceApi getDataSourceApi();
}
