package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltQueryResultRow;
import org.neo4j.driver.v1.summary.ResultSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Neo4jBoltBuffer {

    private List<GraphQueryResultColumn> columns;
    private List<GraphQueryResultRow> rows;
    private ResultSummary resultSummary;
    private List<GraphNode> nodes;
    private List<GraphRelationship> relationships;

    public Neo4jBoltBuffer() {
        this.rows = new ArrayList<>();
    }

    public void addColumns(List<String> columns) {
        this.columns = columns.stream()
                .map(Neo4jBoltQueryResultColumn::new)
                .collect(Collectors.toList());
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
                .collect(Collectors.toList());

        return nodes;
    }

    public List<GraphRelationship> getRelationships() {
        if (relationships != null) {
            return relationships;
        }

        relationships = rows.stream()
                .flatMap(row -> row.getRelationships().stream())
                .distinct()
                .collect(Collectors.toList());

        return relationships;
    }

    public ResultSummary getResultSummary() {
        return resultSummary;
    }
}
