package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPropertyContainer;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import org.neo4j.driver.types.Relationship;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Neo4jBoltRelationship implements GraphRelationship {

    private final String id;
    private final List<String> types;
    private final GraphPropertyContainer propertyContainer;
    private final String startNodeId;
    private final String endNodeId;
    private GraphNode startNode;
    private GraphNode endNode;

    public Neo4jBoltRelationship(Relationship rel) {
        this.id = String.valueOf(rel.id());
        this.types = Collections.singletonList(rel.type());
        this.propertyContainer = new Neo4jBoltPropertyContainer(rel.asMap());

        this.startNodeId = String.valueOf(rel.startNodeId());
        this.endNodeId = String.valueOf(rel.endNodeId());
    }

    public Neo4jBoltRelationship(String id, List<String> types, GraphPropertyContainer propertyContainer, String startNodeId, String endNodeId) {
        this.id = id;
        this.types = types;
        this.propertyContainer = propertyContainer;
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
    }

    @Override
    public String getStartNodeId() {
        return startNodeId;
    }

    @Override
    public String getEndNodeId() {
        return endNodeId;
    }

    public void setStartNode(GraphNode startNode) {
        this.startNode = startNode;
    }

    public void setEndNode(GraphNode endNode) {
        this.endNode = endNode;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public List<String> getTypes() {
        return types;
    }

    @Override
    public String getTypesName() {
        return "type";
    }

    @Override
    public boolean isTypesSingle() {
        return true;
    }

    @Override
    public GraphPropertyContainer getPropertyContainer() {
        return propertyContainer;
    }

    @Override
    public boolean hasStartAndEndNode() {
        return startNode != null && endNode != null;
    }

    @Override
    public GraphNode getStartNode() {
        return startNode;
    }

    @Override
    public GraphNode getEndNode() {
        return endNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Neo4jBoltRelationship that = (Neo4jBoltRelationship) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
