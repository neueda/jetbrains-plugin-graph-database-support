package com.neueda.jetbrains.plugin.graphdb;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.dumb.DumbDatabase;
import com.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class TestSimpleGraph {

    public static void main(String[] argv) {
        PrefuseVisualization v = new PrefuseVisualization();
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

        v.addNodeListener(EventType.CLICK, (id, item, event) -> System.out.println("Node clicked: " + id));
        v.addNodeListener(EventType.HOVER_START, (id, item, event) -> System.out.println("Node hovered: " + id));
        v.addEdgeListener(EventType.CLICK, (id, item, event) -> System.out.println("Edge clicked: " + id));
        v.addEdgeListener(EventType.HOVER_START, (id, item, event) -> System.out.println("Edge hovered: " + id));

        JFrame frame = new JFrame("Liquid Lama");
        frame.getContentPane().add(v.getCanvas());
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        v.paint();
    }

}
