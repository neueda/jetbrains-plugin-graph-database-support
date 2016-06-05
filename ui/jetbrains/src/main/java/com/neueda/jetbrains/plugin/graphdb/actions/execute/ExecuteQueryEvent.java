package com.neueda.jetbrains.plugin.graphdb.actions.execute;

import com.intellij.util.messages.Topic;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSource;

public interface ExecuteQueryEvent {

    Topic<ExecuteQueryEvent> EXECUTE_QUERY_TOPIC = Topic.create("GraphDatabase.ExecuteQueryTopic", ExecuteQueryEvent.class);

    void executeQuery(DataSource dataSource, ExecuteQueryPayload payload);
}
