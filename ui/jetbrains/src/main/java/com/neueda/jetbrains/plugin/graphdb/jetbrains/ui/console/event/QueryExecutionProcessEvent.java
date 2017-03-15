package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event;

import com.intellij.util.messages.Topic;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;

public interface QueryExecutionProcessEvent {

    Topic<QueryExecutionProcessEvent> QUERY_EXECUTION_PROCESS_TOPIC =
            Topic.create("GraphDatabaseConsole.QueryExecutionProcessTopic", QueryExecutionProcessEvent.class);

    void executionStarted(DataSourceApi dataSource, ExecuteQueryPayload payload);

    void resultReceived(ExecuteQueryPayload payload, GraphQueryResult result);

    void postResultReceived(ExecuteQueryPayload payload);

    void handleError(ExecuteQueryPayload payload, Exception exception);

    void executionCompleted(ExecuteQueryPayload payload);
}
