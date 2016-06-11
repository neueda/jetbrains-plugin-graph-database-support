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
        Color edgeStrokeColor = lookAndFeelService.getEdgeStrokeColor();
        int edgeStroke = ColorLib.color(edgeStrokeColor);
        nEdges.setDefaultColor(edgeStroke);
        colors.add(nEdges);

        ColorAction arrow = new ColorAction(EDGES, VisualItem.FILLCOLOR);
        int edgeFill = ColorLib.color(lookAndFeelService.getEdgeFillColor());
        arrow.setDefaultColor(edgeFill);
        colors.add(arrow);

        return colors;
    }

    private static void print(Color c) {
        System.out.println(c.getRed() + ", " + c.getGreen() + ", " +  c.getBlue());
    }
}
