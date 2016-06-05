package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.DataSourcesToolWindow;

import javax.swing.Icon;

public class NewDataSourceAction extends AnAction {

    private final DataSourcesToolWindow window;
    private final DataSourceDialog dataSourceDialog;

    public NewDataSourceAction(DataSourcesToolWindow window, DataSourceDialog dataSourceDialog,
                               String title, String description, Icon icon) {
        super(title, description, icon);
        this.window = window;
        this.dataSourceDialog = dataSourceDialog;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        if (dataSourceDialog.go()) {
            window.createDataSource(dataSourceDialog.constructDataSource());
        }
    }
}

