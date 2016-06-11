package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event;

import com.intellij.util.messages.Topic;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;

public interface ShowQueryExecutionResultEvent {

    Topic<ShowQueryExecutionResultEvent> SHOW_QUERY_EXECUTION_RESULT_TOPIC =
            Topic.create("GraphDatabaseConsole.ShowQueryExecutionResultTopic", ShowQueryExecutionResultEvent.class);

    void preShowResult();

    void showResult(GraphQueryResult result);

    void postShowResult();

    void handleError(Exception exception);
}
