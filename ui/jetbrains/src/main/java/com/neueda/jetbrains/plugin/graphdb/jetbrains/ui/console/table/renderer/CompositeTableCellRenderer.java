package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.renderer;

import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.SerialisationHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class CompositeTableCellRenderer implements TableCellRenderer {

    private final TreeModelTableCellRenderer treeModelTableCellRenderer;
    private final DefaultTableCellRenderer defaultTableCellRenderer;

    public CompositeTableCellRenderer() {
        defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setVerticalAlignment(SwingConstants.TOP);
        treeModelTableCellRenderer = new TreeModelTableCellRenderer();
    }

    private static final KeyAdapter COPY_KEY_ADAPTER = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.isControlDown()) {
                if (e.getKeyCode() == KeyEvent.VK_C) { // Copy
                    List<TreePath> v = Arrays.asList(((Tree) e.getComponent()).getSelectionModel().getSelectionPaths());
                    String str = SerialisationHelper.convertToCsv(v);
                    StringSelection selection = new StringSelection(str);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                }
            }
        }
    };

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Tree) {
            Component component = treeModelTableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (Arrays.stream(((Tree) value).getKeyListeners()).noneMatch(o -> o.equals(COPY_KEY_ADAPTER))) {
                ((Tree) value).addKeyListener(COPY_KEY_ADAPTER);
            }
            return component;
        }

        return defaultTableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
