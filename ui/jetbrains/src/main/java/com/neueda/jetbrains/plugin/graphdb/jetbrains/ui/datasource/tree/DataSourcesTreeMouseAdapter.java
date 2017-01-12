package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DataSourcesTreeMouseAdapter extends MouseAdapter {

    private ContextMenuService contextMenuService = new ContextMenuService();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            Tree tree = (Tree) e.getComponent();
            TreePath pathForLocation = tree.getPathForLocation(e.getX(), e.getY());

            DataContext dataContext = DataManager.getInstance().getDataContext(tree);
            contextMenuService.getContextMenu(pathForLocation)
                    .ifPresent(dto -> dto.showPopup(dataContext));
        }
    }
}
