package lv.neueda.jetbrains.plugin.graphdb.database.dumb;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;

import java.util.Collections;
import java.util.Map;

public class DumbNode implements GraphNode {

    private String id;

    public DumbNode(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Map<String, Object> getProperties() {
        return Collections.emptyMap();
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
