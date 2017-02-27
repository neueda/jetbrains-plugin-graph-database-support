package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ui.table.JBTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableContextMenuMouseAdapter extends MouseAdapter {

    private ContextMenuService contextMenuService = new ContextMenuService();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            JBTable table = (JBTable) e.getComponent();
            System.out.println(table.getName());

//            contextMenuService.getContextMenu(pathForLocation)
//                    .ifPresent(dto -> dto.showPopup(dataContext));
        }
    }
}
