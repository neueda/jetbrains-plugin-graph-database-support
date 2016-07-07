package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.ActionButtonComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.psi.PsiTraversalUtilities;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.util.Notifier;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphLanguages;

import java.awt.Component;

public class ExecuteQueryAction extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);

        if (editor != null) {
            e.getPresentation().setEnabled(true);
        } else {
            e.getPresentation().setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);

        if (project == null) {
            Notifier.error("Query execution error", "No project present.");
            return;
        }
        if (editor == null) {
            Notifier.error("Query execution error", "No editor present.");
            return;
        }

        MessageBus messageBus = project.getMessageBus();
        DataSourcesComponent dataSourcesComponent = project.getComponent(DataSourcesComponent.class);

        Caret caret = editor.getCaretModel().getPrimaryCaret();

        String content = null;
        if (caret.hasSelection()) {
            content = caret.getSelectedText();
        } else if (psiFile != null) {
            if (psiFile.getLanguage().getID().equals(GraphLanguages.CYPHER)) {
                String cypherStatement = getCypherStatement(psiFile, caret);
                if (cypherStatement != null) {
                    content = cypherStatement;
                }
            }
        }

        if (content == null) {
            Notifier.error("Query execution error", "No query selected");
            return;
        }

        ExecuteQueryPayload executeQueryPayload = new ExecuteQueryPayload(content);

        ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                "Choose Data Source",
                new ChooseDataSourceActionGroup(messageBus, dataSourcesComponent, executeQueryPayload),
                e.getDataContext(),
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                false
        );

        Component eventComponent = e.getInputEvent().getComponent();
        if (eventComponent instanceof ActionButtonComponent) {
            popup.showUnderneathOf(eventComponent);
        } else {
            popup.showInBestPositionFor(e.getDataContext());
        }
    }

    private String getCypherStatement(PsiFile psiFile, Caret caret) {
        PsiElement element = PsiTraversalUtilities.Cypher.getCypherStatementAtOffset(psiFile, caret.getOffset());

        if (element == null) {
            return null;
        } else {
            return element.getText();
        }
    }
}
