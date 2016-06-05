package com.neueda.jetbrains.plugin.graphdb.visualization.settings;

import prefuse.action.ActionList;
import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

import static com.neueda.jetbrains.plugin.graphdb.visualization.settings.GraphGroups.EDGES;
import static com.neueda.jetbrains.plugin.graphdb.visualization.settings.GraphGroups.NODES;

public class ColorProvider {

    public static final String HOVER = "_hover";
    public static final int NODE_STROKE = ColorLib.gray(100);
    public static final int NODE_STROKE_HOVER = ColorLib.gray(50);
    public static final int NODE_FILL = ColorLib.rgb(200, 200, 255);
    public static final int NODE_FILL_HOVER = ColorLib.rgb(140, 140, 240);
    public static final int EDGE_STROKE = ColorLib.gray(100);
    public static final int EDGE_FILL = ColorLib.gray(100);

    public static ActionList getColors() {
        ActionList colors = new ActionList();

        ColorAction nStroke = new ColorAction(NODES, VisualItem.STROKECOLOR);
        nStroke.setDefaultColor(NODE_STROKE);
        nStroke.add(HOVER, NODE_STROKE_HOVER);
        colors.add(nStroke);

        ColorAction nFill = new ColorAction(NODES, VisualItem.FILLCOLOR);
        nFill.setDefaultColor(NODE_FILL);
        nFill.add(HOVER, NODE_FILL_HOVER);
        colors.add(nFill);

        ColorAction nEdges = new ColorAction(EDGES, VisualItem.STROKECOLOR);
        nEdges.setDefaultColor(EDGE_STROKE);
        colors.add(nEdges);

        ColorAction arrow = new ColorAction(EDGES, VisualItem.FILLCOLOR);
        nEdges.setDefaultColor(EDGE_FILL);
        colors.add(arrow);

        return colors;
    }
}
