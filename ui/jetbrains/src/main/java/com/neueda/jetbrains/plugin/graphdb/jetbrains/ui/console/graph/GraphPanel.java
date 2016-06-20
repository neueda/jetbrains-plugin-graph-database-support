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
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.GraphConstants;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryExecutionProcessEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.UiHelper;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.renderes.tree.PropertyTreeCellRenderer;
import com.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import com.neueda.jetbrains.plugin.graphdb.visualization.util.DisplayUtil;
import prefuse.visual.VisualItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GraphPanel {

    private static final int PROPERTY_DEPTH = 3;

    private PrefuseVisualization visualization;
    private LookAndFeelService lookAndFeelService;
    private BalloonBuilder balloonPopupBuilder;
    private Balloon balloon;
    private JBLabel balloonLabel = new JBLabel();
    private GraphPanelInteractions interactions;
    private Tree entityDetailsTree;
    private DefaultTreeModel entityDetailsTreeModel;
    private PatchedDefaultMutableTreeNode noEntityRoot;

    public GraphPanel() {
        noEntityRoot = new PatchedDefaultMutableTreeNode("Select entity...");
        entityDetailsTreeModel = new DefaultTreeModel(noEntityRoot);
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
        messageBus.connect().subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
            @Override
            public void preResultReceived() {
                entityDetailsTreeModel.setRoot(noEntityRoot);
            }

            @Override
            public void resultReceived(GraphQueryResult result) {
            }

            @Override
            public void postResultReceived() {
            }

            @Override
            public void handleError(Exception exception) {
            }
        });

        // Tooltips
        balloonBuilder();

        // Interactions
        this.interactions = new GraphPanelInteractions(
                consoleToolWindow,
                messageBus,
                visualization);
    }

    public void handleClick(GraphNode node, VisualItem item, MouseEvent e) {
        showNodeData(node, item, e);
    }

    public void showNodeData(GraphNode node, VisualItem item, MouseEvent e) {
        PatchedDefaultMutableTreeNode root = UiHelper.nodeToTreeNode(node.getRepresentation(), node);
        entityDetailsTreeModel.setRoot(root);
        entityDetailsTree.expandRow(PROPERTY_DEPTH);
    }

    public void showRelationshipData(GraphRelationship relationship, VisualItem item, MouseEvent e) {
        PatchedDefaultMutableTreeNode root = UiHelper.relationshipToTreeNode(
                relationship.getRepresentation(), relationship);
        entityDetailsTreeModel.setRoot(root);
        entityDetailsTree.expandRow(PROPERTY_DEPTH);
    }

    public void showTooltip(GraphEntity entity, VisualItem item, MouseEvent e) {
        if (balloon != null && !balloon.isDisposed())
            balloon.hide();

        balloonPopupBuilder.setTitle(entity.getRepresentation());
        balloonLabel.setText(DisplayUtil.getTooltipText(entity));

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
