package com.neueda.jetbrains.plugin.graphdb.visualization.controls;

import prefuse.controls.NeighborHighlightControl;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

import java.awt.event.MouseEvent;

public class CustomNeighborHighlightControl extends NeighborHighlightControl {

    private NodeItem selectedNode;
    private EdgeItem selectedEdge;
    private NodeItem hovered;

    @Override
    public void itemClicked(VisualItem item, MouseEvent e) {
        if (item instanceof NodeItem) {
            NodeItem node = (NodeItem) item;

            if (selectedNode == node) return;

            unselectNode();
            selectNode(node);
        } else if (item instanceof EdgeItem) {
            EdgeItem edge = (EdgeItem) item;

            unselectEdge();
            selectEdge(edge);
        }
    }

    @Override
    public void itemEntered(VisualItem item, MouseEvent e) {
        if (item instanceof NodeItem) {
            NodeItem node = (NodeItem) item;

            hovered = node;
            setNeighborHighlight(hovered, true);
        }
    }

    @Override
    public void itemExited(VisualItem item, MouseEvent e) {
        if (item instanceof NodeItem) {
            if (hovered != null && hovered != selectedNode) {
                highlightNodeAndNeighbors(hovered, false);
                hovered = null;
                highlightNodeAndNeighbors(selectedNode, true);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        unselectNode();
        unselectEdge();
    }

    private void selectEdge(EdgeItem edge) {
        if (edge != null) {
            edge.setHighlighted(true);
            selectedEdge = edge;
        }
        unselectNode();
    }

    private void unselectEdge() {
        if (selectedEdge != null) {
            selectedEdge.setHighlighted(false);
            selectedEdge = null;
        }
    }

    private void selectNode(NodeItem node) {
        if (node != null) {
            highlightNodeAndNeighbors(node, true);
            selectedNode = node;
        }
        unselectEdge();
    }

    private void unselectNode() {
        if (selectedNode != null) {
            highlightNodeAndNeighbors(selectedNode, false);
            selectedNode = null;
        }
    }

    private void highlightNodeAndNeighbors(NodeItem node, boolean state) {
        if (node == null) return;

        setNeighborHighlight(node, state);
        node.setHighlighted(state);
    }
}
