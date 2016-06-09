package com.neueda.jetbrains.plugin.graphdb.visualization.events;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import prefuse.visual.VisualItem;

import java.awt.event.MouseEvent;

@FunctionalInterface
public interface NodeCallback {
    void accept(GraphNode node, VisualItem item, MouseEvent e);
}
