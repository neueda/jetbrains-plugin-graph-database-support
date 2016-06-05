package com.neueda.jetbrains.plugin.graphdb.ui.console;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.popup.BalloonPopupBuilderImpl;
import com.intellij.ui.table.JBTable;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.ui.console.interactions.ConsoleToolWindowInteractions;
import com.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import com.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import org.jetbrains.annotations.NotNull;
import prefuse.visual.VisualItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Map;

public class ConsoleToolWindow implements ToolWindowFactory {

    private ConsoleToolWindowInteractions windowInteractions;
    private VisualizationApi visualization;

    private JPanel toolWindowContent;
    private JPanel canvas;
    private JPanel contentPane;
    private JBTable entityDataTable;
    private JLabel entityDataTableLabel;
    private JScrollPane contentScrollPane;
    private DefaultTableModel entityDataTableModel;

    private BalloonBuilder balloonPopupBuilder;
    private Balloon balloon;
    private JBLabel balloonLabel = new JBLabel();

    private static final int LABEL_TEXT_WIDTH = 300;

    public ConsoleToolWindow() {
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // Bootstrap visualisation
        visualization = new PrefuseVisualization();

        // Bootstrap tool window
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

        balloonBuilder();

        initializeUiComponents();

        // Bootstrap interactions
        this.windowInteractions = new ConsoleToolWindowInteractions(
                this,
                project.getMessageBus(),
                visualization);
    }

    /**
     * Custom initialization.
     */
    private void createUIComponents() {
        canvas = new JPanel(new GridLayout(0, 1));
    }

    private void initializeUiComponents() {
        canvas.add(visualization.getCanvas());

        entityDataTableModel = new DefaultTableModel();
        entityDataTableModel.addColumn("key");
        entityDataTableModel.addColumn("value");

        entityDataTable.setModel(entityDataTableModel);
    }

    public void showNodeData(GraphNode node, VisualItem item, MouseEvent e) {
        entityDataTableLabel.setText(node.getId() + ", Node:<TODOLABELS>");
        showEntityData(node);
    }

    public void showRelationshipData(GraphRelationship relationship, VisualItem item, MouseEvent e) {
        entityDataTableLabel.setText(relationship.getId() + ", Relationship:<TODOTYPE>");
        showEntityData(relationship);
    }

    private void showEntityData(GraphEntity entity) {
        for (int i = entityDataTableModel.getRowCount() - 1; i >= 0; i--) {
            entityDataTableModel.removeRow(i);
        }

        for (Map.Entry<String, Object> entry : entity.getProperties().entrySet()) {
            Object[] data = {entry.getKey(), entry.getValue()};
            entityDataTableModel.addRow(data);
        }
    }

    public void showTooltip(GraphEntity entity, VisualItem item, MouseEvent e) {
        if (balloon != null && !balloon.isDisposed())
            balloon.hide();

        balloonPopupBuilder.setTitle(entity.getRepresentation());
        balloonLabel.setText(getFiveProperties(entity.getProperties()));

        balloon = balloonPopupBuilder.createBalloon();
        Container panel = e.getComponent().getParent();

        final int MAGIC_NUMBER = 15;
        int heightOffset = balloon.getPreferredSize().height / 2 + MAGIC_NUMBER;

        int widthOffset;
        if(e.getX() > panel.getWidth() / 2) {
            widthOffset = balloon.getPreferredSize().width / 2;
        } else {
            widthOffset = panel.getWidth() - balloon.getPreferredSize().width / 2;
        }

        balloon.show(new RelativePoint(panel, new Point(widthOffset, heightOffset)), Balloon.Position.below);
    }

    private String getFiveProperties(Map<String, Object> properties) {
        StringBuilder sb = new StringBuilder();

        properties.entrySet().stream()
                .limit(5)
                .forEach(entry -> sb
                        .append("<p width=\"" + LABEL_TEXT_WIDTH + "px\"><b>")
                        .append(entry.getKey())
                        .append("</b>: ")
                        .append(entry.getValue())
                        .append("</p>"));

        return "<html>" + sb.toString() + "</html>";
    }

    private void balloonBuilder() {
        final BalloonPopupBuilderImpl builder = new BalloonPopupBuilderImpl(null, balloonLabel);
        final Color bg = UIManager.getColor("Panel.background");
        final Color borderOriginal = Color.darkGray;
        final Color border = ColorUtil.toAlpha(borderOriginal, 75);
        builder
                .setShowCallout(false)
                .setDialogMode(false)
                .setAnimationCycle(20)
                .setFillColor(bg).setBorderColor(border).setHideOnClickOutside(true)
                .setHideOnKeyOutside(false)
                .setHideOnAction(false)
                .setCloseButtonEnabled(false)
                .setShadow(true);

        balloonPopupBuilder = builder;
    }
}
