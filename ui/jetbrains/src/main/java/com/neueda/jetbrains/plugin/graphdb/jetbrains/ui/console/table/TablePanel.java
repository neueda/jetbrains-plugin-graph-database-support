package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import com.intellij.openapi.project.Project;
import com.intellij.ui.table.JBTable;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.CopyQueryOutputEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryExecutionProcessEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.editor.CompositeTableCellEditor;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.renderer.CompositeTableCellRenderer;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TableContextMenuMouseAdapter;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.SerialisationHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        table.setDefaultRenderer(Object.class, new CompositeTableCellRenderer());
        table.setDefaultEditor(Object.class, new CompositeTableCellEditor());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.addMouseListener(new TableContextMenuMouseAdapter());

        ColumnResizer cr = new ColumnResizer(table, MAX_WIDTH);

        messageBus.connect().subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
            private DataSourceApi dataSourceApi;

            @Override
            public void executionStarted(DataSourceApi dataSource, ExecuteQueryPayload payload) {
                this.dataSourceApi = dataSource;
                tableModel.setColumnCount(0);
                tableModel.setRowCount(0);
            }

            @Override
            public void resultReceived(ExecuteQueryPayload payload, GraphQueryResult result) {
                List<GraphQueryResultColumn> columns = result.getColumns();
                columns.forEach((column) -> tableModel.addColumn(column.getName()));

                result.getRows().forEach((row) -> {
                    List<Object> data = new ArrayList<>(columns.size());

                    columns.forEach((column) -> data.add(valueConverter.convert(column.getName(), row.getValue(column), this.dataSourceApi)));

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

        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (e.isControlDown()) {
                    if (e.getKeyCode() == KeyEvent.VK_C) { // Copy
                        StringSelection selection = new StringSelection(SerialisationHelper.convertToCsv(table, true));
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(selection, selection);
                    }
                }
            }
        });

        messageBus.connect().subscribe(CopyQueryOutputEvent.COPY_QUERY_OUTPUT_TOPIC, () -> {
            JBTable tableToExport = graphConsoleView.getTableExecuteResults();
            StringSelection selection = new StringSelection(SerialisationHelper.convertToCsv(tableToExport, false));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);

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
