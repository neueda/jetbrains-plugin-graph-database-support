package com.neueda.jetbrains.plugin.graphdb.visualization.renderers;

import com.neueda.jetbrains.plugin.graphdb.platform.ShouldNeverHappenException;
import com.neueda.jetbrains.plugin.graphdb.visualization.constants.VisualizationParameters;
import com.neueda.jetbrains.plugin.graphdb.visualization.util.IntersectionUtil;
import prefuse.Constants;
import prefuse.render.EdgeRenderer;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

public class CustomEdgeRenderer extends EdgeRenderer {

    public CustomEdgeRenderer(int edgeTypeLine) {
        super(edgeTypeLine);
    }

    @Override
    protected Shape getRawShape(VisualItem item) {
        EdgeItem edge = (EdgeItem) item;
        VisualItem item1 = edge.getSourceItem();
        VisualItem item2 = edge.getTargetItem();

        int type = m_edgeType;

        getAlignedPoint(m_tmpPoints[0], item1.getBounds(), m_xAlign1, m_yAlign1);
        getAlignedPoint(m_tmpPoints[1], item2.getBounds(), m_xAlign2, m_yAlign2);
        m_curWidth = (float) (m_width * getLineWidth(item));

        // create the arrow head, if needed
        EdgeItem e = (EdgeItem) item;
        if (e.isDirected() && m_edgeArrow != Constants.EDGE_ARROW_NONE) {
            // get starting and ending edge endpoints
            boolean forward = (m_edgeArrow == Constants.EDGE_ARROW_FORWARD);
            Point2D start = null, end = null;
            start = m_tmpPoints[forward ? 0 : 1];
            end = m_tmpPoints[forward ? 1 : 0];

            // compute the intersection with the target bounding box
            VisualItem dest = forward ? e.getTargetItem() : e.getSourceItem();
            Point2D center = new Point2D.Double(dest.getBounds().getCenterX(), dest.getBounds().getCenterY());
            List<Point2D> intersections = IntersectionUtil.getCircleLineIntersectionPoint(start, end, center, dest.getBounds().getWidth() / 2);

            if (intersections.size() == 0) {
                throw new ShouldNeverHappenException("Andrew Naydyonock", "edge always intersect a node");
            }

            end = intersections.get(0);

            // create the arrow head shape
            AffineTransform at = getArrowTrans(start, end, m_curWidth);
            m_curArrow = at.createTransformedShape(m_arrowHead);

            // update the endpoints for the edge shape
            // need to bias this by arrow head size
            Point2D lineEnd = m_tmpPoints[forward ? 1 : 0];
            lineEnd.setLocation(0, -m_arrowHeight);
            at.transform(lineEnd, lineEnd);
        } else {
            m_curArrow = null;
        }

        // create the edge shape
        Shape shape = null;
        double n1x = m_tmpPoints[0].getX();
        double n1y = m_tmpPoints[0].getY();
        double n2x = m_tmpPoints[1].getX();
        double n2y = m_tmpPoints[1].getY();
        switch (type) {
            case Constants.EDGE_TYPE_LINE:
                m_line.setLine(n1x, n1y, n2x, n2y);
                shape = m_line;
                break;
            case Constants.EDGE_TYPE_CURVE:
                getCurveControlPoints(edge, m_ctrlPoints, n1x, n1y, n2x, n2y);
                m_cubic.setCurve(n1x, n1y,
                        m_ctrlPoints[0].getX(), m_ctrlPoints[0].getY(),
                        m_ctrlPoints[1].getX(), m_ctrlPoints[1].getY(),
                        n2x, n2y);
                shape = m_cubic;
                break;
            default:
                throw new IllegalStateException("Unknown edge type");
        }

        // return the edge shape
        return shape;
    }

    @Override
    public boolean locatePoint(Point2D p, VisualItem item) {
        Shape s = getShape(item);
        if ( s == null ) {
            return false;
        } else {
            double width = item.getSize() * VisualizationParameters.EDGE_THICKNESS;
            double halfWidth = width/2.0;
            return s.intersects(p.getX()-halfWidth,
                    p.getY()-halfWidth,
                    width,width);
        }
    }
}
