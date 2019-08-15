package com.neueda.jetbrains.plugin.graphdb.visualization.renderers;

import com.neueda.jetbrains.plugin.graphdb.visualization.DisplayStatus;
import prefuse.render.LabelRenderer;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.tuple.TableDecoratorItem;
import prefuse.visual.tuple.TableEdgeItem;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.visualization.util.DisplayUtil.cast;
import static java.lang.Math.atan2;

public class LabelRendererAngled extends LabelRenderer {
    private DisplayStatus status;

    public LabelRendererAngled(String textField, DisplayStatus status) {
        super(textField);
        this.status = status;
    }

    @Override
    public void render(Graphics2D g, VisualItem item) {
        Optional<TableEdgeItem> maybeEdge = cast(item, TableDecoratorItem.class)
                .flatMap(e -> cast(e.getDecoratedItem(), TableEdgeItem.class));

        if (!maybeEdge.isPresent()) {
            super.render(g, item);
        }

        maybeEdge.ifPresent(e -> {
                    NodeItem item1 = e.getSourceItem();
                    NodeItem item2 = e.getTargetItem();

                    if (status.isItemDragged(item1) || status.isItemDragged(item2)) {
                        return;
                    }

                    if (item1 == item2 || e.getTable().getRowCount() > 50) {
                        super.render(g, item);
                    } else {
                        double x1 = item1.getBounds().getCenterX();
                        double y1 = item1.getBounds().getCenterY();
                        double x2 = item2.getBounds().getCenterX();
                        double y2 = item2.getBounds().getCenterY();

                        AffineTransform orig = g.getTransform();

                        double angle = atan2(y2 - y1, x2 - x1);
                        g.rotate(angle, item.getBounds().getCenterX(), item.getBounds().getCenterY());

                        setBounds(item);

                        super.render(g, item);
                        g.setTransform(orig);
                    }
                }
        );
    }


}
