package lv.neueda.jetbrains.plugin.graphdb.domain;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;

public class DumbDatabase {

    private static int nodeIdCounter;
    private static int relationshipIdCounter;

    public DumbNode createNode() {
        return new DumbNode(String.valueOf(++nodeIdCounter));
    }

    public DumbRelationship createRelationship(GraphNode node1, GraphNode node2) {
        return new DumbRelationship(String.valueOf(++relationshipIdCounter), node1, node2);
    }
}
