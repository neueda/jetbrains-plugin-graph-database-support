package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;

import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Optional;

public class ContextMenuService {

    public Optional<ContextMenu> getContextMenu(TreePath path) {
        if (path != null) {
            TreeNode lastPathComponent = (TreeNode) path.getLastPathComponent();

            TreeNodeModelApi model = extractUserObject(lastPathComponent);

            return Optional.ofNullable(decideOnMenu(model));
        }
        return Optional.empty();
    }

    public ContextMenu decideOnMenu(TreeNodeModelApi model) {
        NodeType type = model.getType();
        if (model.getText().isPresent()
                && (type == Neo4jTreeNodeType.LABEL
                || type == Neo4jTreeNodeType.RELATIONSHIP
                || type == Neo4jTreeNodeType.PROPERTY_KEY)) {
            return new ContextMenu(type, model.getDataSourceApi(), model.getText().get());
        } else if (type == Neo4jTreeNodeType.DATASOURCE) {
            return new ContextMenu(type, model.getDataSourceApi());
        } else {
            return null;
        }
    }

    private TreeNodeModelApi extractUserObject(TreeNode node) {
        if (node instanceof PatchedDefaultMutableTreeNode) {
            return (TreeNodeModelApi) ((PatchedDefaultMutableTreeNode) node).getUserObject();
        }
        return null;
    }
}
