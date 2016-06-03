package com.neueda.jetbrains.plugin.graphdb.ui.interactions;

import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.bus.ExecuteQueryEvent;
import com.neueda.jetbrains.plugin.graphdb.database.QueryExecutionService;
import com.neueda.jetbrains.plugin.graphdb.ui.GraphDatabaseConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

public class GraphDatabaseConsoleToolWindowInteractions {

    private final GraphDatabaseConsoleToolWindow window;
    private final MessageBus messageBus;
    private final QueryExecutionService queryExecutionService;
    private final VisualizationApi visualization;

    public GraphDatabaseConsoleToolWindowInteractions(GraphDatabaseConsoleToolWindow window,
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
                .subscribe(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC, queryExecutionService::executeQuery);
    }

    private void registerVisualisationEvents() {
        visualization.addNodeListener(EventType.CLICK, window::showNodeData);
        visualization.addEdgeListener(EventType.CLICK, window::showRelationshipData);
        visualization.addNodeListener(EventType.HOVER_START, window::showTooltip);
        visualization.addEdgeListener(EventType.HOVER_START, window::showTooltip);
    }
}
