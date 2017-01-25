package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.ActionButtonComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.Analytics;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params.ParametersService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.NameUtil;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.Notifier;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.PsiTraversalUtilities;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphLanguages;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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
        Analytics.event("query", e.getInputEvent() instanceof KeyEvent ? "executeWithShortcut" : "executeWithMouse");

        Project project = getEventProject(e);
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        VirtualFile virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);

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

        String query = null;
        Map<String, Object> parameters = Collections.emptyMap();
        if (caret.hasSelection()) {
            query = caret.getSelectedText();
        } else if (psiFile != null) {
            if (psiFile.getLanguage().getID().equals(GraphLanguages.CYPHER)) {
                PsiElement cypherStatement = getCypherStatement(psiFile, caret);
                if (cypherStatement != null) {
                    query = cypherStatement.getText();
                    try { // support parameters for PsiElement only
                        ParametersService service = ServiceManager.getService(project, ParametersService.class);
                        parameters = service.getParameters(cypherStatement);
                    } catch (Exception exception) {
                        sendParametersRetrievalErrorEvent(messageBus, exception, editor);
                        return;
                    }
                }
            }
        }

        Analytics.event("query-query", caret.hasSelection() ? "contentFromSelect" : "contentFromCaret");

        if (query == null) {
            Notifier.error("Query execution error", "No query selected");
            return;
        }

        query = decorateQuery(query);

        ExecuteQueryPayload executeQueryPayload = new ExecuteQueryPayload(query, parameters, editor);
        ConsoleToolWindow.ensureOpen(project);

        if (virtualFile != null) {
            String fileName = virtualFile.getName();
            if (fileName.startsWith(GraphConstants.BOUND_DATA_SOURCE_PREFIX)) {
                Optional<? extends DataSourceApi> boundDataSource = dataSourcesComponent.getDataSourceContainer()
                           .findDataSource(NameUtil.extractDataSourceUUID(fileName));
                if (boundDataSource.isPresent()) {
                    executeQuery(messageBus, boundDataSource.get(), executeQueryPayload);
                    return;
                }
            }
        }

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

    public void executeQuery(MessageBus messageBus, DataSourceApi dataSource, ExecuteQueryPayload payload) {
        ExecuteQueryEvent executeQueryEvent = messageBus.syncPublisher(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC);
        executeQueryEvent.executeQuery(dataSource, payload);
    }

    protected String decorateQuery(String query) {
        return query;
    }

    private PsiElement getCypherStatement(PsiFile psiFile, Caret caret) {
        return PsiTraversalUtilities.Cypher.getCypherStatementAtOffset(psiFile, caret.getOffset());
    }

    private void sendParametersRetrievalErrorEvent(MessageBus messageBus, Exception exception, Editor editor) {
        QueryParametersRetrievalErrorEvent event = messageBus
                .syncPublisher(QueryParametersRetrievalErrorEvent.QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC);
        event.handleError(exception, editor);
    }
}
