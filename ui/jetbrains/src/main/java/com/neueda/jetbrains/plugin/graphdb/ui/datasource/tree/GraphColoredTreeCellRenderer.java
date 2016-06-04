package com.neueda.jetbrains.plugin.graphdb.ui.datasource.tree;

import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.JTree;

public class GraphColoredTreeCellRenderer extends ColoredTreeCellRenderer {

    @Override
    public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        append(value.toString(), SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES, true);
        setIcon(GraphIcons.Database.NEO4J);
    }
}
