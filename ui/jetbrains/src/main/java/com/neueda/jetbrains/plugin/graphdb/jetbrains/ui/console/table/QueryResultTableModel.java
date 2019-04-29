package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.ui.treeStructure.Tree;

import javax.swing.table.DefaultTableModel;

public class QueryResultTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column) {
        Object valueAt = getValueAt(row, column);
        return valueAt != null && valueAt instanceof Tree;
    }
}
