package com.neueda.jetbrains.plugin.graphdb.visualization.layouts;

import prefuse.activity.Pacer;

public class AnimationPacer implements Pacer {

    @Override
    public double pace(double f) {
        return paceExp(f);
    }

    public double paceExp(double f) {
        double v = 1 + Math.exp(f)*1.3;
        return v == 2 ? 0 : v > 3 ? 1 : -(2 - v);
    }

}
