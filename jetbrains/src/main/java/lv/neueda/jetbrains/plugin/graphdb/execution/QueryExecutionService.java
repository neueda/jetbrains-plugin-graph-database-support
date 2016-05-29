package lv.neueda.jetbrains.plugin.graphdb.execution;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.domain.DumbNode;
import lv.neueda.jetbrains.plugin.graphdb.domain.DumbRelationship;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;

import java.util.Random;

public class QueryExecutionService {

    private final VisualizationApi visualization;
    private final Random random;

    public QueryExecutionService(VisualizationApi visualization) {
        this.visualization = visualization;
        this.random = new Random();
    }

    public void executeQuery(String query) {
        visualization.clear();

        GraphNode node1 = getGraphNode("1");
        GraphNode node2 = getGraphNode("2");
        GraphNode node3 = getGraphNode("3");

        visualization.addRelation(new DumbRelationship("1", node1, node2));
        visualization.addRelation(new DumbRelationship("2", node1, node3));
        visualization.addRelation(new DumbRelationship("3", node2, node3));

        for (int i = 0; i < random.nextInt(10); i++) {
            visualization.addNode(getGraphNode("r" + i));
        }

        visualization.paint();
    }

    private GraphNode getGraphNode(String id) {
        GraphNode node = new DumbNode(id);
        visualization.addNode(node);
        return node;
    }
}
