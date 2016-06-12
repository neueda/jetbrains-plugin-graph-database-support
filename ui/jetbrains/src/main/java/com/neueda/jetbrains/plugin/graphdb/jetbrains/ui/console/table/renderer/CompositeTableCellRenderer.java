package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.renderer;

import com.intellij.ui.treeStructure.Tree;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class CompositeTableCellRenderer implements TableCellRenderer{

    private final TreeModelTableCellRenderer treeModelTableCellRenderer;
    private final DefaultTableCellRenderer defaultTableCellRenderer;

    public CompositeTableCellRenderer() {
        defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setVerticalAlignment(SwingConstants.TOP);
        treeModelTableCellRenderer = new TreeModelTableCellRenderer();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Tree) {
            return treeModelTableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

        return defaultTableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
