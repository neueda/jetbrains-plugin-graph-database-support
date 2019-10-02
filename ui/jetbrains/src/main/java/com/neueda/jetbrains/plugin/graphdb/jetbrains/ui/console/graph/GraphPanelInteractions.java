package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.ui.console.CleanCanvasEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.QueryExecutionService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryExecutionProcessEvent;
import com.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;
import com.neueda.jetbrains.plugin.graphdb.visualization.events.EventType;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.util.ExceptionWrapper.truncateString;

public class GraphPanelInteractions {

    private final GraphConsoleView graphConsoleView;
    private final MessageBus messageBus;
    private final QueryExecutionService queryExecutionService;
    private final VisualizationApi visualization;

    public GraphPanelInteractions(GraphConsoleView graphConsoleView,
                                  MessageBus messageBus, VisualizationApi visualization) {
        this.graphConsoleView = graphConsoleView;
        this.messageBus = messageBus;
        this.visualization = visualization;
        this.queryExecutionService = new QueryExecutionService(messageBus);

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
                .subscribe(QueryExecutionProcessEvent.QUERY_EXECUTION_PROCESS_TOPIC, new QueryExecutionProcessEvent() {
                    @Override
                    public void executionStarted(DataSourceApi dataSource, ExecuteQueryPayload payload) {
                        visualization.stop();
                        visualization.clear();
                    }

                    @Override
                    public void resultReceived(ExecuteQueryPayload payload, GraphQueryResult result) {
                        result.getNodes().forEach(visualization::addNode);
                        result.getRelationships().forEach(visualization::addRelation);
                    }

                    @Override
                    public void postResultReceived(ExecuteQueryPayload payload) {
                        visualization.paint();
                    }

                    @Override
                    public void handleError(ExecuteQueryPayload payload, Exception exception) {
                        String errorMessage = exception.getMessage() == null ? "Error occurred" : "Error occurred: " + truncateString(exception.getMessage(), 120);
                        payload.getEditor().ifPresent(editor -> HintManager.getInstance().showErrorHint(editor, errorMessage));

                        visualization.stop();
                        visualization.clear();
                    }

                    @Override
                    public void executionCompleted(ExecuteQueryPayload payload) {
                    }
                });
    }

    private void registerVisualisationEvents() {
        visualization.addNodeListener(EventType.CLICK, graphConsoleView.getGraphPanel()::showNodeData);
        visualization.addEdgeListener(EventType.CLICK, graphConsoleView.getGraphPanel()::showRelationshipData);
        visualization.addNodeListener(EventType.HOVER_START, graphConsoleView.getGraphPanel()::showTooltip);
        visualization.addNodeListener(EventType.HOVER_END, graphConsoleView.getGraphPanel()::hideTooltip);
        visualization.addEdgeListener(EventType.HOVER_START, graphConsoleView.getGraphPanel()::showTooltip);
        visualization.addEdgeListener(EventType.HOVER_END, graphConsoleView.getGraphPanel()::hideTooltip);
    }
}
