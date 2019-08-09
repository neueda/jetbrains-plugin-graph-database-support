package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event;

import com.intellij.util.messages.Topic;

public interface OpenTabEvent {

    Topic<OpenTabEvent> OPEN_TAB_TOPIC = Topic.create("GraphDatabaseConsole.OpenTabTopic", OpenTabEvent.class);

    void openTab(String graph);
}
