package com.neueda.jetbrains.plugin.graphdb.visualization.layouts;

import prefuse.visual.DecoratorItem;
import prefuse.visual.VisualItem;
import prefuse.visual.sort.ItemSorter;

public class CustomItemSorter extends ItemSorter {

    @Override
    public int score(VisualItem item) {
        if (item instanceof DecoratorItem) {
            VisualItem decoratedItem = ((DecoratorItem) item).getDecoratedItem();
            return super.score(decoratedItem) + item.getRow() * 2 + 1;
        }

        // TODO limit with mod
        return super.score(item) + item.getRow() * 2;
    }
}
