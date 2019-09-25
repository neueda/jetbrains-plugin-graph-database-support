package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OpenCypherGremlinGraphQueryResultRow implements GraphQueryResultRow {
    private Map<String, Object> results;
    private Set<GraphNode> nodes;
    private Set<GraphRelationship> relationships;

    public OpenCypherGremlinGraphQueryResultRow(Map<String, Object> results, Set<GraphNode> nodes, Set<GraphRelationship> relationships) {
        this.results = results;
        this.nodes = nodes;
        this.relationships = relationships;
    }

    @Override
    public Object getValue(GraphQueryResultColumn column) {
        return results.get(column.getName());
    }

    @Override
    public List<GraphNode> getNodes() {
        return new ArrayList<>(nodes);
    }

    @Override
    public List<GraphRelationship> getRelationships() {
        return new ArrayList<>(relationships);
    }
}
