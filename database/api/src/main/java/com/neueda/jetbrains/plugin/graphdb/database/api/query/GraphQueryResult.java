package com.neueda.jetbrains.plugin.graphdb.database.api.query;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;

import java.util.List;

public interface GraphQueryResult {

    long getExecutionTimeMs();

    String getResultSummary();

    List<GraphQueryResultColumn> getColumns();

    List<GraphQueryResultRow> getRows();

    List<GraphNode> getNodes();

    List<GraphRelationship> getRelationships();
}
