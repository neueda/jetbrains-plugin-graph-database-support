package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.ui.table.JBTable;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.ShowQueryExecutionResultEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.editor.CompositeTableCellEditor;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.renderer.CompositeTableCellRenderer;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class TablePanel {

    private final ValueConverter valueConverter;
    private QueryResultTableModel tableModel;
    private JBTable table;

    public TablePanel() {
        valueConverter = new ValueConverter(this);
    }

    public void initialize(ConsoleToolWindow consoleToolWindow, MessageBus messageBus) {
        tableModel = new QueryResultTableModel();
        table = consoleToolWindow.getTableExecuteResults();
        table.setModel(tableModel);
        table.setCellSelectionEnabled(false);
        table.setDefaultRenderer(Object.class, new CompositeTableCellRenderer());
        table.setDefaultEditor(Object.class, new CompositeTableCellEditor());

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
                    List<Object> data = new ArrayList<>(columns.size());

                    columns.forEach((column) -> {
                        data.add(valueConverter.convert(row.getValue(column)));
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

    public void updateRowHeights() {
        for (int row = 0; row < table.getRowCount(); row++) {
            int rowHeight = table.getRowHeight();
            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            table.setRowHeight(row, rowHeight);
        }
    }
}
