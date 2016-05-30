package lv.neueda.jetbrains.plugin.graphdb.visualization;

import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphRelationship;

import javax.swing.JComponent;

public interface VisualizationApi {

    JComponent getCanvas();

    void addNode(GraphNode node);

    void addRelation(GraphRelationship relationship);

    void clear();

    void paint();
}
