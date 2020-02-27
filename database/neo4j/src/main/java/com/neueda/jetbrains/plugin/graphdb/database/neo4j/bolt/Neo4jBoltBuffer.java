package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryNotification;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryPlan;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltQueryNotification;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltQueryPlan;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltQueryResultRow;
import org.neo4j.driver.summary.InputPosition;
import org.neo4j.driver.summary.Plan;
import org.neo4j.driver.summary.ResultSummary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Neo4jBoltBuffer {

    private List<GraphQueryResultColumn> columns;
    private List<GraphQueryResultRow> rows;
    private ResultSummary resultSummary;
    private List<GraphNode> nodes;
    private List<GraphRelationship> relationships;
    private List<GraphQueryNotification> notifications;

    public Neo4jBoltBuffer() {
        this.rows = new ArrayList<>();
    }

    public void addColumns(List<String> columns) {
        this.columns = columns.stream()
                .map(Neo4jBoltQueryResultColumn::new)
                .collect(toList());
    }

    public void addResultSummary(ResultSummary resultSummary) {
        this.resultSummary = resultSummary;
    }

    public void addRow(Map<String, Object> row) {
        this.rows.add(new Neo4jBoltQueryResultRow(row));
    }

    public List<GraphQueryResultColumn> getColumns() {
        return columns;
    }

    public List<GraphQueryResultRow> getRows() {
        return rows;
    }

    public List<GraphNode> getNodes() {
        if (nodes != null) {
            return nodes;
        }

        nodes = rows.stream()
                .flatMap(row -> row.getNodes().stream())
                .distinct()
                .collect(toList());

        return nodes;
    }

    public List<GraphRelationship> getRelationships() {
        if (relationships != null) {
            return relationships;
        }

        relationships = rows.stream()
                .flatMap(row -> row.getRelationships().stream())
                .distinct()
                .collect(toList());

        return relationships;
    }

    public ResultSummary getResultSummary() {
        return resultSummary;
    }

    public List<GraphQueryNotification> getNotifications() {
        if (resultSummary == null) {
            return Collections.emptyList();
        }

        if (notifications != null) {
            return notifications;
        }

        notifications = resultSummary.notifications().stream()
                .map(notification -> new Neo4jBoltQueryNotification(notification.title(),
                        notification.description(),
                        Optional.ofNullable(notification.position())
                                .map(InputPosition::offset)
                                .orElse(null)))
                .collect(toList());

        return notifications;
    }

    public boolean hasPlan() {
        return Optional.ofNullable(resultSummary)
                .map(ResultSummary::hasPlan)
                .orElse(false);
    }

    public boolean isProfilePlan() {
        return Optional.ofNullable(resultSummary)
                .map(ResultSummary::hasProfile)
                .orElse(false);
    }

    public Optional<GraphQueryPlan> getQueryPlan() {
        if (!hasPlan()) {
            return Optional.empty();
        }

        Plan plan = resultSummary.plan();
        return Optional.of(new Neo4jBoltQueryPlan(plan.operatorType(), getArguments(plan), plan.identifiers(),
                getPlanChildren(plan.children())));
    }

    private static List<Neo4jBoltQueryPlan> getPlanChildren(List<? extends Plan> childrenPlans) {
        return childrenPlans.stream()
                .map(plan -> new Neo4jBoltQueryPlan(plan.operatorType(), getArguments(plan), plan.identifiers(),
                        getPlanChildren(plan.children())))
                .collect(toList());
    }

    private static Map<String, Object> getArguments(Plan plan) {
        return plan.arguments().entrySet().stream()
                .collect(toMap(Map.Entry::getKey, e -> e.getValue().asObject()));
    }

}
