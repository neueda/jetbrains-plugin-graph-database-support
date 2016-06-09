package com.neueda.jetbrains.plugin.graphdb.database.api.query;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;

import java.util.List;

public interface GraphQueryResultRow {

    Object getValue(GraphQueryResultColumn column);

    List<GraphNode> getNodes();

    List<GraphRelationship> getRelationships();
}
