package lv.neueda.jetbrains.plugin.graphdb.database.dumb;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;

public class DumbNode implements GraphNode {

    private String id;

    public DumbNode(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DumbNode{" +
                "id='" + id + '\'' +
                '}';
    }
}
