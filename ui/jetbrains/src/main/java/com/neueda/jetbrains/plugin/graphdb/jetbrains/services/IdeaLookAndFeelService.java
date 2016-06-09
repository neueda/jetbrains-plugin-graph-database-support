package com.neueda.jetbrains.plugin.graphdb.jetbrains.services;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.UIUtil;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;

import javax.swing.*;
import java.awt.*;

public class IdeaLookAndFeelService implements LookAndFeelService {

    @Override
    public Color getBackgroundColor() {
        return UIUtil.getPanelBackground();
    }

    @Override
    public Color getBorderColor() {
        return JBColor.border();
    }

    @Override
    public Color getEdgeStrokeColor() {
        return UIManager.getColor("controlText");
    }

    @Override
    public Color getEdgeFillColor() {
        return UIManager.getColor("controlText");
    }

    @Override
    public Color getNodeStrokeColor() {
        return UIManager.getColor("controlText");
    }

    @Override
    public Color getNodeStrokeHoverColor() {
        return UIManager.getColor("controlText");
    }

    @Override
    public Color getNodeFillColor() {
        return UIManager.getColor("InternalFrame.inactiveTitleBackground");
    }

    @Override
    public Color getNodeFillHoverColor() {
        return UIManager.getColor("InternalFrame.activeTitleBackground");
    }

    @Override
    public Color getTextColor() {
        return UIManager.getColor("text");
    }

}
