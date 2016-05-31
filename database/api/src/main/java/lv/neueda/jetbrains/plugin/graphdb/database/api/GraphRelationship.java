package lv.neueda.jetbrains.plugin.graphdb.database.api;

public interface GraphRelationship {

    String getId();

    GraphNode getStart();

    GraphNode getEnd();
}
