package lv.neueda.jetbrains.plugin.graphdb.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.messages.MessageBus;
import lv.neueda.jetbrains.plugin.graphdb.domain.DumbDatabase;
import lv.neueda.jetbrains.plugin.graphdb.event.ExecuteQueryEvent;
import lv.neueda.jetbrains.plugin.graphdb.execution.QueryExecutionService;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import lv.neueda.jetbrains.plugin.graphdb.visualization.PrefuseVisualization;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class GraphDatabaseSupportToolWindow implements ToolWindowFactory {

    private static final Logger log = LoggerFactory.getLogger(GraphDatabaseSupportToolWindow.class);

    private ToolWindow toolWindow;
    private DumbDatabase dumbDatabase;
    private QueryExecutionService queryExecutionService;

    private JPanel toolWindowContent;
    private JPanel canvas;
    private JScrollPane content;

    public GraphDatabaseSupportToolWindow() {
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        PrefuseVisualization visualization = new PrefuseVisualization();
        this.toolWindow = toolWindow;
        this.queryExecutionService = new QueryExecutionService(visualization);
        this.dumbDatabase = new DumbDatabase();

        initializeContent(visualization);
        registerSubscriber(project.getMessageBus());
    }

    private void initializeContent(VisualizationApi visualization) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

        canvas.add(visualization.getCanvas());
    }

    private void registerSubscriber(MessageBus messageBus) {
        messageBus.connect().subscribe(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC,
                (query) -> queryExecutionService.executeQuery(dumbDatabase, query));
    }

    /**
     * Custom initialization.
     */
    private void createUIComponents() {
        canvas = new JPanel(new GridLayout(0, 1));
    }
}
