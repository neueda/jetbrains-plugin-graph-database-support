package com.neueda.jetbrains.plugin.graphdb.visualization;

import com.intellij.ui.border.CustomLineBorder;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.visualization.decorators.CenteredLayout;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.NodeCallback;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.RelationshipCallback;
import com.neueda.jetbrains.plugin.graphdb.visualization.listeners.NodeListener;
import com.neueda.jetbrains.plugin.graphdb.visualization.listeners.RelationshipListener;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import com.neueda.jetbrains.plugin.graphdb.visualization.settings.ColorProvider;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.layout.Layout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.*;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.neueda.jetbrains.plugin.graphdb.visualization.settings.GraphGroups.*;
import static prefuse.Constants.EDGE_TYPE_LINE;
import static prefuse.Constants.SHAPE_ELLIPSE;

public class GraphDisplay extends Display {

    private static final boolean ENFORCE_BOUNDS = false;
    private static final boolean DIRECTED = true;
    private static final boolean RUN_ONCE = true;

    private static final int NODE_DIAMETER = 35;
    private static final String UI_DEFAULT_FONT_KEY = "Label.font";
    private static final String LAYOUT = "layout";
    private static final String REPAINT = "repaint";

    private static final int FONT_SIZE = 10;
    private static final int FONT_COLOR = ColorLib.rgb(15, 15, 45);
    private static final String ID = "id";
    private static final String LABEL_FIELD = "id";

    private static final int SIMULATION_DURATION = 1000;
    private static final long SIMULATION_STEP_TIME = Activity.DEFAULT_STEP_TIME - 7L;
    public static final double EDGE_THICKNESS_COEFFICIENT = 4;
    public static final double ARROW_THICKNESS_COEFFICIENT = 2;

    private LookAndFeelService lookAndFeelService;

    private Graph graph;

    private Map<String, Node> nodeMap = new HashMap<>();
    private Map<String, GraphNode> graphNodeMap = new HashMap<>();
    private Map<String, GraphRelationship> graphRelationshipMap = new HashMap<>();

    public GraphDisplay(LookAndFeelService lookAndFeelService) {
        super(new Visualization());
        this.lookAndFeelService = lookAndFeelService;
        setBackground(lookAndFeelService.getBackgroundColor());

        setBorder(new CustomLineBorder(new Insets(0, 1, 0, 1)));
        graph = new Graph(DIRECTED);
        graph.addColumn(ID, String.class);

        Schema nodeSchema = PrefuseLib.getVisualItemSchema();
        nodeSchema.setDefault(VisualItem.SHAPE, SHAPE_ELLIPSE);

        Schema edgeSchema = PrefuseLib.getVisualItemSchema();

        m_vis.addGraph(GRAPH, graph, null, nodeSchema, edgeSchema);
        m_vis.setInteractive(EDGES, null, false);
        m_vis.setValue(NODES, null, VisualItem.SHAPE, SHAPE_ELLIPSE);

        setupRenderer();
        createLayout();
        setHighQuality(true);
        addControlListener(new DragControl());
        addControlListener(new ZoomControl());
        addControlListener(new ZoomToFitControl());
        addControlListener(new PanControl());
        addControlListener(new NeighborHighlightControl());
    }

    public void clearGraph() {
        graph.clear();
    }

    public void addNodeListener(EventType type, NodeCallback callback) {
        addControlListener(new NodeListener(type, callback, graphNodeMap));
    }

    public void addEdgeListener(EventType type, RelationshipCallback callback) {
        addControlListener(new RelationshipListener(type, callback, graphRelationshipMap));
    }

    public void addNode(GraphNode graphNode) {
        Node node = graph.addNode();
        node.set(ID, graphNode.getId());
        nodeMap.put(graphNode.getId(), node);
        graphNodeMap.put(graphNode.getId(), graphNode);
    }

    public void addRelationship(GraphRelationship graphRelationship) {
        if (graphRelationship.hasStartAndEndNode()) {
            String start = graphRelationship.getStartNode().getId();
            String end = graphRelationship.getEndNode().getId();

            Edge edge = graph.addEdge(nodeMap.get(start), nodeMap.get(end));
            edge.set(ID, graphRelationship.getId());
            graphRelationshipMap.put(graphRelationship.getId(), graphRelationship);
        }
    }

    private void setupRenderer() {
        ShapeRenderer nodeRenderer = new ShapeRenderer();

        nodeRenderer.setBaseSize(NODE_DIAMETER);
        EdgeRenderer edgeRenderer = new EdgeRenderer(EDGE_TYPE_LINE);
        edgeRenderer.setDefaultLineWidth(EDGE_THICKNESS_COEFFICIENT);
        edgeRenderer.setArrowHeadSize((int)(8 * ARROW_THICKNESS_COEFFICIENT), (int)(12 * ARROW_THICKNESS_COEFFICIENT));
        DefaultRendererFactory rf = new DefaultRendererFactory(nodeRenderer, edgeRenderer);
        LabelRenderer labelRenderer = new LabelRenderer(LABEL_FIELD);
        labelRenderer.setMaxTextWidth(NODE_DIAMETER);
        rf.add(new InGroupPredicate(NODE_LABEL), labelRenderer);

        final Schema decoratorSchema = PrefuseLib.getVisualItemSchema();
        decoratorSchema.setDefault(VisualItem.INTERACTIVE, false);
        decoratorSchema.setDefault(VisualItem.TEXTCOLOR, FONT_COLOR);
        Font font = FontLib.getFont(UIManager.getFont(UI_DEFAULT_FONT_KEY).getFontName(), FONT_SIZE);
        decoratorSchema.setDefault(VisualItem.FONT, font);

        m_vis.addDecorators(NODE_LABEL, NODES, decoratorSchema);

        m_vis.setRendererFactory(rf);
    }

    private void createLayout() {
        ActionList layout = new ActionList(SIMULATION_DURATION, SIMULATION_STEP_TIME);
        layout.add(ColorProvider.getColors(lookAndFeelService));
        layout.add(createForceLayout(layout));

        layout.setPacingFunction(new AnimationPacer());

        ActionList repaint = new ActionList(Activity.INFINITY);
        repaint.add(new CenteredLayout(NODE_LABEL));
        repaint.add(new RepaintAction());

        m_vis.putAction(LAYOUT, layout);
        m_vis.putAction(REPAINT, repaint);
    }

    private Layout createForceLayout(ActionList layout) {
        ForceDirectedLayout forceLayout = new CustomForceLayout(GRAPH, ENFORCE_BOUNDS);
        layout.add(forceLayout);
        layout.add(new RepaintAction());

        return forceLayout;
    }

    public void startLayout() {
        m_vis.run(LAYOUT);
        m_vis.run(REPAINT);
    }

    public void stopLayout() {
        m_vis.cancel(LAYOUT);
        m_vis.cancel(REPAINT);
    }
}
