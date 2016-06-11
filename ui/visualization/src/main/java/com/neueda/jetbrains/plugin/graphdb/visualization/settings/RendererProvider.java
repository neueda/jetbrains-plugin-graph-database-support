package com.neueda.jetbrains.plugin.graphdb.visualization.settings;

import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.render.ShapeRenderer;

import static prefuse.Constants.EDGE_TYPE_LINE;

public class RendererProvider {

    private static final String LABEL_FIELD = "id";
    private static final int NODE_DIAMETER = 35;

    private static final double EDGE_THICKNESS_COEFFICIENT = 4;
    private static final double ARROW_THICKNESS_COEFFICIENT = 2;

    public static LabelRenderer labelRenderer() {
        LabelRenderer labelRenderer = new LabelRenderer(LABEL_FIELD);
        labelRenderer.setMaxTextWidth(NODE_DIAMETER);

        return labelRenderer;
    }

    public static ShapeRenderer nodeRenderer() {
        ShapeRenderer nodeRenderer = new ShapeRenderer();
        nodeRenderer.setBaseSize(NODE_DIAMETER);

        return nodeRenderer;
    }

    public static EdgeRenderer edgeRenderer() {
        EdgeRenderer edgeRenderer = new EdgeRenderer(EDGE_TYPE_LINE);
        edgeRenderer.setDefaultLineWidth(EDGE_THICKNESS_COEFFICIENT);
        edgeRenderer.setArrowHeadSize((int)(8 * ARROW_THICKNESS_COEFFICIENT), (int)(12 * ARROW_THICKNESS_COEFFICIENT));

        return edgeRenderer;
    }
}
