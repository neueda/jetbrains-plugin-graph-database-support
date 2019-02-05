package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.NoIdGraphEntity;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;

public class EntityContextMenu implements ContextMenu {

    private DataSourceApi dataSourceApi;
    private NoIdGraphEntity entity;

    public EntityContextMenu(DataSourceApi dataSourceApi, NoIdGraphEntity entity) {
        this.dataSourceApi = dataSourceApi;
        this.entity = entity;
    }

    @Override
    public void showPopup(DataContext dataContext) {
        ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                entity.getRepresentation(),
                new EntityActionGroup(dataSourceApi, entity),
                dataContext,
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                true
        );

        popup.showInBestPositionFor(dataContext);
    }
}
