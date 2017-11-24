package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

public class GutterQueryExecutionAction extends AnAction {

    private final PsiElement element;
    private final ExecuteQueryService executionProvider;

    public GutterQueryExecutionAction(@Nullable String text, @Nullable String description, @Nullable Icon icon, PsiElement element) {
        super(text, description, icon);
        this.element = element;
        this.executionProvider = ExecuteQueryService.getInstance();
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        QueryActionWrapper actionWrapper = new QueryActionWrapper(e);
        executionProvider.executeQuery(actionWrapper, element);
    }

}
