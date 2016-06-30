package com.neueda.jetbrains.plugin.graphdb.jetbrains.database;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltDatabase;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;

public class DatabaseManagerServiceImpl implements DatabaseManagerService {

    public DatabaseManagerServiceImpl() {
    }

    public GraphDatabaseApi getDatabaseFor(DataSource dataSource) {
        switch (dataSource.getDataSourceType()) {
            case NEO4J_BOLT:
                return new Neo4jBoltDatabase(dataSource.getConfiguration());
            default:
                throw new RuntimeException(String.format("Database for data source [%s] does not exists", dataSource.dataSourceType));
        }
    }
}
