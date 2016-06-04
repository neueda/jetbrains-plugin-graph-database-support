package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.DataSourcesToolWindow;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions.action.neo4j.bolt.NewNeo4jDataSourceAction;
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
                new NewNeo4jDataSourceAction(project, window)
        };
    }
}
