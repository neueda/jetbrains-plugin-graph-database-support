package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesView;

import javax.swing.Icon;

public class NewDataSourceAction extends AnAction {

    private final DataSourcesView window;
    private final DataSourceDialog dataSourceDialog;

    public NewDataSourceAction(DataSourcesView dataSourcesView, DataSourceDialog dataSourceDialog,
                               String title, String description, Icon icon) {
        super(title, description, icon);
        this.window = dataSourcesView;
        this.dataSourceDialog = dataSourceDialog;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        if (dataSourceDialog.go()) {
            window.createDataSource(dataSourceDialog.constructDataSource());
        }
    }
}

