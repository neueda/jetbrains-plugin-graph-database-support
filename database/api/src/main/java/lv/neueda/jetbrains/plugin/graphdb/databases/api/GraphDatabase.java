package lv.neueda.jetbrains.plugin.graphdb.databases.api;

public interface GraphDatabase {

    GraphQueryResult execute(String query);
}
