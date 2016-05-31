package lv.neueda.jetbrains.plugin.graphdb.database.neo4j;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;
import org.neo4j.driver.v1.types.Relationship;

public class Neo4jV3Relationship implements GraphRelationship {

    private final long id;
    private final GraphNode startNode;
    private final GraphNode endNode;

    public Neo4jV3Relationship(Relationship relationship,
                               GraphNode startNode, GraphNode endNode) {
        this.id = relationship.id();
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public GraphNode getStart() {
        return startNode;
    }

    @Override
    public GraphNode getEnd() {
        return endNode;
    }
}
