package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event;

import com.intellij.openapi.editor.Editor;
import com.intellij.util.messages.Topic;

public interface QueryParametersRetrievalErrorEvent {

    Topic<QueryParametersRetrievalErrorEvent> QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC =
            Topic.create("GraphDatabaseConsole.QueryParametersRetrievalErrorEventTopic", QueryParametersRetrievalErrorEvent.class);

    String PARAMS_ERROR_COMMON_MSG = "Failed to retrieve query parameters";
    void handleError(Exception exception, Editor editor);

}
