package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.google.common.collect.ImmutableMap;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.QueryExecutionService;

import javax.swing.*;

public class NodeDeleteAction extends AnAction {

    private DataSourceApi dataSource;
    private GraphNode node;

    NodeDeleteAction(String title, String description, Icon icon, DataSourceApi dataSource, GraphNode node) {
        super(title, description, icon);
        this.dataSource = dataSource;
        this.node = node;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        if (project != null) {
            QueryExecutionService service = new QueryExecutionService(project.getMessageBus());

            service.executeQuery(dataSource, new ExecuteQueryPayload("MATCH (n) WHERE ID(n) = $id DETACH DELETE n",
                    ImmutableMap.of("id", Long.parseLong(node.getId())),
                    null));
        }
    }

}
