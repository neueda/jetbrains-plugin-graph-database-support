package com.neueda.jetbrains.plugin.graphdb.visualization;

import com.neueda.jetbrains.plugin.graphdb.visualization.util.DisplayUtil;
import prefuse.controls.ControlAdapter;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.tuple.TableNodeItem;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

import static com.neueda.jetbrains.plugin.graphdb.visualization.util.DisplayUtil.cast;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

public class DisplayStatus extends ControlAdapter {
    private volatile VisualItem current;

    @Override
    public void itemPressed(VisualItem item, MouseEvent e) {
        current = item;
    }

    @Override
    public void itemReleased(VisualItem item, MouseEvent ev) {
        DisplayUtil.cast(item, TableNodeItem.class)
                .ifPresent(
                        n -> {
                            Spliterator<?> iterator = spliteratorUnknownSize(n.edges(), 0);
                            stream(iterator, false)
                                    .map(e -> cast(e, EdgeItem.class))
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .forEach(e -> e.getVisualization().repaint());
                        }
                );
        current = null;
    }

    public boolean isItemDragged(VisualItem item) {
        return current == item;
    }
}
