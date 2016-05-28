package lv.neueda.jetbrains.plugin.graphdb.visualization;

import lv.neueda.jetbrains.plugin.graphdb.visualization.domain.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.visualization.domain.GraphRelationship;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class VisualizationImpl implements VisualizationApi {

    private GraphDisplay display = new GraphDisplay();

    @Override
    public void addNode(GraphNode node) {
        display.addNode(node);
    }

    @Override
    public void addRelation(GraphNode start, GraphNode end) {
        display.addRelationship(new GraphRelationship(start, end));
    }

    @Override
    public void clear() {

    }

    @Override
    public void paint() {

    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Liquid Lama");
        frame.getContentPane().add(display);
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        display.run();
    }

}
