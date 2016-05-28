package lv.neueda.jetbrains.plugin.graphdb.event;

import com.intellij.util.messages.Topic;

public interface ExecuteQueryEvent {
    Topic<ExecuteQueryEvent> EXECUTE_QUERY_TOPIC = Topic.create("GraphDatabase.ExecuteQueryTopic", ExecuteQueryEvent.class);

    void execute(String query);
}
