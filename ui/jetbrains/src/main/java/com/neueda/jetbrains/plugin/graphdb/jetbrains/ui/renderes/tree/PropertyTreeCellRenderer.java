package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.renderes.tree;

import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeNodeModelApi;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class PropertyTreeCellRenderer extends ColoredTreeCellRenderer {

    @Override
    public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

        if (userObject instanceof TreeNodeModelApi) {
            TreeNodeModelApi model = (TreeNodeModelApi) userObject;

            append(model.getText().get() + ": ", SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES, true);
            if (model.getDescription().isPresent()) {
                append(model.getDescription().get(), SimpleTextAttributes.GRAYED_SMALL_ATTRIBUTES);
            } else {
                if (model.getValue().isPresent()) {
                    append(model.getValue().get().toString());
                }
            }
        } else if (userObject != null) {
            append(userObject.toString());
        }
    }
}
