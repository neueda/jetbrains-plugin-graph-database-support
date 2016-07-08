package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.ide.scratch.ScratchFileService;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.neo4j.bolt.Neo4jBoltDataSourceDialog;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.util.Notifier;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
        initQuickEditorAction();
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

    private void initQuickEditorAction() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickCount = e.getClickCount();
                if (clickCount == 2) {
                    try {
                        VirtualFile file = ScratchFileService.getInstance().findFile(
                                GraphDbConsoleRootType.getInstance(),
                                "test-editor.cyp",
                                ScratchFileService.Option.create_if_missing
                        );

                        FileEditorManager.getInstance(project).openFile(file, true);

                        FileEditor[] fileEditors = FileEditorManager.getInstance(project).openFile(file, true);
//                        FileEditorManager.getInstance(project).openEditor(new OpenFileDescriptor(project, file), true);
//                        FileEditorManager.getInstance(project).
                    } catch (IOException exception) {
                        Notifier.error("Open editor error", exception.getMessage());
                    }
                }
            }
        };
        dataSourceTree.addMouseListener(mouseAdapter);
    }
}
