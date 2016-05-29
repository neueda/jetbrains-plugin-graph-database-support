package lv.neueda.jetbrains.plugin.graphdb.visualization;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.common.GraphRelationship;
import lv.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.ControlAdapter;
import prefuse.controls.DragControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static prefuse.Constants.EDGE_TYPE_LINE;
import static prefuse.Constants.SHAPE_ELLIPSE;

public class GraphDisplay extends Display {

    private static final String GRAPH = "graph";
    private static final String NODES = "graph.nodes";
    private static final String EDGES = "graph.edges";
    private static final boolean DIRECTED = true;

    private Graph graph;

    private Map<String, Node> nodeMap = new HashMap<>();

    public GraphDisplay() {
        super(new Visualization());
        graph = new Graph(DIRECTED);
        graph.addColumn("id", String.class);

        m_vis.addGraph(GRAPH, graph);
        m_vis.setInteractive(EDGES, null, false);
        m_vis.setValue(NODES, null, VisualItem.SHAPE, SHAPE_ELLIPSE);

        setupRenderer();
        createLayout();
        setSize(500, 500);
        pan(250, 250);
        setHighQuality(true);
        addControlListener(new DragControl());
    }

    public void addNodeListener(EventType type, Consumer<String> action) {
        ControlAdapter focusListener = new ControlAdapter() {
            @Override
            public void itemEntered(VisualItem item, MouseEvent e) {
                if (type == EventType.HOVER && item instanceof NodeItem) {
                    action.accept((String)item.get("id"));
                }
            }

            @Override
            public void itemClicked(VisualItem item, MouseEvent e) {
                if (type == EventType.CLICK && item instanceof NodeItem) {
                    action.accept((String)item.get("id"));
                }
            }
        };
        addControlListener(focusListener);
    }

    public void addNode(GraphNode graphNode) {
        Node node = graph.addNode();
        node.set("id", graphNode.getId());
        nodeMap.put(graphNode.getId(), node);
    }

    public void addRelationship(GraphRelationship graphRelationship) {
        String start = graphRelationship.getStart().getId();
        String end = graphRelationship.getEnd().getId();
        graph.addEdge(nodeMap.get(start), nodeMap.get(end));
    }

    private void setupRenderer() {
        DefaultRendererFactory rf = new DefaultRendererFactory();
        rf.add(new InGroupPredicate(EDGES), new EdgeRenderer(EDGE_TYPE_LINE));
        m_vis.setRendererFactory(rf);
    }

    private void createLayout() {
        ActionList layout = new ActionList(Activity.INFINITY);
        layout.add(getColors());
        layout.add(new ForceDirectedLayout(GRAPH, true));
        layout.add(new RepaintAction());

        m_vis.putAction("layout", layout);
    }

    private ActionList getColors() {
        ActionList colors = new ActionList();

        ColorAction nStroke = new ColorAction(NODES, VisualItem.STROKECOLOR);
        nStroke.setDefaultColor(ColorLib.gray(100));
        nStroke.add("_hover", ColorLib.gray(50));
        colors.add(nStroke);

        ColorAction nFill = new ColorAction(NODES, VisualItem.FILLCOLOR);
        nFill.setDefaultColor(ColorLib.gray(255));
        nFill.add("_hover", ColorLib.gray(200));
        colors.add(nFill);

        ColorAction nEdges = new ColorAction(EDGES, VisualItem.STROKECOLOR);
        nEdges.setDefaultColor(ColorLib.gray(100));
        colors.add(nEdges);

        ColorAction arrow = new ColorAction(EDGES, VisualItem.FILLCOLOR);
        nEdges.setDefaultColor(ColorLib.gray(100));
        colors.add(arrow);

        return colors;
    }

    public void run() {
        m_vis.run("layout");
    }
}
