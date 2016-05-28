package lv.neueda.jetbrains.plugin.graphdb.domain;

import lv.neueda.jetbrains.plugin.graphdb.common.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.common.GraphRelationship;

public class DumbRelationship implements GraphRelationship {

    private GraphNode start;
    private GraphNode end;

    public DumbRelationship() {}

    public DumbRelationship(GraphNode start, GraphNode end) {
        this.start = start;
        this.end = end;
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
}
