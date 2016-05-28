package lv.neueda.jetbrains.plugin.graphdb.visualization;

import lv.neueda.jetbrains.plugin.graphdb.visualization.domain.GraphNode;

public interface VisualizationApi {
    void addNode(GraphNode node);
    void addRelation(GraphNode start, GraphNode end);

    void clear();
    void paint();

    void run();
}
