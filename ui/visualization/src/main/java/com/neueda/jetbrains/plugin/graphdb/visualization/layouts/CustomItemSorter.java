package com.neueda.jetbrains.plugin.graphdb.visualization.layouts;

import prefuse.visual.DecoratorItem;
import prefuse.visual.VisualItem;
import prefuse.visual.sort.ItemSorter;

public class CustomItemSorter extends ItemSorter {

    // Value of 2^22, which is minimal score gap before the next category
    public static final int GAP = 4194304;

    @Override
    public int score(VisualItem item) {
        if (item instanceof DecoratorItem) {
            VisualItem decoratedItem = ((DecoratorItem) item).getDecoratedItem();
            return layerByRow(decoratedItem) + 1;
        }

        return layerByRow(item);
    }

    private int layerByRow(VisualItem item) {
        return super.score(item) + (item.getRow() * 2 % GAP);
    }
}
