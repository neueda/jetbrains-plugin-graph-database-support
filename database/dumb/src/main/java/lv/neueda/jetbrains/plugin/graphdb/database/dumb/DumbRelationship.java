package lv.neueda.jetbrains.plugin.graphdb.database.dumb;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;

import java.util.Collections;
import java.util.Map;

public class DumbRelationship implements GraphRelationship {

    private String id;
    private GraphNode start;
    private GraphNode end;

    public DumbRelationship(String id) {
        this.id = id;
    }

    public DumbRelationship(String id, GraphNode start, GraphNode end) {
        this.id = id;
        this.start = start;
        this.end = end;
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
    public GraphNode getStart() {
        return start;
    }

    public void setStart(GraphNode start) {
        this.start = start;
    }

    @Override
    public GraphNode getEnd() {
        return end;
    }

    public void setEnd(GraphNode end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "DumbRelationship{" +
                "id='" + id + '\'' +
                '}';
    }
}
