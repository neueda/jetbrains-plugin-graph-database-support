package lv.neueda.jetbrains.plugin.graphdb.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import lv.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class GraphDatabaseSupportToolWindow implements ToolWindowFactory {

    private static final Logger log = LoggerFactory.getLogger(GraphDatabaseSupportToolWindow.class);

    private ToolWindowInteractions toolWindowInteractions;

    private JPanel toolWindowContent;
    private JPanel canvas;
    private JScrollPane content;

    public GraphDatabaseSupportToolWindow() {
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // Bootstrap visualisation
        VisualizationApi visualization = new PrefuseVisualization();

        // Bootstrap tool window
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
        canvas.add(visualization.getCanvas());

        // Bootstrap interactions
        this.toolWindowInteractions = new ToolWindowInteractions(
                project.getMessageBus(),
                visualization);
    }

    /**
     * Custom initialization.
     */
    private void createUIComponents() {
        canvas = new JPanel(new GridLayout(0, 1));
    }
}
