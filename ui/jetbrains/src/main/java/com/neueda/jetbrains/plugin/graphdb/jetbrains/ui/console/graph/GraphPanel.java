package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.popup.BalloonPopupBuilderImpl;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.GraphConstants;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.UiHelper;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.renderes.tree.PropertyTreeCellRenderer;
import com.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import prefuse.visual.VisualItem;

import javax.swing.Box;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Map;

public class GraphPanel {

    private static final int LABEL_TEXT_WIDTH = 300;

    private PrefuseVisualization visualization;
    private LookAndFeelService lookAndFeelService;
    private BalloonBuilder balloonPopupBuilder;
    private Balloon balloon;
    private JBLabel balloonLabel = new JBLabel();
    private GraphPanelInteractions interactions;
    private Tree entityDetailsTree;
    private DefaultTreeModel entityDetailsTreeModel;

    public GraphPanel() {
        entityDetailsTreeModel = new DefaultTreeModel(new PatchedDefaultMutableTreeNode("Select entity..."));
    }

    public void initialize(ConsoleToolWindow consoleToolWindow, MessageBus messageBus) {
        this.lookAndFeelService = consoleToolWindow.getLookAndFeelService();
        this.entityDetailsTree = consoleToolWindow.getEntityDetailsTree();

        // Actions
        final ActionGroup consoleActionGroup = (ActionGroup)
                ActionManager.getInstance().getAction(GraphConstants.Actions.CONSOLE_ACTIONS);

        ActionToolbar consoleToolbar = ActionManager.getInstance()
                .createActionToolbar(GraphConstants.ToolWindow.CONSOLE_TOOL_WINDOW, consoleActionGroup, false);
        final Box toolBarBox = Box.createHorizontalBox();
        toolBarBox.add(consoleToolbar.getComponent());
        consoleToolWindow.getGraphToolbarPanel().add(toolBarBox);

        // Bootstrap visualisation
        visualization = new PrefuseVisualization(lookAndFeelService);
        consoleToolWindow.getGraphCanvas().add(visualization.getCanvas());

        // Entity data table
        entityDetailsTree.setCellRenderer(new PropertyTreeCellRenderer());
        entityDetailsTree.setModel(entityDetailsTreeModel);

        // Tooltips
        balloonBuilder();

        // Interactions
        this.interactions = new GraphPanelInteractions(
                consoleToolWindow,
                messageBus,
                visualization);
    }

    public void showNodeData(GraphNode node, VisualItem item, MouseEvent e) {
        PatchedDefaultMutableTreeNode root = UiHelper.nodeToTreeNode(node.getRepresentation(), node);
        entityDetailsTreeModel.setRoot(root);
        entityDetailsTree.expandRow(3);
    }

    public void showRelationshipData(GraphRelationship relationship, VisualItem item, MouseEvent e) {
        PatchedDefaultMutableTreeNode root = UiHelper.relationshipToTreeNode(
                relationship.getRepresentation(), relationship);
        entityDetailsTreeModel.setRoot(root);
        entityDetailsTree.expandRow(3);
    }

    public void showTooltip(GraphEntity entity, VisualItem item, MouseEvent e) {
        if (balloon != null && !balloon.isDisposed())
            balloon.hide();

        balloonPopupBuilder.setTitle(entity.getRepresentation());
        balloonLabel.setText(getFiveProperties(entity.getPropertyContainer().getProperties()));

        balloon = balloonPopupBuilder.createBalloon();
        Container panel = e.getComponent().getParent();

        final int MAGIC_NUMBER = 15;
        int heightOffset = balloon.getPreferredSize().height / 2 + MAGIC_NUMBER;

        int widthOffset;
        if (e.getX() > panel.getWidth() / 2) {
            widthOffset = balloon.getPreferredSize().width / 2;
        } else {
            widthOffset = panel.getWidth() - balloon.getPreferredSize().width / 2;
        }

        balloon.show(new RelativePoint(panel, new Point(widthOffset, heightOffset)), Balloon.Position.below);
    }

    public void resetPan(GraphNode n, VisualItem item, MouseEvent e) {
        visualization.resetPan();
    }

    private String getFiveProperties(Map<String, Object> properties) {
        StringBuilder sb = new StringBuilder();

        properties.entrySet().stream()
                .limit(5)
                .forEach(entry -> sb
                        .append("<p width=\"" + LABEL_TEXT_WIDTH + "px\"><b>")
                        .append(entry.getKey())
                        .append("</b>: ")
                        .append(entry.getValue())
                        .append("</p>"));

        return "<html>" + sb.toString() + "</html>";
    }

    private void balloonBuilder() {
        final BalloonPopupBuilderImpl builder = new BalloonPopupBuilderImpl(null, balloonLabel);

        final Color bg = lookAndFeelService.getBackgroundColor();
        final Color borderOriginal = lookAndFeelService.getEdgeStrokeColor();
        final Color border = ColorUtil.toAlpha(borderOriginal, 75);
        builder
                .setShowCallout(false)
                .setDialogMode(false)
                .setAnimationCycle(20)
                .setFillColor(bg).setBorderColor(border).setHideOnClickOutside(true)
                .setHideOnKeyOutside(false)
                .setHideOnAction(false)
                .setCloseButtonEnabled(false)
                .setShadow(true);

        balloonPopupBuilder = builder;
    }

    public void hideTooltip(GraphEntity entity, VisualItem visualItem, MouseEvent mouseEvent) {
        balloon.dispose();
    }
}
