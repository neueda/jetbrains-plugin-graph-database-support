package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.messages.MessageBus;

import java.awt.event.InputEvent;

public class QueryActionWrapper {

    private final AnActionEvent event;

    public QueryActionWrapper(AnActionEvent event) {
        this.event = event;
    }

    public Project getProject() {
        return event.getProject();
    }

    public Editor getEditor() {
        return event.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);
    }

    public PsiFile getPsiFile() {
        return event.getData(CommonDataKeys.PSI_FILE);
    }

    public MessageBus getProjectMessageBus() {
        return getProject().getMessageBus();
    }

    public DataContext getDataContext() {
        return event.getDataContext();
    }

    public InputEvent getInputEvent() {
        return event.getInputEvent();
    }

    public Caret getCaret() {
        return getEditor().getCaretModel().getPrimaryCaret();
    }

    public AnActionEvent getEvent() {
        return event;
    }

}
