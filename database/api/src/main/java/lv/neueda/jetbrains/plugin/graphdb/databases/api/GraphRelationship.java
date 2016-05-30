package lv.neueda.jetbrains.plugin.graphdb.databases.api;

public interface GraphRelationship {

    String getId();

    GraphNode getStart();

    GraphNode getEnd();
}
