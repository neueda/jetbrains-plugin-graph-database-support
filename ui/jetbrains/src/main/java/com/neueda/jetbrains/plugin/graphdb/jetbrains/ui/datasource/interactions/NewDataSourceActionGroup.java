package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.neo4j.bolt.Neo4jBoltDataSourceDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NewDataSourceActionGroup extends ActionGroup {

    private final Project project;
    private final DataSourcesToolWindow window;

    public NewDataSourceActionGroup(Project project, DataSourcesToolWindow window) {
        this.project = project;
        this.window = window;
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent e) {
        return new AnAction[]{
                new NewDataSourceAction(window, new Neo4jBoltDataSourceDialog(project, window),
                        "Neo4j - Bolt", "Create Neo4j 3.0+ Bolt data source", GraphIcons.Database.NEO4J)
        };
    }
}
