package com.neueda.jetbrains.plugin.graphdb.visualization.settings;

import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import prefuse.action.ActionList;
import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

import java.awt.*;

import static com.neueda.jetbrains.plugin.graphdb.visualization.settings.GraphGroups.EDGES;
import static com.neueda.jetbrains.plugin.graphdb.visualization.settings.GraphGroups.NODES;

public class ColorProvider {

    public static final String HOVER = "_hover";
    public static final int NODE_STROKE = ColorLib.gray(100);
    public static final int NODE_STROKE_HOVER = ColorLib.gray(50);
    public static final int NODE_FILL = ColorLib.rgb(200, 200, 255);
    public static final int NODE_FILL_HOVER = ColorLib.rgb(140, 140, 240);
    public static final int EDGE_STROKE = ColorLib.color(Color.YELLOW);
    public static final int EDGE_FILL = ColorLib.gray(100);

    public static ActionList getColors(LookAndFeelService lookAndFeelService) {
        ActionList colors = new ActionList();

        ColorAction nStroke = new ColorAction(NODES, VisualItem.STROKECOLOR);
        nStroke.setDefaultColor(ColorLib.color(lookAndFeelService.getNodeStrokeColor()));
        nStroke.add(HOVER, ColorLib.color(lookAndFeelService.getNodeStrokeHoverColor()));
        colors.add(nStroke);

        ColorAction nFill = new ColorAction(NODES, VisualItem.FILLCOLOR);
        nFill.setDefaultColor(ColorLib.color(lookAndFeelService.getNodeFillColor()));
        nFill.add(HOVER, ColorLib.color(lookAndFeelService.getNodeFillHoverColor()));
        colors.add(nFill);

        ColorAction nEdges = new ColorAction(EDGES, VisualItem.STROKECOLOR);
        nEdges.setDefaultColor(ColorLib.color(lookAndFeelService.getEdgeStrokeColor()));
        colors.add(nEdges);

        ColorAction arrow = new ColorAction(EDGES, VisualItem.FILLCOLOR);
        nEdges.setDefaultColor(ColorLib.color(lookAndFeelService.getEdgeFillColor()));
        colors.add(arrow);

        return colors;
    }
}
