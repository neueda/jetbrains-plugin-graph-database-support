package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeContextMenuMouseAdapter;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.renderes.tree.PropertyTreeCellRenderer;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.UiHelper;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;

public class ValueConverter {

    private final TablePanel tablePanel;

    public ValueConverter(TablePanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public Object convert(String columnName, Object object, DataSourceApi dataSourceApi) {
        if (object == null) {
            return null;
        }

        if (UiHelper.canBeTree(object)) {
            return createTree(UiHelper.keyValueToTreeNode(columnName, object, dataSourceApi, object));
        } else {
            return objectToString(object);
        }
    }

    private Object objectToString(Object object) {
        return object.toString();
    }

    private Tree createTree(PatchedDefaultMutableTreeNode root) {
        DefaultTreeModel treeModel = new DefaultTreeModel(root, false);
        Tree tree = new Tree();
        tree.setModel(treeModel);
        tree.setCellRenderer(new PropertyTreeCellRenderer());
        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                tablePanel.updateRowHeights();
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                tablePanel.updateRowHeights();
            }
        });

        tree.addMouseListener(new TreeContextMenuMouseAdapter());

        treeModel.reload();
        return tree;
    }
}
