package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.util.messages.Topic;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;

public interface ExecuteQueryEvent {

    Topic<ExecuteQueryEvent> EXECUTE_QUERY_TOPIC = Topic.create("GraphDatabase.ExecuteQueryTopic", ExecuteQueryEvent.class);

    void executeQuery(DataSourceApi dataSource, ExecuteQueryPayload payload);
}
