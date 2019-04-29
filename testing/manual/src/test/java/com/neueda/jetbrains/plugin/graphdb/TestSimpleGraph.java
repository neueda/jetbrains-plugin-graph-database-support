package com.neueda.jetbrains.plugin.graphdb;

import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltConfiguration;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltDatabase;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.services.IdeaLookAndFeelService;
import com.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

import javax.swing.JFrame;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class TestSimpleGraph {

    public static void main(String[] argv) {
        PrefuseVisualization v = new PrefuseVisualization(new IdeaLookAndFeelService());

        Map<String, String> config = new HashMap<>();
        config.put(Neo4jBoltConfiguration.HOST, "localhost");
//        config.put(Neo4jBoltConfiguration.PASSWORD)
//        config.put(Neo4jBoltConfiguration.PORT)
//        config.put(Neo4jBoltConfiguration.USER)
        Neo4jBoltDatabase db = new Neo4jBoltDatabase(config);

        GraphQueryResult execute = db.execute("MATCH (n:TEST)-[r]-(n) RETURN * LIMIT 1;");
//        GraphQueryResult execute = db.execute("MATCH (n)-[r]-(m) RETURN * LIMIT 10;");
        execute.getNodes().forEach(v::addNode);
        execute.getRelationships().forEach(v::addRelation);

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

