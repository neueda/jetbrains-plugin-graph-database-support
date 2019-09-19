package com.neueda.jetbrains.plugin.graphdb.jetbrains.database;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltDatabase;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.OpenCypherGremlinDatabase;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;

public class DatabaseManagerServiceImpl implements DatabaseManagerService {

    public DatabaseManagerServiceImpl() {
    }

    public GraphDatabaseApi getDatabaseFor(DataSourceApi dataSource) {
        switch (dataSource.getDataSourceType()) {
            case NEO4J_BOLT:
                return new Neo4jBoltDatabase(dataSource.getConfiguration());
            case OPENCYPHER_GREMLIN:
                return new OpenCypherGremlinDatabase(dataSource.getConfiguration());
            default:
                throw new RuntimeException(String.format("Database for data source [%s] does not exists", dataSource.getDataSourceType()));
        }
    }
}
