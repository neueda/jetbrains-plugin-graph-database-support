package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event;

import com.intellij.util.messages.Topic;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;

public interface QueryExecutionProcessEvent {

    Topic<QueryExecutionProcessEvent> QUERY_EXECUTION_PROCESS_TOPIC =
            Topic.create("GraphDatabaseConsole.QueryExecutionProcessTopic", QueryExecutionProcessEvent.class);

    void preResultReceived();

    void resultReceived(GraphQueryResult result);

    void postResultReceived();

    void handleError(Exception exception);
}
