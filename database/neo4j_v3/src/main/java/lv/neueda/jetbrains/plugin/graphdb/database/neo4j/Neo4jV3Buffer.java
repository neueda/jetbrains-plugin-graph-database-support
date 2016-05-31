package lv.neueda.jetbrains.plugin.graphdb.database.neo4j;

import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import java.util.HashMap;
import java.util.Map;

public class Neo4jV3Buffer {

    private Map<Long, Node> nodes;
    private Map<Long, Relationship> relationships;

    public Neo4jV3Buffer() {
        this.nodes = new HashMap<>();
        this.relationships = new HashMap<>();
    }

    public void addNode(Node node) {
        nodes.put(node.id(), node);
    }

    public void addRelationship(Relationship rel) {
        relationships.put(rel.id(), rel);
    }

    public Map<Long, Node> getNodes() {
        return nodes;
    }

    public Map<Long, Relationship> getRelationships() {
        return relationships;
    }
}
