package lv.neueda.jetbrains.plugin.graphdb.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphEntity;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;
import lv.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prefuse.visual.VisualItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.Map;

public class GraphDatabaseSupportToolWindow implements ToolWindowFactory {

    private static final Logger log = LoggerFactory.getLogger(GraphDatabaseSupportToolWindow.class);

    private ToolWindowInteractions toolWindowInteractions;
    private VisualizationApi visualization;

    private JPanel toolWindowContent;
    private JPanel canvas;
    private JPanel contentPane;
    private JBTable entityDataTable;
    private JLabel entityDataTableLabel;
    private JScrollPane contentScrollPane;
    private DefaultTableModel entityDataTableModel;

    public GraphDatabaseSupportToolWindow() {
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // Bootstrap visualisation
        visualization = new PrefuseVisualization();

        // Bootstrap tool window
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

        initializeUiComponents();

        // Bootstrap interactions
        this.toolWindowInteractions = new ToolWindowInteractions(
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
        for( int i = entityDataTableModel.getRowCount() - 1; i >= 0; i-- )
        {
            entityDataTableModel.removeRow(i);
        }

        for (Map.Entry<String, Object> entry : entity.getProperties().entrySet()) {
            Object[] data = {entry.getKey(), entry.getValue()};
            entityDataTableModel.addRow(data);
        }
    }

    public void showTooltip(GraphEntity entity, VisualItem item, MouseEvent e) {
        String id = (String) item.get("id");
        JPopupMenu jpub = new JPopupMenu();
        jpub.add("Id: " + id);
        jpub.show(e.getComponent(), (int) item.getX(), (int) item.getY());
    }
}
