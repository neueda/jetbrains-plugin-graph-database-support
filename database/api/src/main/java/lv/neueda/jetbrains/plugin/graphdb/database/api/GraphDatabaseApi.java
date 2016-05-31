package lv.neueda.jetbrains.plugin.graphdb.database.api;

public interface GraphDatabaseApi {

    GraphQueryResult execute(String query);
}
