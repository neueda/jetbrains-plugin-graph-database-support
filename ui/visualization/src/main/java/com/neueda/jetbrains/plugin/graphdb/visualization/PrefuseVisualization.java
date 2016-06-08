package com.neueda.jetbrains.plugin.graphdb.visualization;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.NodeCallback;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.RelationshipCallback;

import javax.swing.*;

public class PrefuseVisualization implements VisualizationApi {

    private GraphDisplay display = new GraphDisplay();
    private boolean layoutRunning = false;

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
        display.clearGraph();
    }

    @Override
    public void paint() {
        display.startLayout();
        layoutRunning = true;
    }

    @Override
    public void stop() {
        display.stopLayout();
        layoutRunning = false;
    }

    @Override
    public void toggleLayout() {
        if (layoutRunning) {
            stop();
        } else {
            paint();
        }
    }

    @Override
    public JComponent getCanvas() {
        return display;
    }

    @Override
    public void addNodeListener(EventType type, NodeCallback action) {
        display.addNodeListener(type, action);
    }

    @Override
    public void addEdgeListener(EventType type, RelationshipCallback action) {
        display.addEdgeListener(type, action);
    }
}
