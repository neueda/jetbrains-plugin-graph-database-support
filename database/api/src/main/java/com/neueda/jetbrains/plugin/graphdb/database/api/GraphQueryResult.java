package com.neueda.jetbrains.plugin.graphdb.database.api;

import java.util.List;

public interface GraphQueryResult {

    List<GraphNode> getNodes();

    List<GraphRelationship> getRelationships();
}
