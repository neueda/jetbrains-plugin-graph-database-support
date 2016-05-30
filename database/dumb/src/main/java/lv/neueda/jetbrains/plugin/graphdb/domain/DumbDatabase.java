package lv.neueda.jetbrains.plugin.graphdb.domain;

import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphDatabase;
import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphQueryResult;

public class DumbDatabase implements GraphDatabase {

    private static int nodeIdCounter;
    private static int relationshipIdCounter;

    @Override
    public GraphQueryResult execute(String query) {
        DumbQueryResult queryResult = new DumbQueryResult();

        GraphNode node1 = createNode();
        GraphNode node2 = createNode();
        GraphNode node3 = createNode();

        queryResult
                .addNode(node1)
                .addNode(node2)
                .addNode(node3)
                .addRelationship(createRelationship(node1, node2))
                .addRelationship(createRelationship(node1, node3))
                .addRelationship(createRelationship(node2, node3));

        return queryResult;
    }

    public DumbNode createNode() {
        return new DumbNode(String.valueOf(++nodeIdCounter));
    }

    public DumbRelationship createRelationship(GraphNode node1, GraphNode node2) {
        return new DumbRelationship(String.valueOf(++relationshipIdCounter), node1, node2);
    }
}
