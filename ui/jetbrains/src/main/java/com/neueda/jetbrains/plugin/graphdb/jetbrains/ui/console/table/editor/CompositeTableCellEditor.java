package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.editor;

import com.intellij.ui.treeStructure.Tree;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import java.awt.Component;

public class CompositeTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private final TreeModelTableCellEditor treeModelTableCellEditor;
    private TableCellEditor current;

    public CompositeTableCellEditor() {
        treeModelTableCellEditor = new TreeModelTableCellEditor();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof Tree) {
            current = treeModelTableCellEditor;
        }
        if (current == null) {
            throw new IllegalArgumentException("No cell editor for " + value.getClass());
        }
        return treeModelTableCellEditor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
        return current.getCellEditorValue();
    }
}
