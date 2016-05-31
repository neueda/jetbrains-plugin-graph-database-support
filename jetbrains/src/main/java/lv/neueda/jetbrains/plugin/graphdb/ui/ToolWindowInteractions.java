package lv.neueda.jetbrains.plugin.graphdb.ui;

import com.intellij.util.messages.MessageBus;
import lv.neueda.jetbrains.plugin.graphdb.bus.ExecuteQueryEvent;
import lv.neueda.jetbrains.plugin.graphdb.database.QueryExecutionService;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;

public class ToolWindowInteractions {

    private final MessageBus messageBus;
    private final QueryExecutionService queryExecutionService;

    public ToolWindowInteractions(MessageBus messageBus, VisualizationApi visualization) {
        this.messageBus = messageBus;
        this.queryExecutionService = new QueryExecutionService(visualization);

        registerMessageBusSubscribers();
    }

    private void registerMessageBusSubscribers() {
        messageBus.connect()
                .subscribe(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC, (query) -> queryExecutionService.executeQuery(query));
    }
}
