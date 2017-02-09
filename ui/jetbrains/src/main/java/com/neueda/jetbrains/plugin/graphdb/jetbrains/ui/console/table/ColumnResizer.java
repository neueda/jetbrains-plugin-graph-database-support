package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ColumnResizer implements TableModelListener {

    private final JTable table;
    private final int minMargin;

    public ColumnResizer(JTable table) {
        this.table = table;
        minMargin = strWidth("ww");
    }

    public void resize() {

        int cols = table.getColumnCount();
        int rows = table.getRowCount();
        int margin = Math.max(table.getIntercellSpacing().width, minMargin);

        TableColumnModel model = table.getColumnModel();

        for (int c = 0; c < cols; c++) {
            TableColumn col = model.getColumn(c);
            String header = String.valueOf(col.getHeaderValue());
            int headerWidth = strWidth(header) + margin;

            for (int r = 0; r < rows; r++) {
                TableCellRenderer render = table.getCellRenderer(r, c);
                Component component = table.prepareRenderer(render, r, c);
                int rendererWidth = component.getPreferredSize().width;

                int cellWidth = Math.max(rendererWidth + margin, 1);

                col.setPreferredWidth(Math.max(headerWidth, cellWidth));
            }
        }
    }

    private int strWidth(String str) {
        return table.getFontMetrics(table.getFont())
                .charsWidth(str.toCharArray(), 0, str.length());
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        resize();
    }

}
