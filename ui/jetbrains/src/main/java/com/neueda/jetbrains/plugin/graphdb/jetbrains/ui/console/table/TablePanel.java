package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.ui.table.JBTable;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.ShowQueryExecutionResultEvent;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TablePanel {

    private DefaultTableModel tableModel;

    public TablePanel() {
        tableModel = new QueryResultTableModel();
    }

    public void initialize(ConsoleToolWindow consoleToolWindow, MessageBus messageBus) {
        JBTable table = consoleToolWindow.getTableExecuteResults();
        table.setModel(tableModel);

        messageBus.connect().subscribe(ShowQueryExecutionResultEvent.SHOW_QUERY_EXECUTION_RESULT_TOPIC, new ShowQueryExecutionResultEvent() {
            @Override
            public void preShowResult() {
                tableModel.setColumnCount(0);
                tableModel.setRowCount(0);
            }

            @Override
            public void showResult(GraphQueryResult result) {
                List<GraphQueryResultColumn> columns = result.getColumns();
                columns.forEach((column) -> tableModel.addColumn(column.getName()));

                result.getRows().forEach((row) -> {
                    List<String> data = new ArrayList<>(columns.size());

                    columns.forEach((column) -> {
                        data.add(row.getValue(column).toString());
                    });

                    tableModel.addRow(data.toArray());
                });
            }

            @Override
            public void postShowResult() {
            }

            @Override
            public void handleError(Exception exception) {
            }
        });
    }
}
