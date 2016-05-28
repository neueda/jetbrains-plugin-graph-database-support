package lv.neueda.jetbrains.plugin.graphdb;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.domain.DumbNode;
import lv.neueda.jetbrains.plugin.graphdb.domain.DumbRelationship;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationImpl;

public class TestSimpleGraph {
    public static void main(String[] argv) {
        VisualizationImpl v = new VisualizationImpl();

        GraphNode node1 = getGraphNode(v, "1");
        GraphNode node2 = getGraphNode(v, "2");
        GraphNode node3 = getGraphNode(v, "3");

        v.addRelation(new DumbRelationship(node1, node2));
        v.addRelation(new DumbRelationship(node1, node3));
        v.addRelation(new DumbRelationship(node2, node3));

        v.run();
    }

    private static GraphNode getGraphNode(VisualizationImpl visualizationImpl, String id) {
        GraphNode node2 = new DumbNode(id);
        visualizationImpl.addNode(node2);
        return node2;
    }
}

