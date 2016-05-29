package lv.neueda.jetbrains.plugin.graphdb;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.messages.MessageBus;
import lv.neueda.jetbrains.plugin.graphdb.event.ExecuteQueryEvent;
import lv.neueda.jetbrains.plugin.graphdb.execution.QueryExecutionService;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JPanel;

public class GraphDatabaseSupportToolWindow implements ToolWindowFactory {

    private static final Logger log = LoggerFactory.getLogger(GraphDatabaseSupportToolWindow.class);

    private ToolWindow toolWindow;
    private QueryExecutionService queryExecutionService;

    private JPanel toolWindowContent;
    private JPanel canvas;

    public GraphDatabaseSupportToolWindow() {
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        VisualizationImpl visualization = new VisualizationImpl();
        this.toolWindow = toolWindow;
        this.queryExecutionService = new QueryExecutionService(visualization);

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
        messageBus.connect()
                .subscribe(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC, queryExecutionService::executeQuery);
    }

    /**
     * Custom initialization.
     */
    private void createUIComponents() {
        canvas = new JPanel();
    }
}
