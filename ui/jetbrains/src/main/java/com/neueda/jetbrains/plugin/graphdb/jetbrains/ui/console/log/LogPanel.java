package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.log;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryExecutionProcessEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.util.Notifier;

import java.awt.BorderLayout;

public class LogPanel {

    private ConsoleView log;

    public void initialize(ConsoleToolWindow consoleToolWindow, Project project) {
        MessageBus messageBus = project.getMessageBus();

        log = TextConsoleBuilderFactory.getInstance()
                .createBuilder(project)
                .getConsole();
        consoleToolWindow.getLogTab().add(log.getComponent(), BorderLayout.CENTER);

        messageBus.connect().subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
            @Override
            public void executionStarted(ExecuteQueryPayload payload) {
                info("Executing query: ");
                userInput(payload.getContent());
                newLine();
            }

            @Override
            public void resultReceived(GraphQueryResult result) {
                info(String.format("Query executed in %sms. %s", result.getExecutionTimeMs(), result.getResultSummary()));
                newLine();
            }

            @Override
            public void postResultReceived() {
            }

            @Override
            public void handleError(Exception exception) {
                String message = String.format("Error occurred: %s", exception.getMessage());
                error(message);
                newLine();

                Notifier.error("Query execution", message);
            }

            @Override
            public void executionCompleted() {
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

    public void newLine() {
        log.print("\n", ConsoleViewContentType.NORMAL_OUTPUT);
    }
}
