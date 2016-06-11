package com.neueda.jetbrains.plugin.graphdb.visualization.settings;

import prefuse.data.Schema;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;

import javax.swing.*;
import java.awt.*;

import static prefuse.Constants.SHAPE_ELLIPSE;

public class SchemaProvider {

    private static final int FONT_SIZE = 10;
    private static final int FONT_COLOR = ColorLib.rgb(15, 15, 45);
    private static final String UI_DEFAULT_FONT_KEY = "Label.font";

    public static Schema provideFontSchema() {
        final Schema fontSchema = PrefuseLib.getVisualItemSchema();
        fontSchema.setDefault(VisualItem.INTERACTIVE, false);
        fontSchema.setDefault(VisualItem.TEXTCOLOR, FONT_COLOR);
        Font font = FontLib.getFont(UIManager.getFont(UI_DEFAULT_FONT_KEY).getFontName(), FONT_SIZE);
        fontSchema.setDefault(VisualItem.FONT, font);

        return fontSchema;
    }

    public static Schema provideNodeSchema() {
        Schema nodeSchema = PrefuseLib.getVisualItemSchema();
        nodeSchema.setDefault(VisualItem.SHAPE, SHAPE_ELLIPSE);

        return nodeSchema;
    }

    public static Schema provideEdgeSchema() {
        return PrefuseLib.getVisualItemSchema();
    }
}
