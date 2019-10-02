package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.query;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OpenCypherGremlinQueryResult implements GraphQueryResult {
    private final long executionTimeMs;
    private List<GraphQueryResultColumn> columns;
    private List<GraphQueryResultRow> rows;
    private List<GraphNode> nodes;
    private List<GraphRelationship> relationships;

    public OpenCypherGremlinQueryResult(long executionTimeMs,
                                        List<GraphQueryResultColumn> columns,
                                        List<GraphQueryResultRow> rows,
                                        List<GraphNode> nodes,
                                        List<GraphRelationship> relationships) {
        this.executionTimeMs = executionTimeMs;
        this.columns = columns;
        this.rows = rows;
        this.nodes = nodes;
        this.relationships = relationships;
    }


    @Override
    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    @Override
    public String getResultSummary() {
        return "";
    }

    @Override
    public List<GraphQueryResultColumn> getColumns() {
        return columns;
    }

    @Override
    public List<GraphQueryResultRow> getRows() {
        return rows;
    }

    @Override
    public List<GraphNode> getNodes() {
        return nodes;
    }

    @Override
    public List<GraphRelationship> getRelationships() {
        return relationships;
    }

    @Override
    public List<GraphQueryNotification> getNotifications() {
        return Collections.emptyList();
    }

    @Override
    public boolean hasPlan() {
        return false;
    }

    @Override
    public boolean isProfilePlan() {
        return false;
    }

    @Override
    public Optional<GraphQueryPlan> getPlan() {
        return Optional.empty();
    }
}
