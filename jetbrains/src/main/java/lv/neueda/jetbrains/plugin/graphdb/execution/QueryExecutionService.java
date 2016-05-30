package lv.neueda.jetbrains.plugin.graphdb.execution;

import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphDatabase;
import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphQueryResult;
import lv.neueda.jetbrains.plugin.graphdb.visualization.VisualizationApi;

public class QueryExecutionService {

    private final VisualizationApi visualization;

    public QueryExecutionService(VisualizationApi visualization) {
        this.visualization = visualization;
    }

    public void executeQuery(GraphDatabase database, String query) {
        visualization.clear();

        GraphQueryResult result = database.execute(query);

        result.getNodes().forEach(visualization::addNode);
        result.getRelationships().forEach(visualization::addRelation);

        visualization.paint();
    }
}
