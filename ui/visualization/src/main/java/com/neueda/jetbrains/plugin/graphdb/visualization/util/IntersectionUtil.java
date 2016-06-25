package com.neueda.jetbrains.plugin.graphdb.visualization.util;

import com.google.common.collect.Lists;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;

public class IntersectionUtil {

    /**
     * @author arne.b
     *
     * http://stackoverflow.com/questions/13053061/circle-line-intersection-points
     */
    public static List<Point2D> getCircleLineIntersectionPoint(Point2D pointA, Point2D pointB, Point2D center, double radius) {
        double baX = pointB.getX() - pointA.getX();
        double baY = pointB.getY() - pointA.getY();
        double caX = center.getX() - pointA.getX();
        double caY = center.getY() - pointA.getY();

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - radius * radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        if (disc < 0) {
            return Collections.emptyList();
        }

        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        Point2D p1 = new Point2D.Double(pointA.getX() - baX * abScalingFactor1, pointA.getY() - baY * abScalingFactor1);
        if (disc == 0) {
            return Collections.singletonList(p1);
        }
        Point2D p2 = new Point2D.Double(pointA.getX() - baX * abScalingFactor2, pointA.getY() - baY * abScalingFactor2);
        return Lists.newArrayList(p1, p2);
    }
}
