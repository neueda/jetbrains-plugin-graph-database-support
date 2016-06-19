package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.AnActionButton;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesToolWindow;

public class RefreshDataSourcesAction extends AnActionButton{

    private final DataSourcesToolWindow dataSourcesToolWindow;

    public RefreshDataSourcesAction(DataSourcesToolWindow dataSourcesToolWindow) {
        super("Refresh", "Refresh all data sources", AllIcons.Actions.Refresh);
        this.dataSourcesToolWindow = dataSourcesToolWindow;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        dataSourcesToolWindow.refreshDataSourcesMetadata();
    }
}
