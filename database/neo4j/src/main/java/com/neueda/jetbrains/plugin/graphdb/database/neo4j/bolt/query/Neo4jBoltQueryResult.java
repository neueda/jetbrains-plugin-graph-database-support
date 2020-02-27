package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.query;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryNotification;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryPlan;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltBuffer;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltRelationship;
import org.neo4j.driver.summary.Notification;
import org.neo4j.driver.summary.Plan;
import org.neo4j.driver.summary.ProfiledPlan;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.summary.SummaryCounters;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class Neo4jBoltQueryResult implements GraphQueryResult {

    private final long executionTimeMs;
    private final Neo4jBoltBuffer buffer;

    public Neo4jBoltQueryResult(long executionTimeMs, Neo4jBoltBuffer buffer) {
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
        ResultSummary summary = buffer.getResultSummary();
        SummaryCounters counters = summary.counters();

        StringBuilder sb = new StringBuilder();
        sb.append(format("Query type: %s.\n", summary.queryType()));
        if (counters.containsUpdates()) {
            if (counters.nodesCreated() > 0) {
                sb.append(format("Nodes created: %s\n", counters.nodesCreated()));
            }
            if (counters.nodesDeleted() > 0) {
                sb.append(format("Nodes deleted: %s\n", counters.nodesDeleted()));
            }
            if (counters.labelsAdded() > 0) {
                sb.append(format("Labels added: %s\n", counters.labelsAdded()));
            }
            if (counters.labelsRemoved() > 0) {
                sb.append(format("Labels removed: %s\n", counters.labelsRemoved()));
            }
            if (counters.relationshipsCreated() > 0) {
                sb.append(format("Relationships created: %s\n", counters.relationshipsCreated()));
            }
            if (counters.relationshipsDeleted() > 0) {
                sb.append(format("Relationships deleted: %s\n", counters.relationshipsDeleted()));
            }
            if (counters.propertiesSet() > 0) {
                sb.append(format("Properties set: %s\n", counters.propertiesSet()));
            }
            if (counters.indexesAdded() > 0) {
                sb.append(format("Indexes added: %s\n", counters.indexesAdded()));
            }
            if (counters.indexesRemoved() > 0) {
                sb.append(format("Indexes removed: %s\n", counters.indexesRemoved()));
            }
            if (counters.constraintsAdded() > 0) {
                sb.append(format("Constrains added: %s\n", counters.constraintsAdded()));
            }
            if (counters.constraintsRemoved() > 0) {
                sb.append(format("Constrains removed: %s\n", counters.constraintsRemoved()));
            }
        }

        if (summary.hasProfile()) {
            sb.append("Profile:\n");
            profileToString(sb, summary.profile(), 1);
        } else if (summary.hasPlan()) {
            sb.append("Plan:\n");
            planToString(sb, summary.plan(), 1);
        }

        if (summary.notifications().size() > 0) {
            sb.append("Notifications:\n");
            for (Notification notification : summary.notifications()) {
                sb.append(format("[%s] %s(%s) - %s", notification.severity(),
                        notification.title(), notification.code(), notification.description()));
            }
        }
        return sb.toString();
    }

    private void planToString(StringBuilder sb, Plan plan, int depth) {
        for (Plan childPlan : plan.children()) {
            planToString(sb, childPlan, depth + 1);
        }

        String line = repeat("-", depth);
        sb.append(line)
                .append(format("> %s {identifiers: %s; arguments: %s}\n", plan.operatorType(), plan.identifiers(), plan.arguments()));
    }

    private void profileToString(StringBuilder sb, ProfiledPlan profile, int depth) {
        for (ProfiledPlan childProfile : profile.children()) {
            profileToString(sb, childProfile, depth + 1);
        }

        String line = repeat("-", depth);
        sb.append(line)
                .append(format("> %s[records: %s; dbHits: %s] {identifiers: %s; arguments: %s}\n",
                        profile.operatorType(), profile.records(), profile.dbHits(), profile.identifiers(), profile.arguments()));
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

    private String repeat(String part, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(part);
        }
        return sb.toString();
    }
}
