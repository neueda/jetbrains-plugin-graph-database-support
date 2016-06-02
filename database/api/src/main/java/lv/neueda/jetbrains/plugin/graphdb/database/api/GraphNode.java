package lv.neueda.jetbrains.plugin.graphdb.database.api;

public interface GraphNode extends GraphEntity {

    default String getRepresentation() {
        return "Node[" + getId() + "]";
    }
}
