package com.neueda.jetbrains.plugin.graphdb.database.api;

import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;

public interface GraphDatabaseApi {

    GraphQueryResult execute(String query);
}
