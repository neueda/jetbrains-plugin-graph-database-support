package com.neueda.jetbrains.plugin.graphdb.visualization.settings;

import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import org.jetbrains.annotations.NotNull;
import prefuse.action.ActionList;
import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.GraphGroups.EDGES;
import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.GraphGroups.NODES;

public class ColorProvider {

    private static final int ORANGE = ColorLib.rgb(229, 60, 20);
    private static final int ORANGE_DARK = ColorLib.rgb(180, 40, 8);
    private static final int GREEN = ColorLib.rgb(132, 173, 74);
    private static final int GREEN_DARK = ColorLib.rgb(93, 148, 15);

    private static final int EDGE = GREEN;
    private static final int EDGE_HOVER = ORANGE_DARK;
    private static final int EDGE_HIGHLIGHT = ORANGE_DARK;

    private static final int NODE = GREEN_DARK;
    private static final int NODE_HOVER = ORANGE;
    private static final int NODE_HIGHLIGHT = ORANGE;

    private static final int NODE_STROKE = GREEN;
    private static final int NODE_STROKE_HOVER = ORANGE_DARK;
    private static final int NODE_STROKE_HIGHLIGHT = ORANGE_DARK;

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
        ColorAction arrow = new ColorAction(EDGES, VisualItem.FILLCOLOR, EDGE);
        arrow.add(VisualItem.HIGHLIGHT, EDGE_HIGHLIGHT);
        arrow.add(VisualItem.HOVER, EDGE_HOVER);

        return arrow;
    }

    @NotNull
    private static ColorAction getEdgeStroke(LookAndFeelService lookAndFeelService) {
        ColorAction nEdges = new ColorAction(EDGES, VisualItem.STROKECOLOR, EDGE);
        nEdges.add(VisualItem.HIGHLIGHT, EDGE_HIGHLIGHT);
        nEdges.add(VisualItem.HOVER, EDGE_HOVER);

        return nEdges;
    }

    @NotNull
    private static ColorAction getNodeFill(LookAndFeelService lookAndFeelService) {
        ColorAction nFill = new ColorAction(NODES, VisualItem.FILLCOLOR, NODE);
        nFill.add(VisualItem.HIGHLIGHT, NODE_HIGHLIGHT);
        nFill.add(VisualItem.HOVER, NODE_HOVER);

        return nFill;
    }

    @NotNull
    private static ColorAction getNodeStroke(LookAndFeelService lookAndFeelService) {
        ColorAction nStroke = new ColorAction(NODES, VisualItem.STROKECOLOR, NODE_STROKE);
        nStroke.add(VisualItem.HIGHLIGHT, NODE_STROKE_HIGHLIGHT);
        nStroke.add(VisualItem.HOVER, NODE_STROKE_HOVER);
        return nStroke;
    }
}
