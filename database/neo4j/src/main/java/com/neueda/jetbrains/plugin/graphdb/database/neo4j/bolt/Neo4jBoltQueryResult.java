package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt;

import com.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphRelationship;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Neo4jBoltQueryResult implements GraphQueryResult {

    private final List<GraphNode> nodes;
    private final List<GraphRelationship> relationships;

    public Neo4jBoltQueryResult(Neo4jBoltBuffer neo4JBoltBuffer) {
        nodes = new ArrayList<>();
        relationships = new ArrayList<>();

        // nodes
        Map<Long, GraphNode> foundNodes = new HashMap<>();
        Map<Long, Node> bufferNodes = neo4JBoltBuffer.getNodes();
        for (Map.Entry<Long, Node> entry : bufferNodes.entrySet()) {
            foundNodes.put(entry.getKey(), new Neo4jBoltNode(entry.getValue()));
        }
        nodes.addAll(foundNodes.values());

        // relationships
        Map<Long, Relationship> bufferRelationships = neo4JBoltBuffer.getRelationships();
        for (Relationship relationship : bufferRelationships.values()) {
            GraphNode startNode = foundNodes.get(relationship.startNodeId());
            GraphNode endNode = foundNodes.get(relationship.endNodeId());

            if (startNode != null && endNode != null) {
                relationships.add(new Neo4jBoltRelationship(relationship, startNode, endNode));
            } else {
                // either start or end node are not present in result
            }
        }

    }

    @Override
    public List<GraphNode> getNodes() {
        return nodes;
    }

    @Override
    public List<GraphRelationship> getRelationships() {
        return relationships;
    }
}
