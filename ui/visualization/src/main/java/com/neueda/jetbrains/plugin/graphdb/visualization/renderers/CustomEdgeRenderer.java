package com.neueda.jetbrains.plugin.graphdb.visualization.renderers;

import com.neueda.jetbrains.plugin.graphdb.visualization.util.RenderingUtil;
import prefuse.Constants;
import prefuse.render.EdgeRenderer;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.tuple.TableNodeItem;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.VisualizationParameters.EDGE_THICKNESS;
import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.VisualizationParameters.NODE_DIAMETER;
import static java.lang.Math.*;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

public class CustomEdgeRenderer extends EdgeRenderer {

    private static final double RADIUS = (NODE_DIAMETER + EDGE_THICKNESS) / 2;
    private static final double RIGHT_ANGLE_IN_RADS = 1.5708;

    public CustomEdgeRenderer(int edgeTypeLine) {
        super(edgeTypeLine);
    }

    @Override
    protected Shape getRawShape(VisualItem item) {
        EdgeItem edge = (EdgeItem) item;
        VisualItem item1 = edge.getSourceItem();
        VisualItem item2 = edge.getTargetItem();

        boolean isDirected = m_edgeArrow != Constants.EDGE_ARROW_NONE;
        boolean isLoopNode = item1 == item2;
        double shift = getRelationShift(edge, item1, item2);

        double x1 = item1.getBounds().getCenterX();
        double y1 = item1.getBounds().getCenterY();
        double x2 = item2.getBounds().getCenterX();
        double y2 = item2.getBounds().getCenterY();

        if (isLoopNode) {
            double angle = 0.261799 * 3 * shift;

            getAlignedPoint(m_tmpPoints[0], item1.getBounds(), m_xAlign1, m_yAlign1);
            getAlignedPoint(m_tmpPoints[1], item2.getBounds(), m_xAlign2, m_yAlign2);
            m_curWidth = (float) (m_width * getLineWidth(item));

            if (isDirected) {
                double x = m_tmpPoints[0].getX();
                double y = m_tmpPoints[0].getY();

                Point.Double[] arrowPoints = RenderingUtil.arrow(angle, RADIUS, x, y, m_arrowHeight + 8);
                AffineTransform at = getArrowTrans(arrowPoints[0], arrowPoints[1], m_curWidth);
                m_curArrow = at.createTransformedShape(m_arrowHead);
            }

            return RenderingUtil.loopArrow(angle, RADIUS, x1, y1, m_arrowHeight);
        } else {
            double angle = RIGHT_ANGLE_IN_RADS - atan2(y2 - y1, x2 - x1);

            double shiftInRad = toRadians(shift * 20);

            double lineX1 = x1 + RADIUS * sin(angle + shiftInRad);
            double lineY1 = y1 + RADIUS * cos(angle + shiftInRad);

            double arrowX = x2 - RADIUS * sin(angle - shiftInRad);
            double arrowY = y2 - RADIUS * cos(angle - shiftInRad);

            if (isDirected) {
                AffineTransform at = getArrowTrans(new Point2D.Double(lineX1, lineY1), new Point2D.Double(arrowX, arrowY), m_curWidth);
                m_curArrow = at.createTransformedShape(m_arrowHead);

                double lineX2 = arrowX - m_arrowWidth * sin(angle);
                double lineY2 = arrowY - m_arrowWidth * cos(angle);

                m_line.setLine(lineX1, lineY1, lineX2, lineY2);
            } else {
                m_line.setLine(lineX1, lineY1, arrowX, arrowY);
            }

            return m_line;
        }
    }

    private double getRelationShift(EdgeItem edge, VisualItem node1, VisualItem node2) {
        boolean reverse = node1.getRow() > node2.getRow();
        VisualItem node = reverse ? node1 : node2;

        return cast(node, TableNodeItem.class)
                .map(n -> {
                    Spliterator<?> iterator = spliteratorUnknownSize(n.edges(), 0);
                    List<EdgeItem> edges = stream(iterator, false)
                            .map(e -> cast(e, EdgeItem.class))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .filter(e ->
                                    (e.getSourceItem() == node1 && e.getTargetItem() == node2) ||
                                            (e.getSourceItem() == node2 && e.getTargetItem() == node1)
                            )
                            .collect(toList());

                    if (edges.size() > 1) {
                        int relNumber = edges.indexOf(edge);
                        double relPos = relNumber - edges.size() / 2;
                        if (edges.size() % 2 == 0) {
                            relPos = relPos + 0.5;
                        }

                        return reverse ? relPos : relPos * -1;
                    } else {
                        return 0d;
                    }
                })
                .orElse(0d);
    }

    @Override
    public boolean locatePoint(Point2D p, VisualItem item) {
        Shape s = getShape(item);
        if (s == null) {
            return false;
        } else {
            double width = item.getSize() * EDGE_THICKNESS;
            double halfWidth = width / 2.0;
            return s.intersects(p.getX() - halfWidth, p.getY() - halfWidth, width, width)
                    || (m_curArrow != null && m_curArrow.contains(p.getX(), p.getY()));
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Optional<T> cast(Object o, Class<T> clazz) {
        if (clazz.isInstance(o)) {
            return Optional.of((T) o);
        } else {
            return Optional.empty();
        }
    }
}
