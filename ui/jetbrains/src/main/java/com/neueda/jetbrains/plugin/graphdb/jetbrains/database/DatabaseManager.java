package com.neueda.jetbrains.plugin.graphdb.jetbrains.database;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltDatabase;
import org.jetbrains.annotations.Nullable;

public class DatabaseManager {

    public DatabaseManager() {
    }

    @Nullable
    public GraphDatabaseApi getDatabaseFor(DataSource dataSource) {
        switch (dataSource.getDataSourceType()) {
            case NEO4J_BOLT:
                return new Neo4jBoltDatabase(dataSource.getConfiguration());
            default:
                return null;
        }
    }
}
