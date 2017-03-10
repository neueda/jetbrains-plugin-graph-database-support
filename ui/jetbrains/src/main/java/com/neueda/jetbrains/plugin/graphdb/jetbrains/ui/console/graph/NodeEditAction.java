package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DiffService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.EditEntityDialog;

import javax.swing.*;

public class NodeEditAction extends AnAction {

    private DataSourceApi dataSource;
    private GraphNode node;

    NodeEditAction(String title, String description, Icon icon, DataSourceApi dataSource, GraphNode node) {
        super(title, description, icon);
        this.dataSource = dataSource;
        this.node = node;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        EditEntityDialog dialog = new EditEntityDialog(project, node);
        if (dialog.showAndGet()) {
            DiffService diffService = new DiffService(project);
            diffService.updateNode(dataSource, node, dialog.getUpdatedEntity());
        }
    }

}
