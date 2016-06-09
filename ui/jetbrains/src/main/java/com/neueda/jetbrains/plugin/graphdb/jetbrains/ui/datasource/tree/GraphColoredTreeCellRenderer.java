package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class GraphColoredTreeCellRenderer extends ColoredTreeCellRenderer {

    private final DataSourcesComponent component;

    public GraphColoredTreeCellRenderer(DataSourcesComponent component) {
        this.component = component;
    }

    @Override
    public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded,
                                      boolean leaf, int row, boolean hasFocus) {
        Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

        if (userObject instanceof DataSource) {
            DataSource dataSource = (DataSource) userObject;
            append(dataSource.getName(), SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES, true);
            setIcon(component.getDataSourceIcon(dataSource));
        } else {
            append(value.toString());
        }
    }
}
