package com.neueda.jetbrains.plugin.graphdb.actions.execute;

import com.intellij.openapi.actionSystem.ActionButtonComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.psi.PsiFile;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.ui.util.Notifier;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class ExecuteQueryAction extends AnAction {

    private static final List<String> SUPPORTED_LANGUAGES = new ArrayList<>();

    static {
        SUPPORTED_LANGUAGES.add("Cypher");
    }

    @Override
    public void update(AnActionEvent e) {
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        if (psiFile != null) {
            String languageId = psiFile.getLanguage().getID();

            if (SUPPORTED_LANGUAGES.contains(languageId)) {
                e.getPresentation().setEnabled(true);
                return;
            }
        }
        e.getPresentation().setEnabled(false);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);

        if (project == null) {
            Notifier.error("Execute query error", "No project present.");
            return;
        }
        if (editor == null) {
            Notifier.error("Execute query error", "No editor present.");
            return;
        }

        MessageBus messageBus = project.getMessageBus();
        DataSourcesComponent dataSourcesComponent = project.getComponent(DataSourcesComponent.class);

        String content = editor.getDocument().getCharsSequence().toString();
        Caret caret = editor.getCaretModel().getPrimaryCaret();
        ExecuteQueryPayload executeQueryPayload = new ExecuteQueryPayload(content, caret);

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
}
