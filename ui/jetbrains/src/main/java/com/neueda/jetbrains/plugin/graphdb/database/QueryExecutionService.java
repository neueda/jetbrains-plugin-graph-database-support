package com.neueda.jetbrains.plugin.graphdb.database;

import com.neueda.jetbrains.plugin.graphdb.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.ui.util.Notifier;
import com.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;

public class QueryExecutionService {

    private final VisualizationApi visualization;
    private final DatabaseManager databaseManager;

    public QueryExecutionService(VisualizationApi visualization) {
        this.visualization = visualization;
        this.databaseManager = new DatabaseManager();
    }

    public void executeQuery(DataSource dataSource, ExecuteQueryPayload payload) {
        visualization.stop();
        visualization.clear();

        try {
            GraphDatabaseApi database = databaseManager.getDatabaseFor(dataSource);
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
            Notifier.error("Query execution", "Error during execution: " + e.toString());
            throw e;
        }
    }
}
