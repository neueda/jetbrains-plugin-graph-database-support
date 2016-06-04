package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions.action.neo4j.bolt;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.DataSourcesToolWindow;

public class NewNeo4jDataSourceAction extends AnAction {

    private final Project project;
    private final DataSourcesToolWindow window;

    public NewNeo4jDataSourceAction(Project project, DataSourcesToolWindow window) {
        super("Neo4j - Bolt", "Create Neo4j 3.0+ Bolt data source", GraphIcons.Database.NEO4J);
        this.project = project;
        this.window = window;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Neo4jBoltDataSourceDialog dataSourceDialog = createDataSourceDialog();
        if (dataSourceDialog.showAndGet()) {
            window.createDataSource(dataSourceDialog.constructDataSource());
        }
    }

    private Neo4jBoltDataSourceDialog createDataSourceDialog() {
        return new Neo4jBoltDataSourceDialog(project, window);
    }
}

