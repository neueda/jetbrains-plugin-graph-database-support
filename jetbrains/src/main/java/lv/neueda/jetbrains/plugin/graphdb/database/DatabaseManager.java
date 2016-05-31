package lv.neueda.jetbrains.plugin.graphdb.database;

import lv.neueda.jetbrains.plugin.graphdb.databases.api.GraphDatabase;
import lv.neueda.jetbrains.plugin.graphdb.domain.DumbDatabase;

public class DatabaseManager {

    private final DumbDatabase dumbDatabase;

    public DatabaseManager() {
        this.dumbDatabase = new DumbDatabase();
    }

    public GraphDatabase getActiveDatabase() {
        return dumbDatabase;
    }
}
