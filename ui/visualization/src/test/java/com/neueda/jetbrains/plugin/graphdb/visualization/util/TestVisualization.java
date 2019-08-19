package com.neueda.jetbrains.plugin.graphdb.visualization.util;


import com.intellij.ui.JBColor;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPropertyContainer;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Shows Graph Visualization component without launching full IDE
 * Prepopulated with different combinations of nodes and relationships
 * Useful for experimentation when working on visualization
 */
@Ignore("For development. Nothing to test.")
public class TestVisualization {
    private static AtomicInteger counter = new AtomicInteger();
    private static PrefuseVisualization vis = new PrefuseVisualization(mockLook());

    @Test
    public void showNoRels() {
        addNode("A single");
        addNode("B single");
    }

    @Test
    public void showSimple() {
        addRel(addNode("A"), addNode("B"));
    }

    @Test
    public void show2Rel() {
        GraphNode r2a = addNode("A 2rel");
        GraphNode r2b = addNode("B 2rel");
        addRel(r2a, r2b);
        addRel(r2b, r2a);
    }

    @Test
    public void show3Nodes() {
        GraphNode tr2a = addNode("A 2rel");
        GraphNode tr2b = addNode("B 2rel");
        GraphNode tr2c = addNode("C 2rel");
        addRel(tr2a, tr2b);
        addRel(tr2b, tr2a);
        addRel(tr2b, tr2c);
        addRel(tr2c, tr2b);
    }

    @Test
    public void show4Rel() {
        GraphNode r4a = addNode("A 4rel");
        GraphNode r4b = addNode("B 4rel");
        addRel(r4a, r4b);
        addRel(r4a, r4b);
        addRel(r4a, r4b);
        addRel(r4b, r4a);
    }

    @Test
    public void showLoop() {
        GraphNode loop = addNode("selfLoop");
        addRel(loop, loop);
    }

    @Test
    public void showMultiLoop() {
        GraphNode loop3 = addNode("selfLoop x3");
        addRel(loop3, loop3);
        addRel(loop3, loop3);
        addRel(loop3, loop3);
    }

    @Test
    public void show2SameDirection() {
        GraphNode sda = addNode("A sd");
        GraphNode sdb = addNode("B sd");
        addRel(sdb, sda);
        addRel(sdb, sda);
    }

    @Test
    public void show5Rel() {
        GraphNode node3 = addNode("A 5rel");
        GraphNode node4 = addNode("B 5rel");
        addRel(node4, node3);
        addRel(node3, node4);
        addRel(node4, node3);
        addRel(node3, node4);
        addRel(node3, node4);
    }

    @Test
    public void show15Rel() {
        GraphNode a = addNode("A 15rel");
        GraphNode b = addNode("B 15rel");
        for (int i = 0; i < 15; i++) {
            addRel(a, b);
        }
    }

    @AfterClass
    public static void show() throws IOException {
        vis.paint();
        JComponent com = vis.getCanvas();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("JComponent Example");
        frame.setSize(1500, 1500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(com);
        frame.setVisible(true);

        System.in.read();
    }

    private static GraphRelationship addRel(final GraphNode startNode, final GraphNode endNode) {
        String id = "" + counter.incrementAndGet();
        return addRel(id, startNode, endNode, "relType", "name", new HashMap<>());
    }

    private static GraphRelationship addRel(String id,
                                            final GraphNode startNode,
                                            final GraphNode endNode,
                                            String type,
                                            final String name,
                                            Map<String, Object> properties) {
        GraphRelationship newRelationship = new GraphRelationship() {
            @Override
            public boolean hasStartAndEndNode() {
                return true;
            }

            @Override
            public String getStartNodeId() {
                return startNode.getId();
            }

            @Override
            public String getEndNodeId() {
                return endNode.getId();
            }

            @Override
            public GraphNode getStartNode() {
                return startNode;
            }

            @Override
            public GraphNode getEndNode() {
                return endNode;
            }

            @Override
            public String getId() {
                return id;
            }

            @Override
            public GraphPropertyContainer getPropertyContainer() {
                return () -> properties;
            }

            @Override
            public List<String> getTypes() {
                return Collections.singletonList(type);
            }

            @Override
            public String getTypesName() {
                return name;
            }

            @Override
            public boolean isTypesSingle() {
                return true;
            }
        };

        vis.addRelation(newRelationship);
        return newRelationship;
    }

    private static GraphNode addNode(String name) {
        String id = "" + counter.incrementAndGet();
        return addNode(id, "nodeType", "name", Collections.singletonMap("name", name));
    }

    private static GraphNode addNode(String id, final String type, final String name, final Map<String, Object> properties) {

        GraphNode newNode = new GraphNode() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public GraphPropertyContainer getPropertyContainer() {
                return () -> properties;
            }

            @Override
            public List<String> getTypes() {
                return Collections.singletonList(type);
            }

            @Override
            public String getTypesName() {
                return name;
            }

            @Override
            public boolean isTypesSingle() {
                return false;
            }
        };
        vis.addNode(newNode);
        return newNode;
    }



    private static LookAndFeelService mockLook() {
        return new LookAndFeelService() {
            @Override
            public Color getBackgroundColor() {
                return JBColor.BLACK;
            }

            @Override
            public Color getBorderColor() {
                return JBColor.RED;
            }

            @Override
            public Color getNodeStrokeColor() {
                return JBColor.GREEN;
            }

            @Override
            public Color getNodeStrokeHoverColor() {
                return JBColor.ORANGE;
            }

            @Override
            public Color getNodeFillColor() {
                return JBColor.CYAN;
            }

            @Override
            public Color getNodeFillHoverColor() {
                return JBColor.DARK_GRAY;
            }

            @Override
            public Color getEdgeStrokeColor() {
                return JBColor.LIGHT_GRAY;
            }

            @Override
            public Color getEdgeFillColor() {
                return JBColor.MAGENTA;
            }

            @Override
            public Color getTextColor() {
                return JBColor.WHITE;
            }

            @Override
            public boolean isGraphViewZoomInverted() {
                return true;
            }
        };
    }


}
