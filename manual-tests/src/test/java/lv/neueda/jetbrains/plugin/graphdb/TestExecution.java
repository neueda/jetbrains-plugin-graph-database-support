package lv.neueda.jetbrains.plugin.graphdb;

import lv.neueda.jetbrains.plugin.graphdb.domain.DumbDatabase;
import lv.neueda.jetbrains.plugin.graphdb.database.QueryExecutionService;
import lv.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class TestExecution {

    public static void main(String[] args) {
        PrefuseVisualization v = new PrefuseVisualization();

        GridLayout layout = new GridLayout(0, 1);
        JPanel jPanel = new JPanel(layout);
        jPanel.add(v.getCanvas());

        JFrame frame = new JFrame("Liquid Lama");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(jPanel);
        frame.setSize(1000, 800);
        frame.pack();
        frame.setVisible(true);

        v.paint();

        DumbDatabase dumbDatabase = new DumbDatabase();
        QueryExecutionService service = new QueryExecutionService(v);
        service.executeQuery(dumbDatabase, "query for dumb database");
    }
}
