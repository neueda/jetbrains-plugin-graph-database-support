package com.neueda.jetbrains.plugin.graphdb.visualization.layouts;

import com.neueda.jetbrains.plugin.graphdb.visualization.GraphDisplay;
import com.neueda.jetbrains.plugin.graphdb.visualization.util.PrefuseUtil;
import prefuse.Visualization;
import prefuse.action.RepaintAction;

public class RepainAndRepositionAction extends RepaintAction {

    private static final long DURATION = 0;

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

        PrefuseUtil.zoomAndPanToFit(visualization, display);
    }
}
