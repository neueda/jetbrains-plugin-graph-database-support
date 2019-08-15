package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.SerialisationHelper;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CopyToClipboardAction extends AnAction {
    private Object object;

    CopyToClipboardAction(String title, String description, Icon icon, Object object) {
        super(title, description, icon);
        this.object = object;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        StringSelection selection = new StringSelection(SerialisationHelper.convertToCsv(object));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
