package lv.neueda.jetbrains.plugin.graphdb.database.dumb;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphQueryResult;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;

import java.util.ArrayList;
import java.util.List;

public class DumbQueryResult implements GraphQueryResult {

    private List<GraphNode> nodes;
    private List<GraphRelationship> relationships;

    public DumbQueryResult() {
        nodes = new ArrayList<>();
        relationships = new ArrayList<>();
    }

    @Override
    public List<GraphNode> getNodes() {
        return nodes;
    }

    @Override
    public List<GraphRelationship> getRelationships() {
        return relationships;
    }

    public DumbQueryResult addNode(GraphNode node) {
        nodes.add(node);
        return this;
    }

    public DumbQueryResult addRelationship(GraphRelationship relationship) {
        relationships.add(relationship);
        return this;
    }
}
