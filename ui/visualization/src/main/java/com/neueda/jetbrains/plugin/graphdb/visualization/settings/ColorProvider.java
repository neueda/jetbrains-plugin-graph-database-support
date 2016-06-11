package com.neueda.jetbrains.plugin.graphdb.visualization.settings;

import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import org.jetbrains.annotations.NotNull;
import prefuse.action.ActionList;
import prefuse.action.assignment.ColorAction;
import prefuse.visual.VisualItem;

import java.awt.*;

import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.GraphGroups.EDGES;
import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.GraphGroups.NODES;
import static prefuse.util.ColorLib.color;

public class ColorProvider {

    public static final String HOVER = "_hover";

    public static ActionList colors(LookAndFeelService lookAndFeelService) {
        ActionList colors = new ActionList();

        colors.add(getNodeStroke(lookAndFeelService));
        colors.add(getNodeFill(lookAndFeelService));
        colors.add(getEdgeStroke(lookAndFeelService));
        colors.add(getEdgeFill(lookAndFeelService));

        return colors;
    }

    @NotNull
    private static ColorAction getEdgeFill(LookAndFeelService lookAndFeelService) {
        ColorAction arrow = new ColorAction(EDGES, VisualItem.FILLCOLOR);
        arrow.setDefaultColor(color(lookAndFeelService.getEdgeFillColor()));
        return arrow;
    }

    @NotNull
    private static ColorAction getEdgeStroke(LookAndFeelService lookAndFeelService) {
        ColorAction nEdges = new ColorAction(EDGES, VisualItem.STROKECOLOR);
        nEdges.setDefaultColor(color(lookAndFeelService.getEdgeStrokeColor()));
        return nEdges;
    }

    @NotNull
    private static ColorAction getNodeFill(LookAndFeelService lookAndFeelService) {
        ColorAction nFill = new ColorAction(NODES, VisualItem.FILLCOLOR);
        nFill.setDefaultColor(color(lookAndFeelService.getNodeFillColor()));
        nFill.add(HOVER, color(lookAndFeelService.getNodeFillHoverColor()));
        return nFill;
    }

    @NotNull
    private static ColorAction getNodeStroke(LookAndFeelService lookAndFeelService) {
        ColorAction nStroke = new ColorAction(NODES, VisualItem.STROKECOLOR);
        nStroke.setDefaultColor(color(lookAndFeelService.getNodeStrokeColor()));
        nStroke.add(HOVER, color(lookAndFeelService.getNodeStrokeHoverColor()));
        return nStroke;
    }
}
