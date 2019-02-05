package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event;

import com.intellij.util.messages.Topic;

public interface CopyQueryOutputEvent {

    Topic<CopyQueryOutputEvent> COPY_QUERY_OUTPUT_TOPIC = Topic.create("GraphDatabaseConsole.CopyQueryOutputTopic", CopyQueryOutputEvent.class);

    void copyQueryOutputToClipboard();
}
