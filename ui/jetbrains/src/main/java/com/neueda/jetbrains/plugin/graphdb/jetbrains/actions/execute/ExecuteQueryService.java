package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.google.common.base.Preconditions;
import com.intellij.openapi.actionSystem.ActionButtonComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
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

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class ExecuteQueryService {

    private static final String QUERY_EXECUTION_ERROR_TITLE = "Query execution error";
    private static final String NO_PROJECT_PRESENT_MESSAGE = "No project present.";
    private static final String NO_EDITOR_PRESENT_MESSAGE = "No editor present.";
    private static final String NO_QUERY_SELECTED_MESSAGE = "No query selected";

    private static final String EXECUTE_WITH_SHORTCUT_ACTION = "executeWithShortcut";
    private static final String EXECUTE_WITH_MOUSE_ACTION = "executeWithMouse";

    private static ExecuteQueryService instance;

    private ExecuteQueryService() {
    }

    public static synchronized ExecuteQueryService getInstance() {
        if (isNull(instance)) {
            instance = new ExecuteQueryService();
        }

        return instance;
    }

    public void executeQuery(QueryActionWrapper action, PsiElement element) {
        Map<String, Object> parameters = Collections.emptyMap();
        try {
            ParametersService service = ServiceManager.getService(action.getProject(), ParametersService.class);
            parameters = service.getParameters(element);
        } catch (Exception exception) {
            sendParametersRetrievalErrorEvent(action.getProjectMessageBus(), exception, action.getEditor());
        }

        executeQuery(action, element.getText(), parameters);
    }

    private void sendParametersRetrievalErrorEvent(MessageBus messageBus, Exception exception, Editor editor) {
        QueryParametersRetrievalErrorEvent event = messageBus
                .syncPublisher(QueryParametersRetrievalErrorEvent.QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC);
        event.handleError(exception, editor);
    }

    public void executeQuery(QueryActionWrapper action, String query) {
        executeQuery(action, query, Collections.emptyMap());
    }

    private void executeQuery(QueryActionWrapper action, String query, Map<String, Object> queryParameters) {
        Preconditions.checkNotNull(action, "'action' is undefined'");
        Preconditions.checkNotNull(queryParameters, "'queryParameters' are undefined");

        Analytics.event("query", getQueryExecutionAction(action.getInputEvent()));

        try {
            validateArguments(action, query);

            Project project = Preconditions.checkNotNull(action.getProject(), "action project is undefined");
            ConsoleToolWindow.ensureOpen(project);

            DataSourcesComponent dataSourcesComponent = getDataSourceComponent(project);
            ExecuteQueryPayload queryPayload = new ExecuteQueryPayload(query, queryParameters, action.getEditor());
            executeQueryIfPossible(action, dataSourcesComponent, queryPayload);
        } catch (IllegalStateException e) {
            Notifier.error(QUERY_EXECUTION_ERROR_TITLE, e.getMessage());
        }
    }

    private void validateArguments(QueryActionWrapper action, String query) throws IllegalStateException {
        if (isNull(query)) {
            throw new IllegalStateException(NO_QUERY_SELECTED_MESSAGE);
        } else if (isNull(action.getProject())) {
            throw new IllegalStateException(NO_PROJECT_PRESENT_MESSAGE);
        } else if (isNull(action.getEditor())) {
            throw new IllegalStateException(NO_EDITOR_PRESENT_MESSAGE);
        }
    }

    private DataSourcesComponent getDataSourceComponent(Project project) {
        return project.getComponent(DataSourcesComponent.class);
    }

    private void executeQueryIfPossible(QueryActionWrapper action, DataSourcesComponent dataSourcesComponent, ExecuteQueryPayload executeQueryPayload) {
        PsiFile virtualFile = action.getPsiFile();
        if (nonNull(virtualFile)) {
            if (isBoundDataSourceFile(virtualFile)) {
                Optional<? extends DataSourceApi> boundDataSource = findBoundDataSource(dataSourcesComponent, virtualFile.getName());
                boundDataSource.ifPresent(dataSourceApi -> executeQuery(action.getProjectMessageBus(), dataSourceApi, executeQueryPayload));
            } else {
                createAndShowListPopup(action, dataSourcesComponent, executeQueryPayload);
            }
        }
    }

    private String getQueryExecutionAction(InputEvent inputEvent) {
        return inputEvent instanceof KeyEvent ? EXECUTE_WITH_SHORTCUT_ACTION : EXECUTE_WITH_MOUSE_ACTION;
    }

    private boolean isBoundDataSourceFile(PsiFile virtualFile) {
        String fileName = virtualFile.getName();
        return fileName.startsWith(GraphConstants.BOUND_DATA_SOURCE_PREFIX);
    }

    private Optional<DataSourceApi> findBoundDataSource(DataSourcesComponent dataSourcesComponent, String virtualFile) {
        String dataSourceUUID = NameUtil.extractDataSourceUUID(virtualFile);
        return dataSourcesComponent
                .getDataSourceContainer()
                .findDataSource(dataSourceUUID);
    }

    public void executeQuery(MessageBus messageBus, DataSourceApi dataSource, ExecuteQueryPayload payload) {
        ExecuteQueryEvent executeQueryEvent = messageBus.syncPublisher(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC);
        executeQueryEvent.executeQuery(dataSource, payload);
    }

    private void createAndShowListPopup(QueryActionWrapper action, DataSourcesComponent dataSourcesComponent, ExecuteQueryPayload queryPayload) {
        ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                "Choose Data Source",
                new ChooseDataSourceActionGroup(action.getProjectMessageBus(), dataSourcesComponent, queryPayload),
                action.getDataContext(),
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                false
        );

        Component eventComponent = action.getInputEvent().getComponent();
        if (eventComponent instanceof ActionButtonComponent) {
            popup.showUnderneathOf(eventComponent);
        } else {
            popup.showInBestPositionFor(action.getDataContext());
        }
    }

}
