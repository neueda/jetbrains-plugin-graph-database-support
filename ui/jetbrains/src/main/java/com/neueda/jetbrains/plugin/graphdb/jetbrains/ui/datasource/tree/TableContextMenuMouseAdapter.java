package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableContextMenuMouseAdapter extends MouseAdapter {

    private ContextMenuService contextMenuService = new ContextMenuService();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            JBTable table = (JBTable) e.getComponent();

            int selectedRow = table.rowAtPoint(e.getPoint());
            int selectedColumn = table.columnAtPoint(e.getPoint());

            Object value = table.getModel().getValueAt(selectedRow, selectedColumn);
            if (Tree.class.isAssignableFrom(value.getClass())) {
                Tree tree = (Tree) value;

                TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                DataContext dataContext = DataManager.getInstance()
                        .getDataContext(table, e.getX(), e.getY());

                contextMenuService.getContextMenu(path)
                        .ifPresent(dto -> dto.showPopup(dataContext));
            }
        }
    }
}
