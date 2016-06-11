package com.neueda.jetbrains.plugin.graphdb.visualization.settings;

import com.neueda.jetbrains.plugin.graphdb.visualization.layouts.AnimationPacer;
import com.neueda.jetbrains.plugin.graphdb.visualization.layouts.CenteredLayout;
import com.neueda.jetbrains.plugin.graphdb.visualization.layouts.DynamicForceLayout;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.activity.Activity;

import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.GraphGroups.GRAPH;
import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.GraphGroups.NODE_LABEL;

public class LayoutProvider {

    private static final boolean ENFORCE_BOUNDS = false;
    private static final int SIMULATION_DURATION = 1000;
    private static final long SIMULATION_STEP_TIME = Activity.DEFAULT_STEP_TIME - 7L;

    public static ActionList forceLayout(LookAndFeelService lookAndFeelService) {
        ActionList actions = new ActionList(SIMULATION_DURATION, SIMULATION_STEP_TIME);
        actions.setPacingFunction(new AnimationPacer());

        actions.add(ColorProvider.colors(lookAndFeelService));
        actions.add(new DynamicForceLayout(GRAPH, ENFORCE_BOUNDS));
        actions.add(new RepaintAction());

        return actions;
    }

    public static ActionList repaintLayout() {
        ActionList repaint = new ActionList(Activity.INFINITY);
        repaint.add(new CenteredLayout(NODE_LABEL));
        repaint.add(new RepaintAction());

        return repaint;
    }
}
