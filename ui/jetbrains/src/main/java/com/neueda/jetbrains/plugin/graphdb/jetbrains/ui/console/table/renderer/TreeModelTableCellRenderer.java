package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.renderer;

import com.intellij.ui.treeStructure.Tree;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class TreeModelTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return (Tree) value;
    }
}
