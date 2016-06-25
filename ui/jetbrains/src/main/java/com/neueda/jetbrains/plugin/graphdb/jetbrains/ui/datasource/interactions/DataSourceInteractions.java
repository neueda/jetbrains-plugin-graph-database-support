package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.neo4j.bolt.Neo4jBoltDataSourceDialog;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataSourceInteractions {

    private final DataSourcesView dataSourcesView;
    private final ToolbarDecorator decorator;
    private final Project project;
    private final Tree dataSourceTree;

    public DataSourceInteractions(Project project, DataSourcesView dataSourcesView) {
        this.project = project;
        this.dataSourcesView = dataSourcesView;
        this.decorator = dataSourcesView.getDecorator();
        this.dataSourceTree = dataSourcesView.getDataSourceTree();

        initAddAction();
        initRemoveAction();
        initEditAction();
    }

    private void initAddAction() {
        decorator.setAddAction(anActionButton -> {
            ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                    "New Data Source",
                    new NewDataSourceActionGroup(project, dataSourcesView),
                    anActionButton.getDataContext(),
                    JBPopupFactory.ActionSelectionAid.NUMBERING,
                    false
            );
            popup.show(anActionButton.getPreferredPopupPoint());
        });
    }

    private void initRemoveAction() {
        decorator.setRemoveAction(anActionButton -> {
            DefaultMutableTreeNode[] selectedNodes = dataSourceTree.getSelectedNodes(DefaultMutableTreeNode.class,
                    (node) -> node.getUserObject() instanceof DataSource);

            List<DataSource> dataSourcesForRemoval = Arrays.stream(selectedNodes)
                    .map((node) -> (DataSource) node.getUserObject())
                    .collect(Collectors.toList());

            if (dataSourcesForRemoval.size() > 0) {
                dataSourcesView.removeDataSources(dataSourcesForRemoval);
            }
        });
        decorator.setRemoveActionUpdater(e -> {
            DefaultMutableTreeNode[] selectedNodes = dataSourceTree.getSelectedNodes(DefaultMutableTreeNode.class,
                    (node) -> node.getUserObject() instanceof DataSource);

            return selectedNodes.length > 0;
        });
    }

    private void initEditAction() {
        decorator.setEditActionUpdater(e -> {
            DefaultMutableTreeNode[] selectedNodes = dataSourceTree.getSelectedNodes(DefaultMutableTreeNode.class,
                    (node) -> node.getUserObject() instanceof DataSource);

            return selectedNodes.length == 1;
        });
        decorator.setEditAction(anActionButton -> {
            PatchedDefaultMutableTreeNode[] selectedNodes = dataSourceTree.getSelectedNodes(PatchedDefaultMutableTreeNode.class,
                    (node) -> node.getUserObject() instanceof DataSource);

            if (selectedNodes.length == 1) {
                PatchedDefaultMutableTreeNode treeNode = selectedNodes[0];

                DataSource dataSourceToEdit = (DataSource) treeNode.getUserObject();

                DataSourceDialog dialog = null;
                if (dataSourceToEdit.getDataSourceType().equals(DataSourceType.NEO4J_BOLT)) {
                    dialog = new Neo4jBoltDataSourceDialog(project, dataSourcesView, dataSourceToEdit);
                }

                if (dialog != null) {
                    if (dialog.go()) {
                        dataSourcesView.updateDataSource(treeNode, dataSourceToEdit, dialog.constructDataSource());
                    }
                }
            }
        });
    }
}
