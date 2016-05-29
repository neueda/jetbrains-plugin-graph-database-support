package lv.neueda.jetbrains.plugin.graphdb;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.domain.DumbDatabase;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationImpl;
import lv.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class TestSimpleGraph {

    public static void main(String[] argv) {
        VisualizationImpl v = new VisualizationImpl();
        DumbDatabase db = new DumbDatabase();

        GraphNode node1 = db.createNode();
        GraphNode node2 = db.createNode();
        GraphNode node3 = db.createNode();

        v.addNode(node1);
        v.addNode(node2);
        v.addNode(node3);

        v.addRelation(db.createRelationship(node1, node2));
        v.addRelation(db.createRelationship(node1, node3));
        v.addRelation(db.createRelationship(node2, node3));

        v.addNodeListener(EventType.CLICK, (id) -> System.out.println("Node clicked: " + id));
        v.addNodeListener(EventType.HOVER, (id) -> System.out.println("Node hovered: " + id));
        v.addEdgeListener(EventType.CLICK, (id) -> System.out.println("Edge clicked: " + id));
        v.addEdgeListener(EventType.HOVER, (id) -> System.out.println("Edge hovered: " + id));

        JFrame frame = new JFrame("Liquid Lama");
        frame.getContentPane().add(v.getCanvas());
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        v.paint();
    }

}

