package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.NoIdGraphEntity;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.SerialisationHelper;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class EntityCopyToClipboardAction extends AnAction {
    private Object node;

    EntityCopyToClipboardAction(String title, String description, Icon icon, NoIdGraphEntity node) {
        super(title, description, icon);
        this.node = node;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        StringSelection selection = new StringSelection(SerialisationHelper.convertToCsv(node));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
