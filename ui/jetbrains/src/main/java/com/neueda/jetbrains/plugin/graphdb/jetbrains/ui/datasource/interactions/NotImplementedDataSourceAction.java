package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

public class NotImplementedDataSourceAction extends AnAction {

    public NotImplementedDataSourceAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        // do nothing
    }
}
