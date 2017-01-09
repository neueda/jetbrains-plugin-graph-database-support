package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.log;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryExecutionProcessEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.MetadataRetrieveEvent;

import java.awt.*;

public class LogPanel implements Disposable {

    private ConsoleView log;

    public void initialize(GraphConsoleView graphConsoleView, Project project) {
        MessageBus messageBus = project.getMessageBus();

        log = TextConsoleBuilderFactory.getInstance()
                .createBuilder(project)
                .getConsole();
        Disposer.register(graphConsoleView, log);
        graphConsoleView.getLogTab().add(log.getComponent(), BorderLayout.CENTER);

        messageBus.connect().subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
            @Override
            public void executionStarted(ExecuteQueryPayload payload) {
                info("Executing query: ");
                userInput(payload.getContent());
                newLine();
            }

            @Override
            public void resultReceived(ExecuteQueryPayload payload, GraphQueryResult result) {
                info(String.format("Query executed in %sms. %s", result.getExecutionTimeMs(), result.getResultSummary()));
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
            public void metadataRefreshSucceed(DataSourceApi nodeDataSource) {
                info(String.format("DataSource[%s] - metadata refreshed successfully!", nodeDataSource.getName()));
                newLine();
                newLine();
            }

            @Override
            public void metadataRefreshFailed(DataSourceApi nodeDataSource, Exception exception) {
                error(String.format("DataSource[%s] - metadata refresh failed. Reason: ", nodeDataSource.getName()));
                printException(exception);
                newLine();
            }
        });
    }

    public void userInput(String message) {
        log.print(message, ConsoleViewContentType.USER_INPUT);
    }

    public void info(String message) {
        log.print(message, ConsoleViewContentType.NORMAL_OUTPUT);
    }

    public void error(String message) {
        log.print(message, ConsoleViewContentType.ERROR_OUTPUT);
    }

    public void printException(Exception exception) {
        if (exception.getMessage() != null) {
            error(exception.getMessage());
        } else {
            error(exception.toString());
        }
        newLine();

        Throwable cause = exception.getCause();
        while (cause != null) {
            error(cause.getMessage());
            newLine();
            cause = cause.getCause();
        }
    }

    public void newLine() {
        log.print("\n", ConsoleViewContentType.NORMAL_OUTPUT);
    }

    @Override
    public void dispose() {
        log.dispose();
    }
}
