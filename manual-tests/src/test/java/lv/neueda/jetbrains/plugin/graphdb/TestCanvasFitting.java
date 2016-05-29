package lv.neueda.jetbrains.plugin.graphdb;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationImpl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class TestCanvasFitting {

    public static void main(String[] args) {
        VisualizationImpl v = new VisualizationImpl();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(1, 2);
        JPanel jPanel = new JPanel(gridLayoutManager);
        GridConstraints constraints = new GridConstraints(
                0, 0, 1, 1,
                GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1));
        jPanel.add(v.getCanvas(), constraints);

        GridConstraints constraints2 = new GridConstraints(
                0, 1, 1, 1,
                GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1));
        jPanel.add(new JPanel(), constraints2);

        JFrame frame = new JFrame("Liquid Lama");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(jPanel);
        frame.setSize(1000, 800);
        frame.pack();
        frame.setVisible(true);

        v.paint();
    }
}
