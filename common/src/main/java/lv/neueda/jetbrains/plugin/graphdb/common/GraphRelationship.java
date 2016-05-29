package lv.neueda.jetbrains.plugin.graphdb.common;

public interface GraphRelationship {
    String getId();
    GraphNode getStart();
    GraphNode getEnd();
}
