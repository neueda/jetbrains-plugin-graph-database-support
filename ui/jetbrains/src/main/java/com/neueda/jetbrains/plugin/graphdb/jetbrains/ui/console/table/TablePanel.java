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

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
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

        messageBus.connect().subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
            @Override
            public void executionStarted(ExecuteQueryPayload payload) {
                tableModel.setColumnCount(0);
                tableModel.setRowCount(0);
            }

            @Override
            public void resultReceived(GraphQueryResult result) {
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
            public void postResultReceived() {
                updateColumnWidths();
                updateRowHeights();
            }

            @Override
            public void handleError(Exception exception) {
            }

            @Override
            public void executionCompleted() {
            }
        });
    }

    private void updateColumnWidths() {
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = MAX_WIDTH;

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows
                if (preferredWidth >= MAX_WIDTH) {
                    preferredWidth = MAX_WIDTH;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }
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
