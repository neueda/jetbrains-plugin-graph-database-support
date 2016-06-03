package com.neueda.jetbrains.plugin.graphdb.database;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.dumb.DumbDatabase;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.Neo4jV3Database;

public class DatabaseManager {

    private final DumbDatabase dumbDatabase;
    private final Neo4jV3Database neo4jV3Database;

    public DatabaseManager() {
        this.dumbDatabase = new DumbDatabase();
        this.neo4jV3Database = new Neo4jV3Database("localhost", 7687, null, null);
    }

    public GraphDatabaseApi getActiveDatabase() {
        return neo4jV3Database;
    }
}
