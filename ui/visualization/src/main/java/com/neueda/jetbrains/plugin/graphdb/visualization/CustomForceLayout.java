package com.neueda.jetbrains.plugin.graphdb.visualization;

import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.util.force.DragForce;
import prefuse.util.force.Force;
import prefuse.util.force.NBodyForce;
import prefuse.util.force.SpringForce;

public class CustomForceLayout extends ForceDirectedLayout {

    private static final int SPRING_COEFFICIENT = 0;
    private static final int SPRING_LENGTH = 1;
    private static final int DRAG_COEFFICIENT = 0;
    private static final int GRAVITATIONAL_CONSTANT = 0;

    private static final double DRAG_START = 0.2;
    private static final double DRAG_END = 0.01;

    private static final double SPRING_COEFFICIENT_START = 0.2;
    private static final double SPRING_COEFFICIENT_END = 0.0;
    private static final int SPRING_LENGTH_START = 100;
    private static final int SPRING_LENGTH_END = 200;
    private static final int GRAVITY_START = -8;
    private static final int GRAVITY_END = -10;


    public CustomForceLayout(String group, boolean enforceBounds) {
        super(group, enforceBounds);
    }

    @Override
    public void run(double frac) {
        for(Force force: getForceSimulator().getForces()) {
            if (force instanceof DragForce) {
                float dragMax = force.getMinValue(DRAG_COEFFICIENT);
                float dragMin = force.getMaxValue(DRAG_COEFFICIENT);
                force.setParameter(DRAG_COEFFICIENT, part(
                        part(dragMin, dragMax, DRAG_START),
                        part(dragMin, dragMax, DRAG_END),
                        frac));
            } else if (force instanceof SpringForce) {
                float springMax = force.getMinValue(SPRING_COEFFICIENT);
                float sprintMin = force.getMaxValue(SPRING_COEFFICIENT);
                force.setParameter(SPRING_COEFFICIENT, part(
                        part(springMax, sprintMin, SPRING_COEFFICIENT_START),
                        part(springMax, sprintMin, SPRING_COEFFICIENT_END),
                        frac));
                force.setParameter(SPRING_LENGTH, part(SPRING_LENGTH_START, SPRING_LENGTH_END, frac));
            } else if (force instanceof NBodyForce) {
                force.setParameter(GRAVITATIONAL_CONSTANT, part(GRAVITY_START, GRAVITY_END, frac));
            }
        }

        super.run(frac);
    }

    private float part(double min, double max, double part) {
        return (float)(min + (max - min) * part);
    }


}
