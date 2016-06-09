package com.neueda.jetbrains.plugin.graphdb.visualization.events;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import prefuse.visual.VisualItem;

import java.awt.event.MouseEvent;

@FunctionalInterface
public interface RelationshipCallback {
    void accept(GraphRelationship relationship, VisualItem item, MouseEvent e);
}
