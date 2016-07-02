package com.neueda.jetbrains.plugin.graphdb.visualization.layouts;

import com.neueda.jetbrains.plugin.graphdb.visualization.GraphDisplay;
import prefuse.Visualization;
import prefuse.action.RepaintAction;
import prefuse.util.GraphicsLib;
import prefuse.util.display.DisplayLib;

import java.awt.geom.Rectangle2D;

public class RepainAndRepositionAction extends RepaintAction {

    private static final long DURATION = 0;
    private static final int MARGIN = 50;

    private Visualization visualization;
    private GraphDisplay display;

    public RepainAndRepositionAction(Visualization visualization, GraphDisplay display) {
        super(visualization);
        this.visualization = visualization;
        this.display = display;
    }

    @Override
    public void run(double frac) {
        super.run(frac);

        Rectangle2D bounds = visualization.getBounds(Visualization.ALL_ITEMS);
        GraphicsLib.expand(bounds, MARGIN + (int) (1 / display.getScale()));
        DisplayLib.fitViewToBounds(display, bounds, DURATION);
    }
}
