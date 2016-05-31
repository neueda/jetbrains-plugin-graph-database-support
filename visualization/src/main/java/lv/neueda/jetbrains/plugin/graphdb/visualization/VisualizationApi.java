package lv.neueda.jetbrains.plugin.graphdb.visualization;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;
import lv.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

import javax.swing.JComponent;
import java.util.function.Consumer;

public interface VisualizationApi {

    JComponent getCanvas();

    void addNode(GraphNode node);

    void addRelation(GraphRelationship relationship);

    void clear();

    void paint();

    void stop();

    void addNodeListener(EventType type, Consumer<GraphNode> action);

    void addEdgeListener(EventType type, Consumer<GraphRelationship> action);
}
