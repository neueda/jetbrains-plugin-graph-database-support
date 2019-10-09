package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.log;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.IconButton;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.ui.JBUI;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.exceptions.ExceptionErrorMessages;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.exceptions.OpenCypherGremlinException;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryExecutionProcessEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.MetadataRetrieveEvent;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.exceptions.ExceptionWrapper.*;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent.*;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.DataSourceDialog.*;

public class LogPanel implements Disposable {
    private static final String SHOW_DETAILS = "Details...";

    private ConsoleView log;

    public void initialize(GraphConsoleView graphConsoleView, Project project) {
        MessageBus messageBus = project.getMessageBus();

        log = TextConsoleBuilderFactory.getInstance()
                .createBuilder(project)
                .getConsole();
        log.addMessageFilter(new GoToTabFilter(log));

        Disposer.register(graphConsoleView, log);
        graphConsoleView.getLogTab().add(log.getComponent(), BorderLayout.CENTER);

        messageBus.connect().subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
            @Override
            public void executionStarted(DataSourceApi dataSource, ExecuteQueryPayload payload) {
                if (payload.getFileName().isPresent()) {
                    info("Executing all queries in a file: ");
                    newLine();
                    userInput(payload.getFileName().get());
                } else {
                    info("Executing query: ");
                    newLine();
                    userInput(payload.getQueries().get(0));
                }
                newLine();
                if (!payload.getParameters().isEmpty()) {
                    info("With parameters: ");
                    newLine();
                    printParametersMap(payload.getParameters());
                }
            }

            @Override
            public void resultReceived(ExecuteQueryPayload payload, GraphQueryResult result) {
                info(String.format("Query executed in %sms. %s", result.getExecutionTimeMs(), result.getResultSummary()));
                if (result.getRows().isEmpty()) {
                    info("No results.");
                } else if (result.getNodes().isEmpty()) {
                    info(String.format("Got %s rows. View results: %s",
                            result.getRows().size(),
                            GoToTabFilter.TABLE_TAB_LINK));
                } else {
                    info(String.format("Got %s rows. View results: %s, %s",
                            result.getRows().size(),
                            GoToTabFilter.GRAPH_TAB_LINK,
                            GoToTabFilter.TABLE_TAB_LINK));
                }
                newLine();
            }

            @Override
            public void postResultReceived(ExecuteQueryPayload payload) {
            }

            @Override
            public void handleError(ExecuteQueryPayload payload, Exception exception) {
                error("Error occurred: ");
                printException(exception);
            }

            @Override
            public void executionCompleted(ExecuteQueryPayload payload) {
                newLine();
            }
        });

        messageBus.connect().subscribe(MetadataRetrieveEvent.METADATA_RETRIEVE_EVENT, new MetadataRetrieveEvent() {
            @Override
            public void startMetadataRefresh(DataSourceApi nodeDataSource) {
                info(String.format("DataSource[%s] - refreshing metadata...", nodeDataSource.getName()));
                newLine();
            }

            @Override
            public void metadataRefreshSucceed(DataSourceApi nodeDataSource, DataSourceMetadata metadata) {
                info(String.format("DataSource[%s] - metadata refreshed successfully!", nodeDataSource.getName()));
                newLine();
                newLine();
            }

            @Override
            public void metadataRefreshFailed(DataSourceApi nodeDataSource, Exception exception) {
                String prefix = String.format("DataSource[%s] - metadata refresh failed. Reason: ", nodeDataSource.getName());
                error(prefix);
                printException(exception);
                newLine();
            }
        });

        messageBus.connect().subscribe(QueryParametersRetrievalErrorEvent.QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC,
                (exception, editor) -> {
                    error(String.format("%s ", PARAMS_ERROR_COMMON_MSG));
                    printException(exception);
                });
    }

    private void userInput(String message) {
        log.print(message, ConsoleViewContentType.USER_INPUT);
    }

    private void printParametersMap(Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String message = String.format("%s: %s", entry.getKey(), entry.getValue());
            log.print(message, ConsoleViewContentType.USER_INPUT);
            newLine();
        }
    }

    private void info(String message) {
        log.print(message, ConsoleViewContentType.NORMAL_OUTPUT);
    }

    public void error(@Nullable String message) {
        if (message != null) {
            log.print(message, ConsoleViewContentType.ERROR_OUTPUT);
        }
    }

    private String printException(Exception exception) {
        String errorMessage;
        if (exception.getMessage() != null) {
            errorMessage = exception.getMessage();
        } else {
            errorMessage = exception.toString();
        }
        error(errorMessage);
        String newLine = System.lineSeparator();
        String details = getCause(exception) + newLine + getStackTrace(exception);
        log.printHyperlink(" " + SHOW_DETAILS, p -> showPopup("Error details", details, exception));
        newLine();
        return errorMessage;
    }

    private void newLine() {
        log.print("\n", ConsoleViewContentType.NORMAL_OUTPUT);
    }

    private void showPopup(String title, String details, Exception exception) {
        JPanel popupPanel = new JPanel(new BorderLayout());
        popupPanel.setBorder(JBUI.Borders.empty(THICKNESS));

        JTextArea exceptionDetails = new JTextArea();
        exceptionDetails.setLineWrap(false);
        exceptionDetails.append(details);
        JLabel jLabel = new JLabel(exception.getMessage(), AllIcons.Process.State.RedExcl, JLabel.LEFT);

        JBScrollPane scrollPane = new JBScrollPane(exceptionDetails);
        scrollPane.setPreferredSize(new Dimension(-1, HEIGHT));
        popupPanel.add(jLabel, BorderLayout.NORTH);
        popupPanel.add(scrollPane, BorderLayout.CENTER);
        String gremlinTranslationWarning = exception instanceof OpenCypherGremlinException ? ExceptionErrorMessages.SYNTAX_WARNING.getDescription() : "";

        JBPopupFactory.getInstance()
                .createComponentPopupBuilder(
                        popupPanel,
                        log.getComponent())
                .setTitle(title)
                .setAdText(gremlinTranslationWarning)
                .setResizable(true)
                .setMovable(true)
                .setCancelButton(new IconButton("Close", AllIcons.Actions.Close, AllIcons.Actions.CloseHovered))
                .createPopup()
                .showInFocusCenter();
    }

    @Override
    public void dispose() {
        log.dispose();
    }
}
