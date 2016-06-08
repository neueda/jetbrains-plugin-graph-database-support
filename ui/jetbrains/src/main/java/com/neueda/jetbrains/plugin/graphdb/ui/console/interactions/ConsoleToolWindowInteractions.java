package com.neueda.jetbrains.plugin.graphdb.ui.console.interactions;

import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.actions.execute.ExecuteQueryEvent;
import com.neueda.jetbrains.plugin.graphdb.actions.ui.console.CleanCanvasEvent;
import com.neueda.jetbrains.plugin.graphdb.actions.ui.console.ToggleLayoutEvent;
import com.neueda.jetbrains.plugin.graphdb.database.QueryExecutionService;
import com.neueda.jetbrains.plugin.graphdb.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

public class ConsoleToolWindowInteractions {

    private final ConsoleToolWindow window;
    private final MessageBus messageBus;
    private final QueryExecutionService queryExecutionService;
    private final VisualizationApi visualization;

    public ConsoleToolWindowInteractions(ConsoleToolWindow window,
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
        messageBus.connect()
                .subscribe(ToggleLayoutEvent.TOGGLE_LAYOUT_TOPIC, visualization::toggleLayout);
    }

    private void registerVisualisationEvents() {
        visualization.addNodeListener(EventType.CLICK, window::showNodeData);
        visualization.addEdgeListener(EventType.CLICK, window::showRelationshipData);
        visualization.addNodeListener(EventType.HOVER_START, window::showTooltip);
        visualization.addEdgeListener(EventType.HOVER_START, window::showTooltip);
    }
}
