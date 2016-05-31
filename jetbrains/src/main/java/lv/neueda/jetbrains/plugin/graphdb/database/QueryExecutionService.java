package lv.neueda.jetbrains.plugin.graphdb.database;

import lv.neueda.jetbrains.plugin.graphdb.bus.ExecuteQueryPayload;
import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphDatabase;
import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphQueryResult;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;

public class QueryExecutionService {

    private final VisualizationApi visualization;
    private final DatabaseManager databaseManager;

    public QueryExecutionService(VisualizationApi visualization) {
        this.visualization = visualization;
        this.databaseManager = new DatabaseManager();
    }

    public void executeQuery(ExecuteQueryPayload payload) {
        visualization.clear();

        GraphDatabase database = databaseManager.getActiveDatabase();
        GraphQueryResult result = database.execute(payload.getContent());

        result.getNodes().forEach(visualization::addNode);
        result.getRelationships().forEach(visualization::addRelation);

        visualization.paint();
    }
}
