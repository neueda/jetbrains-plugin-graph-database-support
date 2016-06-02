package lv.neueda.jetbrains.plugin.graphdb.visualization.events;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;
import prefuse.visual.VisualItem;

import java.awt.event.MouseEvent;

@FunctionalInterface
public interface RelationshipCallback {
    void accept(GraphRelationship relationship, VisualItem item, MouseEvent e);
}
