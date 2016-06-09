package com.neueda.jetbrains.plugin.graphdb.visualization.listeners;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.RelationshipCallback;
import prefuse.controls.ControlAdapter;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;

import java.awt.event.MouseEvent;
import java.util.Map;

public class RelationshipListener extends ControlAdapter {

    private EventType type;
    private RelationshipCallback callback;
    private Map<String, GraphRelationship> graphRelationshipMap;

    public RelationshipListener(EventType type, RelationshipCallback callback, Map<String, GraphRelationship> graphRelationshipMap) {
        this.type = type;
        this.callback = callback;
        this.graphRelationshipMap = graphRelationshipMap;
    }

    @Override
    public void itemEntered(VisualItem item, MouseEvent e) {
        if (type == EventType.HOVER_START && item instanceof EdgeItem) {
            callback.accept(graphRelationshipMap.get(item.get("id")), item, e);
        }
    }

    @Override
    public void itemExited(VisualItem item, MouseEvent e) {
        if (type == EventType.HOVER_END && item instanceof EdgeItem) {
            callback.accept(graphRelationshipMap.get(item.get("id")), item, e);
        }
    }

    @Override
    public void itemClicked(VisualItem item, MouseEvent e) {
        if (type == EventType.CLICK && item instanceof EdgeItem) {
            callback.accept(graphRelationshipMap.get(item.get("id")), item, e);
        }
    }
}
