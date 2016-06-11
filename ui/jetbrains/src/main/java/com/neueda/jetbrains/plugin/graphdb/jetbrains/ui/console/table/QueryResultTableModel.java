package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import javax.swing.table.DefaultTableModel;

public class QueryResultTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
