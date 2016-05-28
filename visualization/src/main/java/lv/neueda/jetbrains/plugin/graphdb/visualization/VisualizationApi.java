package lv.neueda.jetbrains.plugin.graphdb.visualization;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.common.GraphRelationship;

public interface VisualizationApi {

    void addNode(GraphNode node);

    void addRelation(GraphRelationship relationship);

    void clear();

    void paint();

    void run();
}
