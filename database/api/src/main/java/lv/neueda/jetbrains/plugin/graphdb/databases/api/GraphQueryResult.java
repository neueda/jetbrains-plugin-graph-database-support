package lv.neueda.jetbrains.plugin.graphdb.databases.api;

import java.util.List;

public interface GraphQueryResult {

    List<GraphNode> getNodes();

    List<GraphRelationship> getRelationships();
}
