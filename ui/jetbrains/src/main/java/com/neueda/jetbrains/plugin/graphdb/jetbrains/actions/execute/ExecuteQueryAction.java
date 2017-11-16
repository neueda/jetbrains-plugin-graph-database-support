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
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.util.PsiTraversalUtilities.Cypher.getCypherStatementAtOffset;
import static com.neueda.jetbrains.plugin.graphdb.platform.SupportedLanguage.isSupported;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ExecuteQueryAction extends AnAction {

    private static final String QUERY_EXECUTION_ERROR_TITLE = "Query execution error";
    private static final String NO_PROJECT_PRESENT_MESSAGE = "No project present.";
    private static final String NO_EDITOR_PRESENT_MESSAGE = "No editor present.";
    private static final String NO_QUERY_SELECTED_MESSAGE = "No query selected";

    private static final String EXECUTE_WITH_SHORTCUT_ACTION = "executeWithShortcut";
    private static final String EXECUTE_WITH_MOUSE_ACTION = "executeWithMouse";
    private static final String CONTENT_FROM_SELECT_ACTION = "contentFromSelect";
    private static final String CONTENT_FROM_CARET_ACTION = "contentFromCaret";

    public ExecuteQueryAction() {
    }

    public ExecuteQueryAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void update(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);

        if (nonNull(editor)) {
            e.getPresentation().setEnabled(true);
        } else {
            e.getPresentation().setEnabled(false);
        }
    }

    ExecuteQueryPayload createQueryPayload(ActionState state) {
        String query = null;
        Map<String, Object> parameters = Collections.emptyMap();
        if (state.getCaret().hasSelection()) {
            query = state.getCaret().getSelectedText();
        } else if (nonNull(state.getPsiFile())) {
            String languageId = state.getPsiFile().getLanguage().getID();
            if (isSupported(languageId)) {
                PsiElement cypherStatement = getCypherStatementAtOffset(state.getPsiFile(), state.getCaret().getOffset());
                if (nonNull(cypherStatement)) {
                    query = cypherStatement.getText();
                    try { // support parameters for PsiElement only
                        ParametersService service = ServiceManager.getService(state.getProject(), ParametersService.class);
                        parameters = service.getParameters(cypherStatement);
                    } catch (Exception exception) {
                        sendParametersRetrievalErrorEvent(state.getMessageBus(), exception, state.getEditor());
                    }
                }
            }
        }

        Analytics.event("query-content", state.getCaret().hasSelection() ? CONTENT_FROM_SELECT_ACTION : CONTENT_FROM_CARET_ACTION);

        if (isNull(query)) {
            Notifier.error(QUERY_EXECUTION_ERROR_TITLE, NO_QUERY_SELECTED_MESSAGE);
            return null;
        }
        query = decorateQuery(query);

        return new ExecuteQueryPayload(query, parameters, state.getEditor());
    }

    class ActionState {

        private final Project project;
        private final Editor editor;
        private final PsiFile psiFile;
        private final VirtualFile virtualFile;

        public ActionState(AnActionEvent e) {
            this.project = getEventProject(e);
            this.editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);
            this.psiFile = e.getData(CommonDataKeys.PSI_FILE);
            this.virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        }

        public PsiFile getPsiFile() {
            return psiFile;
        }

        public VirtualFile getVirtualFile() {
            return virtualFile;
        }

        public MessageBus getMessageBus() {
            return project.getMessageBus();
        }

        public Caret getCaret() {
            return editor.getCaretModel().getPrimaryCaret();
        }

        public Project getProject() {
            return project;
        }

        public Editor getEditor() {
            return editor;
        }
    }


    @Override
    public void actionPerformed(AnActionEvent e) {
        Analytics.event("query", getQueryExecutionAction(e));
        ActionState state = new ActionState(e);

        if (isNull(state.getProject())) {
            Notifier.error(QUERY_EXECUTION_ERROR_TITLE, NO_PROJECT_PRESENT_MESSAGE);
            return;
        }
        if (isNull(state.getEditor())) {
            Notifier.error(QUERY_EXECUTION_ERROR_TITLE, NO_EDITOR_PRESENT_MESSAGE);
            return;
        }

        ExecuteQueryPayload executeQueryPayload = createQueryPayload(state);
        if (nonNull(executeQueryPayload)) {
            ConsoleToolWindow.ensureOpen(state.getProject());

            DataSourcesComponent dataSourcesComponent = state.getProject().getComponent(DataSourcesComponent.class);
            if (nonNull(state.getVirtualFile())) {
                String fileName = state.getVirtualFile().getName();
                if (fileName.startsWith(GraphConstants.BOUND_DATA_SOURCE_PREFIX)) {
                    Optional<? extends DataSourceApi> boundDataSource = dataSourcesComponent.getDataSourceContainer()
                            .findDataSource(NameUtil.extractDataSourceUUID(fileName));
                    if (boundDataSource.isPresent()) {
                        executeQuery(state.getMessageBus(), boundDataSource.get(), executeQueryPayload);
                        return;
                    }
                }
            }

            ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                    "Choose Data Source",
                    new ChooseDataSourceActionGroup(state.getMessageBus(), dataSourcesComponent, executeQueryPayload),
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

    private String getQueryExecutionAction(AnActionEvent e) {
        return e.getInputEvent() instanceof KeyEvent ? EXECUTE_WITH_SHORTCUT_ACTION : EXECUTE_WITH_MOUSE_ACTION;
    }

    private void executeQuery(MessageBus messageBus, DataSourceApi dataSource, ExecuteQueryPayload payload) {
        ExecuteQueryEvent executeQueryEvent = messageBus.syncPublisher(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC);
        executeQueryEvent.executeQuery(dataSource, payload);
    }

    protected String decorateQuery(String query) {
        return query;
    }

    private void sendParametersRetrievalErrorEvent(MessageBus messageBus, Exception exception, Editor editor) {
        QueryParametersRetrievalErrorEvent event = messageBus
                .syncPublisher(QueryParametersRetrievalErrorEvent.QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC);
        event.handleError(exception, editor);
    }
}
