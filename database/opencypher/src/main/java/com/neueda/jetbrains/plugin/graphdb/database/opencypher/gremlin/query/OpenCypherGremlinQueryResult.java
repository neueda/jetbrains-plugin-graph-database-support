package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.query;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryNotification;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryPlan;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltBuffer;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltRelationship;
import java.util.List;
import java.util.Optional;

public class OpenCypherGremlinQueryResult implements GraphQueryResult {

    private final long executionTimeMs;
    private final Neo4jBoltBuffer buffer;

    public OpenCypherGremlinQueryResult(long executionTimeMs, Neo4jBoltBuffer buffer) {
        this.executionTimeMs = executionTimeMs;
        this.buffer = buffer;

        List<GraphNode> nodes = buffer.getNodes();
        buffer.getRelationships().forEach((rel) -> {
            Neo4jBoltRelationship boltRel = (Neo4jBoltRelationship) rel;

            boltRel.setStartNode(findNodeById(nodes, boltRel.getStartNodeId()).orElse(null));
            boltRel.setEndNode(findNodeById(nodes, boltRel.getEndNodeId()).orElse(null));
        });
    }


    @Override
    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    @Override
    public String getResultSummary() {
        return "Execution time: " + getExecutionTimeMs() + "ms";
    }

    @Override
    public List<GraphQueryResultColumn> getColumns() {
        return buffer.getColumns();
    }

    @Override
    public List<GraphQueryResultRow> getRows() {
        return buffer.getRows();
    }

    @Override
    public List<GraphNode> getNodes() {
        return buffer.getNodes();
    }

    @Override
    public List<GraphRelationship> getRelationships() {
        return buffer.getRelationships();
    }

    @Override
    public List<GraphQueryNotification> getNotifications() {
        return buffer.getNotifications();
    }

    @Override
    public boolean hasPlan() {
        return buffer.hasPlan();
    }

    @Override
    public boolean isProfilePlan() {
        return buffer.isProfilePlan();
    }

    @Override
    public Optional<GraphQueryPlan> getPlan() {
        return buffer.getQueryPlan();
    }

    private Optional<GraphNode> findNodeById(List<GraphNode> nodes, String id) {
        return nodes.stream().filter((node) -> node.getId().equals(id)).findFirst();
    }
}
