package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;

import javax.swing.table.DefaultTableModel;

public class TablePanel {

    private final DefaultTableModel resultTableModel;

    public TablePanel() {
        resultTableModel = new DefaultTableModel();
    }

    public void initialize(ConsoleToolWindow consoleToolWindow, MessageBus messageBus) {
        resultTableModel.addColumn("key");
        resultTableModel.addColumn("value");

        consoleToolWindow.getTableExecuteResults().setModel(resultTableModel);
        for (int i = 0; i < 1000; i++) {
            resultTableModel.addRow(new String[]{"test1", "test2"});
        }
    }
}
