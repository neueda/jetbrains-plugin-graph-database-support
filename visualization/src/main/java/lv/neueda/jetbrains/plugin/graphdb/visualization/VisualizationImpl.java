package lv.neueda.jetbrains.plugin.graphdb.visualization;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.common.GraphRelationship;
import lv.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

import javax.swing.*;
import java.util.function.Consumer;

public class VisualizationImpl implements VisualizationApi {

    private GraphDisplay display = new GraphDisplay();

    @Override
    public void addNode(GraphNode node) {
        display.addNode(node);
    }

    @Override
    public void addRelation(GraphRelationship relationship) {
        display.addRelationship(relationship);
    }

    @Override
    public void clear() {
        display.removeAll();
    }

    @Override
    public void paint() {
        display.startLayout();
    }

    public void stop() {
        display.stopLayout();
    }

    public JComponent getCanvas() {
        return display;
    }

    public void addNodeListener(EventType type, Consumer<GraphNode> action) {
        display.addNodeListener(type, action);
    }

    public void addEdgeListener(EventType type, Consumer<GraphRelationship> action) {
        display.addEdgeListener(type, action);
    }
}
