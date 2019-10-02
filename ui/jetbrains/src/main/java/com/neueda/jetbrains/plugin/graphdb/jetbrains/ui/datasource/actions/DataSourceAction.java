package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.Analytics;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.FileUtil;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.Notifier;

import javax.swing.*;
import java.io.IOException;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.util.ExceptionWrapper.truncateString;

public class DataSourceAction extends AnAction {

    private DataSourceApi dataSource;

    DataSourceAction(String title, String description, Icon icon, DataSourceApi dataSource) {
        super(title, description, icon);
        this.dataSource = dataSource;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Analytics.event(dataSource, "openEditor");
        Project project = getEventProject(e);
        try {
            FileUtil.openFile(project, FileUtil.getDataSourceFile(project, dataSource));
        } catch (IOException exception) {
            Notifier.error("Open editor error", truncateString(exception.getMessage(), 120));
        }
    }
}
