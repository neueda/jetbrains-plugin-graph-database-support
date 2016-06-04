package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.DataSourcesToolWindow;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.dialog.CreateDataSourceDialog;

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
        CreateDataSourceDialog dataSourceDialog = createDataSourceDialog();
        if (dataSourceDialog.showAndGet()) {
            createDataSource(dataSourceDialog);
        }
    }

    private CreateDataSourceDialog createDataSourceDialog() {
        return new CreateDataSourceDialog(project);
    }

    private void createDataSource(CreateDataSourceDialog dataSourceDialog) {
        PatchedDefaultMutableTreeNode newChild = new PatchedDefaultMutableTreeNode("Neo4j data source" + System.currentTimeMillis());
        window.getTreeRoot().add(newChild);
        window.getTreeModel().reload();
    }
}

