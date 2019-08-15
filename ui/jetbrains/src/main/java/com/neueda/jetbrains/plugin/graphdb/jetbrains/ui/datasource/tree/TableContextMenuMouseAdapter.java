package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.model.ObjectModel;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.UiHelper.cast;

public class TableContextMenuMouseAdapter extends MouseAdapter {

    private ContextMenuService contextMenuService = new ContextMenuService();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            JBTable table = (JBTable) e.getComponent();

            int selectedRow = table.rowAtPoint(e.getPoint());
            int selectedColumn = table.columnAtPoint(e.getPoint());

            Object value = table.getModel().getValueAt(selectedRow, selectedColumn);

            DataContext dataContext = DataManager.getInstance()
                    .getDataContext(table, e.getX(), e.getY());

            cast(value, Tree.class)
                    .map(tree -> {
                        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                        return contextMenuService.getContextMenu(path);
                    })
                    .orElseGet(() -> {
                        String columnName = table.getModel().getColumnName(selectedColumn);
                        return new ObjectModel(trim(value), columnName, String.valueOf(value), null, value)
                                .getContextMenu();
                    })
                    .ifPresent(c -> c.showPopup(dataContext));
        }
    }

    private Object trim(Object value) {
        return Optional.ofNullable(value)
                .flatMap(e -> cast(e, String.class))
                .map(s -> s.replaceAll("^\"|\"$", ""))
                .orElse(null);
    }
}
