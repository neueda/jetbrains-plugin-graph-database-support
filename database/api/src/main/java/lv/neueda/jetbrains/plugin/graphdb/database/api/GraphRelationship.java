package lv.neueda.jetbrains.plugin.graphdb.database.api;

public interface GraphRelationship extends GraphEntity {

    GraphNode getStart();

    GraphNode getEnd();
}
