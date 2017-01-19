package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event;

import com.intellij.util.messages.Topic;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;

public interface QueryPlanEvent {

    Topic<QueryPlanEvent> QUERY_PLAN_EVENT = Topic.create("GraphDatabaseConsole.QueryPlanEvent", QueryPlanEvent.class);

    void queryPlanReceived(String query, GraphQueryResult result);
}
