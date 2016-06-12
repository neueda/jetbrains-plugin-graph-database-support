package com.neueda.jetbrains.plugin.graphdb.visualization;

import com.intellij.ui.border.CustomLineBorder;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.NodeCallback;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.RelationshipCallback;
import com.neueda.jetbrains.plugin.graphdb.visualization.listeners.NodeListener;
import com.neueda.jetbrains.plugin.graphdb.visualization.listeners.RelationshipListener;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import com.neueda.jetbrains.plugin.graphdb.visualization.settings.LayoutProvider;
import com.neueda.jetbrains.plugin.graphdb.visualization.settings.SchemaProvider;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.controls.*;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.RendererFactory;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.GraphColumns.*;
import static com.neueda.jetbrains.plugin.graphdb.visualization.constants.GraphGroups.*;
import static com.neueda.jetbrains.plugin.graphdb.visualization.settings.RendererProvider.*;
import static java.util.Collections.unmodifiableSet;
import static prefuse.Constants.SHAPE_ELLIPSE;

public class GraphDisplay extends Display {

    private static final boolean DIRECTED = true;

    private static final String LAYOUT = "layout";
    private static final String REPAINT = "repaint";

    private Graph graph;

    private Map<String, Node> nodeMap = new HashMap<>();
    private Map<String, GraphNode> graphNodeMap = new HashMap<>();
    private Map<String, GraphRelationship> graphRelationshipMap = new HashMap<>();

    private static final Set<String> TITLE_PROPERTIES = unmodifiableSet(newHashSet("name", "title"));

    public GraphDisplay(LookAndFeelService lookAndFeelService) {
        super(new Visualization());

        setBackground(lookAndFeelService.getBackgroundColor());
        setBorder(new CustomLineBorder(new Insets(0, 1, 0, 1)));

        graph = new Graph(DIRECTED);
        graph.addColumn(ID, String.class);
        graph.addColumn(TYPE, String.class);
        graph.addColumn(TITLE, String.class);

        m_vis.addGraph(GRAPH, graph, null, SchemaProvider.provideNodeSchema(), SchemaProvider.provideEdgeSchema());
        m_vis.setInteractive(EDGES, null, false);
        m_vis.setValue(NODES, null, VisualItem.SHAPE, SHAPE_ELLIPSE);

        m_vis.addDecorators(NODE_LABEL, NODES, SchemaProvider.provideFontSchema());

        m_vis.setRendererFactory(setupRenderer());

        m_vis.putAction(LAYOUT, LayoutProvider.forceLayout(lookAndFeelService));
        m_vis.putAction(REPAINT, LayoutProvider.repaintLayout(lookAndFeelService));

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
        String type = graphNode.getTypes().size() > 0 ? graphNode.getTypes().get(0) : "";
        node.set(TYPE, type);
        node.set(TITLE, getDisplayProperty(graphNode));

        nodeMap.put(graphNode.getId(), node);
        graphNodeMap.put(graphNode.getId(), graphNode);
    }

    private String getDisplayProperty(GraphNode node) {
        String backup = null;
        for (Map.Entry<String, Object> entry : node.getPropertyContainer().getProperties().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                if (TITLE_PROPERTIES.contains(entry.getKey()))
                    return (String) value;

                backup = (String) value;
            }
        }

        if (backup == null)
            return node.getId();
        else
            return backup;

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

    private RendererFactory setupRenderer() {
        DefaultRendererFactory rendererFactory = new DefaultRendererFactory(nodeRenderer(), edgeRenderer());
        rendererFactory.add(new InGroupPredicate(NODE_LABEL), labelRenderer());

        return rendererFactory;
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
