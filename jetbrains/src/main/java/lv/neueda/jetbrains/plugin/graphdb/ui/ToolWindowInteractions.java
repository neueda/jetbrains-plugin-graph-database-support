package lv.neueda.jetbrains.plugin.graphdb.ui;

import com.intellij.util.messages.MessageBus;
import lv.neueda.jetbrains.plugin.graphdb.bus.ExecuteQueryEvent;
import lv.neueda.jetbrains.plugin.graphdb.database.QueryExecutionService;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import lv.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

public class ToolWindowInteractions {

    private final GraphDatabaseSupportToolWindow window;
    private final MessageBus messageBus;
    private final QueryExecutionService queryExecutionService;
    private final VisualizationApi visualization;

    public ToolWindowInteractions(GraphDatabaseSupportToolWindow window,
                                  MessageBus messageBus, VisualizationApi visualization) {
        this.window = window;
        this.messageBus = messageBus;
        this.visualization = visualization;
        this.queryExecutionService = new QueryExecutionService(visualization);

        registerMessageBusSubscribers();
        registerVisualisationEvents();
    }

    private void registerMessageBusSubscribers() {
        messageBus.connect()
                .subscribe(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC, (query) -> queryExecutionService.executeQuery(query));
    }

    private void registerVisualisationEvents() {
        visualization.addNodeListener(EventType.CLICK, window::showNodeData);
        visualization.addEdgeListener(EventType.CLICK, window::showRelationshipData);
    }
}
