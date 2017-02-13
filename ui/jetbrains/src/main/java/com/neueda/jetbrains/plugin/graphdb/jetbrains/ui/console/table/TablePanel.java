package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.openapi.project.Project;
import com.intellij.ui.table.JBTable;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryExecutionProcessEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.editor.CompositeTableCellEditor;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.renderer.CompositeTableCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TablePanel {

    public static final int MAX_WIDTH = 300;
    private final ValueConverter valueConverter;
    private QueryResultTableModel tableModel;
    private JBTable table;

    public TablePanel() {
        valueConverter = new ValueConverter(this);
    }

    public void initialize(GraphConsoleView graphConsoleView, Project project) {
        MessageBus messageBus = project.getMessageBus();
        tableModel = new QueryResultTableModel();
        table = graphConsoleView.getTableExecuteResults();
        table.setModel(tableModel);
        table.setCellSelectionEnabled(false);
        table.setDefaultRenderer(Object.class, new CompositeTableCellRenderer());
        table.setDefaultEditor(Object.class, new CompositeTableCellEditor());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        ColumnResizer cr = new ColumnResizer(table, MAX_WIDTH);

        messageBus.connect().subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
            @Override
            public void executionStarted(ExecuteQueryPayload payload) {
                tableModel.setColumnCount(0);
                tableModel.setRowCount(0);
            }

            @Override
            public void resultReceived(ExecuteQueryPayload payload, GraphQueryResult result) {
                List<GraphQueryResultColumn> columns = result.getColumns();
                columns.forEach((column) -> tableModel.addColumn(column.getName()));

                result.getRows().forEach((row) -> {
                    List<Object> data = new ArrayList<>(columns.size());

                    columns.forEach((column) -> {
                        data.add(valueConverter.convert(column.getName(), row.getValue(column)));
                    });

                    tableModel.addRow(data.toArray());
                });
            }

            @Override
            public void postResultReceived(ExecuteQueryPayload payload) {
                cr.resize();
                updateRowHeights();
            }

            @Override
            public void handleError(ExecuteQueryPayload payload, Exception exception) {
            }

            @Override
            public void executionCompleted(ExecuteQueryPayload payload) {
            }
        });
    }

    public void updateRowHeights() {
        for (int row = 0; row < table.getRowCount(); row++) {
            int rowHeight = 1;
            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            table.setRowHeight(row, rowHeight);
        }
    }
}
