package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.tree.PropertyTreeCellRenderer;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;
import java.util.Map;

public class ValueConverter {

    private final TablePanel tablePanel;

    public ValueConverter(TablePanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public Object convert(Object object) {
        if (object instanceof GraphNode) {
            return nodeToTree((GraphNode) object);
        } else {
            return objectToString(object);
        }
    }

    private Object objectToString(Object object) {
        return object.toString();
    }

    private Tree nodeToTree(GraphNode node) {
        Tree tree = new Tree();
        PatchedDefaultMutableTreeNode treeRoot = new PatchedDefaultMutableTreeNode(node.getRepresentation());
        DefaultTreeModel treeModel = new DefaultTreeModel(treeRoot, false);

        Map<String, Object> properties = node.getPropertyContainer().getProperties();
        properties.forEach((key, val) -> {
            PatchedDefaultMutableTreeNode keyNode = new PatchedDefaultMutableTreeNode(key);
            PatchedDefaultMutableTreeNode valNode = new PatchedDefaultMutableTreeNode(val);

            keyNode.add(valNode);
            treeRoot.add(keyNode);
        });
        treeModel.reload();
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

        return tree;
    }
}
