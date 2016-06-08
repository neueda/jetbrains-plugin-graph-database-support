package com.neueda.jetbrains.plugin.graphdb.actions.ui.console;

import com.intellij.util.messages.Topic;

public interface ToggleLayoutEvent {

    Topic<ToggleLayoutEvent> TOGGLE_LAYOUT_TOPIC = Topic.create("GraphDatabaseConsole.ToggleLayoutTopic", ToggleLayoutEvent.class);

    void toggleLayout();
}
