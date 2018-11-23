package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ValueWithIcon;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
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

        if (userObject instanceof DataSourceApi) {
            DataSourceApi dataSource = (DataSourceApi) userObject;
            setIcon(dataSource.getDescription().getIcon());
            append(dataSource.getName(), SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES, true);
        } else if (userObject instanceof ValueWithIcon) {
            ValueWithIcon val = (ValueWithIcon) userObject;
            setIcon(val.getIcon());
            append(val.getValue());
        } else if (userObject instanceof TreeNodeModelApi) {
            TreeNodeModelApi model = (TreeNodeModelApi) userObject;
            model.getIcon().ifPresent(this::setIcon);
            model.getText().ifPresent(this::append);
        } else if (userObject != null) {
            append(value.toString());
        }
    }
}
