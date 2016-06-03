package com.neueda.jetbrains.plugin.graphdb.bus;

import com.intellij.util.messages.Topic;

public interface ExecuteQueryEvent {

    Topic<ExecuteQueryEvent> EXECUTE_QUERY_TOPIC = Topic.create("GraphDatabase.ExecuteQueryTopic", ExecuteQueryEvent.class);

    void executeQuery(ExecuteQueryPayload payload);
}
