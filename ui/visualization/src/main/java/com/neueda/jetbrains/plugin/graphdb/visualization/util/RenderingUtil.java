package com.neueda.jetbrains.plugin.graphdb.visualization.util;

import java.awt.*;
import java.awt.geom.Path2D;

public class RenderingUtil {

    private static final double FIFTEEN = 0.261799;
    public static final double CURVE_RADIUS = 2;
    public static final double LINE_RADIUS = 1.7;

    public static Path2D loopArrow(double angle, double radius, double x, double y, double arrowHeight) {
        Path2D.Double path = new Path2D.Double();

        double angleSin = Math.sin(angle);
        double angleCos = Math.cos(angle);
        double angleToSin = Math.sin(angle + FIFTEEN);
        double angleToCos = Math.cos(angle + FIFTEEN);

        path.moveTo(x + radius * angleCos, y + radius * angleSin);
        path.lineTo(x + angleCos * radius * LINE_RADIUS, y + angleSin * radius * LINE_RADIUS);
        path.curveTo(
                x + angleCos * radius * CURVE_RADIUS, y + angleSin * radius * CURVE_RADIUS,
                x + angleToCos * radius * CURVE_RADIUS, y + angleToSin * radius * CURVE_RADIUS,
                x + angleToCos * radius * LINE_RADIUS, y + angleToSin * radius * LINE_RADIUS);
        path.lineTo(x + (radius + arrowHeight / 2) * angleToCos, y + (radius + arrowHeight / 2) * angleToSin);

        return path;
    }

    public static Point.Double[] arrow(double angle, double radius, double x, double y, double arrowHeight) {
        Point.Double[] coords = new Point.Double[2];

        double angleToCos = Math.cos(angle + FIFTEEN);
        double angleToSin = Math.sin(angle + FIFTEEN);

        coords[0] = new Point.Double(x + (radius + arrowHeight) * angleToCos, y + (radius + arrowHeight) * angleToSin);
        coords[1] = new Point.Double(x + radius * angleToCos, y + radius * angleToSin);

        return coords;
    }

}
