package lv.neueda.jetbrains.plugin.graphdb.database.neo4j;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphNode;
import org.neo4j.driver.v1.types.Node;

public class Neo4jV3Node implements GraphNode {

    private final long id;

    public Neo4jV3Node(Node value) {
        this.id = value.id();
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }
}
