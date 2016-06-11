package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.ui.console.CleanCanvasEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.QueryExecutionService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

public class GraphPanelInteractions {

    private final ConsoleToolWindow window;
    private final MessageBus messageBus;
    private final QueryExecutionService queryExecutionService;
    private final VisualizationApi visualization;

    public GraphPanelInteractions(ConsoleToolWindow window,
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
        messageBus.connect()
                .subscribe(CleanCanvasEvent.CLEAN_CANVAS_TOPIC, () -> {
                    visualization.stop();
                    visualization.clear();
                    visualization.paint();
                });
    }

    private void registerVisualisationEvents() {
        visualization.addNodeListener(EventType.CLICK, window.getGraphPanel()::showNodeData);
        visualization.addEdgeListener(EventType.CLICK, window.getGraphPanel()::showRelationshipData);
        visualization.addNodeListener(EventType.HOVER_START, window.getGraphPanel()::showTooltip);
        visualization.addEdgeListener(EventType.HOVER_START, window.getGraphPanel()::showTooltip);
    }
}
