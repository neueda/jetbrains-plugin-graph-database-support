package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.ui.components.labels.LinkLabel;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class TreeMouseAdapter extends MouseAdapter {

    private ContextMenuService contextMenuService = new ContextMenuService();

    @Override
    public void mouseClicked(MouseEvent e) {
        Tree tree = (Tree) e.getComponent();
        TreePath pathForLocation = tree.getPathForLocation(e.getX(), e.getY());

        if (SwingUtilities.isLeftMouseButton(e)) {
            Optional.ofNullable(pathForLocation)
                    .flatMap(p -> cast(p.getLastPathComponent(), PatchedDefaultMutableTreeNode.class))
                    .flatMap(n -> cast(n.getUserObject(), LinkLabel.class))
                    .ifPresent(LinkLabel::doClick);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            DataContext dataContext = DataManager.getInstance().getDataContext(tree);
            contextMenuService.getContextMenu(pathForLocation)
                    .ifPresent(dto -> dto.showPopup(dataContext));
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Optional<T> cast(Object o, Class<T> clazz) {
        if (clazz.isInstance(o)) {
            return Optional.of((T) o);
        } else {
            return Optional.empty();
        }
    }
}
