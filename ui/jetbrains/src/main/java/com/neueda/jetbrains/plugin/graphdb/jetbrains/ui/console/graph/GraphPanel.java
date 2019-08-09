package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.labels.LinkLabel;
import com.intellij.ui.components.labels.LinkListener;
import com.intellij.ui.popup.BalloonPopupBuilderImpl;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryExecutionProcessEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeMouseAdapter;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers.UiHelper;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.renderes.tree.PropertyTreeCellRenderer;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants.ToolWindow.Tabs;
import com.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import prefuse.visual.VisualItem;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.OpenTabEvent.*;
import static com.neueda.jetbrains.plugin.graphdb.visualization.util.DisplayUtil.*;

public class GraphPanel {

    private PrefuseVisualization visualization;
    private LookAndFeelService lookAndFeelService;
    private BalloonBuilder balloonPopupBuilder;
    private Balloon balloon;
    private JBLabel balloonLabel = new JBLabel();
    private GraphPanelInteractions interactions;
    private Tree entityDetailsTree;
    private DefaultTreeModel entityDetailsTreeModel;
    private DataSourceApi dataSource;

    public GraphPanel() {
        entityDetailsTreeModel = new DefaultTreeModel(null);
    }

    public void initialize(GraphConsoleView graphConsoleView, Project project) {
        MessageBus messageBus = project.getMessageBus();
        this.lookAndFeelService = graphConsoleView.getLookAndFeelService();
        this.entityDetailsTree = graphConsoleView.getEntityDetailsTree();
        entityDetailsTree.addMouseListener(new TreeMouseAdapter());

        // Bootstrap visualisation
        visualization = new PrefuseVisualization(lookAndFeelService);
        graphConsoleView.getGraphCanvas().add(visualization.getCanvas());

        // Entity data table
        entityDetailsTree.setCellRenderer(new PropertyTreeCellRenderer());
        entityDetailsTree.setModel(entityDetailsTreeModel);
        messageBus.connect().subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
            @Override
            public void executionStarted(DataSourceApi dataSource, ExecuteQueryPayload payload) {
                GraphPanel.this.dataSource = dataSource;
                entityDetailsTreeModel.setRoot(null);
            }

            @Override
            public void resultReceived(ExecuteQueryPayload payload, GraphQueryResult result) {
                if (result.getNodes().isEmpty() && !result.getRows().isEmpty()) {
                    LinkListener<?> openTableTab = (l, s) -> messageBus.syncPublisher(OPEN_TAB_TOPIC).openTab(Tabs.TABLE);
                    LinkLabel<?> link = new LinkLabel<>("Nothing to display in Graph. Click to view results as Table.", null, openTableTab);
                    entityDetailsTreeModel.setRoot(new PatchedDefaultMutableTreeNode(link));
                } else if (result.getNodes().isEmpty()) {
                    entityDetailsTreeModel.setRoot(new PatchedDefaultMutableTreeNode("Query returned no results."));
                } else {
                    entityDetailsTreeModel.setRoot(new PatchedDefaultMutableTreeNode("Select an item in the graph to view details..."));
                }
            }

            @Override
            public void postResultReceived(ExecuteQueryPayload payload) {
            }

            @Override
            public void handleError(ExecuteQueryPayload payload, Exception exception) {
            }

            @Override
            public void executionCompleted(ExecuteQueryPayload payload) {
            }
        });

        // Tooltips
        balloonBuilder();

        // Interactions
        this.interactions = new GraphPanelInteractions(
                graphConsoleView,
                messageBus,
                visualization);
    }

    public void showNodeData(GraphNode node, VisualItem item, MouseEvent e) {
        PatchedDefaultMutableTreeNode root = UiHelper.nodeToTreeNode(node.getRepresentation(), node, dataSource);
        entityDetailsTreeModel.setRoot(root);

        Enumeration childs = root.children();
        while (childs.hasMoreElements()) {
            PatchedDefaultMutableTreeNode treeNode
                    = (PatchedDefaultMutableTreeNode) childs.nextElement();
            entityDetailsTree.expandPath(new TreePath(treeNode.getPath()));
        }
    }

    public void showRelationshipData(GraphRelationship relationship, VisualItem item, MouseEvent e) {
        PatchedDefaultMutableTreeNode root = UiHelper.relationshipToTreeNode(
                relationship.getRepresentation(), relationship, dataSource);
        entityDetailsTreeModel.setRoot(root);

        Enumeration childs = root.children();
        while (childs.hasMoreElements()) {
            PatchedDefaultMutableTreeNode treeNode
                    = (PatchedDefaultMutableTreeNode) childs.nextElement();
            entityDetailsTree.expandPath(new TreePath(treeNode.getPath()));
        }
    }

    public void showTooltip(GraphEntity entity, VisualItem item, MouseEvent e) {
        if (balloon != null && !balloon.isDisposed()) {
            balloon.hide();
        }

        balloonPopupBuilder.setTitle(getTooltipTitle(entity));
        balloonLabel.setText(getTooltipText(entity));

        balloon = balloonPopupBuilder.createBalloon();
        Container panel = e.getComponent().getParent();

        final int magicNumber = 15;
        int heightOffset = balloon.getPreferredSize().height / 2 + magicNumber;

        int widthOffset;
        if (e.getX() > panel.getWidth() / 2) {
            widthOffset = balloon.getPreferredSize().width / 2;
        } else {
            widthOffset = panel.getWidth() - balloon.getPreferredSize().width / 2;
        }

        balloon.show(new RelativePoint(panel, new Point(widthOffset, heightOffset)), Balloon.Position.below);
    }

    public void resetPan() {
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
