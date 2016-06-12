package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.tree.PropertyTreeCellRenderer;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.UiHelper;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;

public class ValueConverter {

    private final TablePanel tablePanel;

    public ValueConverter(TablePanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public Object convert(Object object) {
        if (object instanceof GraphNode) {
            return createTree(UiHelper.nodeToTree((GraphNode) object));
        }
        if (object instanceof GraphRelationship) {
            return createTree(UiHelper.relationshipToTree((GraphRelationship) object));
        }
        return objectToString(object);
    }

    private Object objectToString(Object object) {
        return object.toString();
    }

    private Tree createTree(PatchedDefaultMutableTreeNode root) {
        DefaultTreeModel treeModel = new DefaultTreeModel(root, false);
        Tree tree = new Tree();
        tree.setModel(treeModel);
        tree.collapseRow(0);
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

        treeModel.reload();
        return tree;
    }
}
