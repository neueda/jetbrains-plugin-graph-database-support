package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.bootstrap;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerAdapter;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import org.jetbrains.annotations.NotNull;

public class GraphDatabasePluginBootstrapImpl implements GraphDatabasePluginBootstrap {

    @Override
    public void initComponent() {
        ProjectManager.getInstance()
                .addProjectManagerListener(new ProjectManagerAdapter() {
                    @Override
                    public void projectOpened(Project project) {
                        project.getComponent(DataSourcesComponent.class).bootstrap(project);
                    }
                });
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "GraphDatabaseSupport.Bootstrap";
    }
}
