package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.DataSourcesToolWindow;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.dialog.CreateDataSourceDialog;

import javax.swing.tree.DefaultTreeModel;

public class DataSourceInteractions {

    private final DataSourcesToolWindow window;
    private final ToolbarDecorator decorator;
    private final PatchedDefaultMutableTreeNode treeRoot;
    private final DefaultTreeModel treeModel;
    private final DataSourcesComponent component;
    private final Project project;

    public DataSourceInteractions(Project project, DataSourcesToolWindow window) {
        this.project = project;
        this.window = window;
        this.decorator = window.getDecorator();
        this.treeRoot = window.getTreeRoot();
        this.treeModel = window.getTreeModel();
        this.component = window.getComponent();

        decorator.setAddAction(anActionButton -> {
            CreateDataSourceDialog dataSourceDialog = createDataSourceDialog();
            dataSourceDialog.show();

            switch(dataSourceDialog.getExitCode()) {
                case DialogWrapper.OK_EXIT_CODE:
                    createDataSource(dataSourceDialog);
                    break;
                default:
                    // User canceled data source creation.
                    break;
            }
        });
        decorator.setRemoveAction(anActionButton -> {
        });
    }

    private void createDataSource(CreateDataSourceDialog dataSourceDialog) {
        PatchedDefaultMutableTreeNode newChild = new PatchedDefaultMutableTreeNode("Neo4j data source" + System.currentTimeMillis());
        treeRoot.add(newChild);
        treeModel.reload();
    }

    private CreateDataSourceDialog createDataSourceDialog() {
        return new CreateDataSourceDialog(project);
    }
}
