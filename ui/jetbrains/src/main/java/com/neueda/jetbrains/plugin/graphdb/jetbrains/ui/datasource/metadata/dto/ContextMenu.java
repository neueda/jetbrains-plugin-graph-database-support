package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto;

import com.intellij.openapi.actionSystem.DataContext;

public interface ContextMenu {
    void showPopup(DataContext dataContext);
}
