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

            Optional<TreeNodeModelApi> model = extractUserObject(lastPathComponent);

            return model.flatMap(TreeNodeModelApi::getContextMenu);
        }
        return Optional.empty();
    }

    private Optional<TreeNodeModelApi> extractUserObject(TreeNode node) {
        if (node instanceof PatchedDefaultMutableTreeNode) {
            return Optional.of((TreeNodeModelApi) ((PatchedDefaultMutableTreeNode) node).getUserObject());
        }
        return Optional.empty();
    }
}
