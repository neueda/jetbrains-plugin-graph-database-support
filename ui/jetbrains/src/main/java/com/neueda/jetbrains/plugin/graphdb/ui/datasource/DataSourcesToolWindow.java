package com.neueda.jetbrains.plugin.graphdb.ui.datasource;

import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions.DataSourceInteractions;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.tree.GraphColoredTreeCellRenderer;
import org.jetbrains.annotations.NotNull;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeModel;
import java.awt.GridLayout;

public class DataSourcesToolWindow implements ToolWindowFactory {

    private DataSourcesComponent component;
    private DataSourceInteractions interactions;
    private PatchedDefaultMutableTreeNode treeRoot;
    private DefaultTreeModel treeModel;

    private JPanel toolWindowContent;
    private JPanel treePanel;
    private Tree dataSourceTree;
    private ToolbarDecorator decorator;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

        component = project.getComponent(DataSourcesComponent.class);
        treeRoot = new PatchedDefaultMutableTreeNode("treeRoot");
        treeModel = new DefaultTreeModel(treeRoot, false);
        decorator = ToolbarDecorator.createDecorator(dataSourceTree);

        configureDataSourceTree();
        decorateDataSourceTree();

        interactions = new DataSourceInteractions(project, this);

        replaceTreeWithDecorated();

        showDataSources();
    }

    public DataSourcesComponent getComponent() {
        return component;
    }

    public PatchedDefaultMutableTreeNode getTreeRoot() {
        return treeRoot;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public ToolbarDecorator getDecorator() {
        return decorator;
    }

    private void createUIComponents() {
        treePanel = new JPanel(new GridLayout(0, 1));
    }

    private void configureDataSourceTree() {
        dataSourceTree.getEmptyText().setText("Create a data source");
        dataSourceTree.setCellRenderer(new GraphColoredTreeCellRenderer());
        dataSourceTree.setModel(treeModel);
        dataSourceTree.setRootVisible(false);
    }

    private void decorateDataSourceTree() {
        decorator.setPanelBorder(BorderFactory.createEmptyBorder());
        decorator.setToolbarPosition(ActionToolbarPosition.TOP);
    }

    private void replaceTreeWithDecorated() {
        JPanel panel = decorator.createPanel();
        treePanel.add(panel);
    }

    private void showDataSources() {
        component.getDataSources()
                .forEach((dataSource) -> treeRoot.add(new PatchedDefaultMutableTreeNode(dataSource)));
        treeModel.reload();
    }

    public void createDataSource(DataSource dataSource) {
        component.addDataSource(dataSource);
        treeRoot.add(new PatchedDefaultMutableTreeNode(dataSource));
        treeModel.reload();
    }
}
