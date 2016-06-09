package com.neueda.jetbrains.plugin.graphdb.jetbrains.database;

import com.intellij.openapi.application.ApplicationManager;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.util.Notifier;
import com.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;

import java.util.concurrent.Future;

public class QueryExecutionService {

    private final VisualizationApi visualization;
    private final DatabaseManager databaseManager;
    private Future<?> runningQuery;

    public QueryExecutionService(VisualizationApi visualization) {
        this.visualization = visualization;
        this.databaseManager = new DatabaseManager();
    }

    public void executeQuery(DataSource dataSource, ExecuteQueryPayload payload) {
        checkForRunningQuery();
        visualization.stop();
        visualization.clear();

        try {
            executeInBackground(dataSource, payload);
        } catch (Exception e) {
            Notifier.error("Query execution", "Error during execution: " + e.toString());
        }
    }

    private synchronized void checkForRunningQuery() {
        if (runningQuery == null) {
            return;
        }

        if (runningQuery.isDone()) {
            runningQuery = null;
        } else {
            runningQuery.cancel(true);
        }
    }

    private synchronized void executeInBackground(DataSource dataSource, ExecuteQueryPayload payload) {
        runningQuery = ApplicationManager.getApplication().executeOnPooledThread(() -> {
            try {
                GraphDatabaseApi database =  databaseManager.getDatabaseFor(dataSource);
                if (database == null) {
                    Notifier.error(
                            "Unknown database",
                            String.format("Database for data source [%s] does not exists", dataSource.dataSourceType));
                    return;
                }
                GraphQueryResult result = database.execute(payload.getContent());

                result.getNodes().forEach(visualization::addNode);
                result.getRelationships().forEach(visualization::addRelation);

                visualization.paint();
                Notifier.info("Query execution", "Query executed successfully!");
            } catch (Exception e) {
                visualization.stop();
                visualization.clear();
                Notifier.error("Query execution", "Error during query execution: " + e.toString());
            }
        });
    }
}
