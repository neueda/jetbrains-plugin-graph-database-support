package lv.neueda.jetbrains.plugin.graphdb.domain;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;

public class DumbNode implements GraphNode {

    private String id;

    public DumbNode() {}

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
}
