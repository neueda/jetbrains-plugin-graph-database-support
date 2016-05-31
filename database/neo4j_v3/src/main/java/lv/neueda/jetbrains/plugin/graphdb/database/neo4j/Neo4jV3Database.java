package lv.neueda.jetbrains.plugin.graphdb.database.neo4j;

import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import lv.neueda.jetbrains.plugin.graphdb.database.api.GraphQueryResult;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;

import java.util.List;
import java.util.Map;

/**
 * Communicates with Neo4j 3.0+ database using Bolt driver.
 */
public class Neo4jV3Database implements GraphDatabaseApi {

    private final String url;
    private final AuthToken auth;

    public Neo4jV3Database(String host,
                           Integer port,
                           String username,
                           String password) {
        this.url = String.format("bolt://%s:%s", host, port);
        if (username != null && password != null) {
            auth = AuthTokens.basic(username, password);
        } else {
            auth = AuthTokens.none();
        }
    }

    @Override
    public GraphQueryResult execute(String query) {
        try (Driver driver = GraphDatabase.driver(url, auth);
             Session session = driver.session()) {

            List<Record> records = session.run(query).list();


            Neo4jV3Buffer buffer = new Neo4jV3Buffer();

            for (Record record : records) {
                List<Value> values = record.values();

                for (Value value : values) {
                    extractValue(buffer, value.asObject());
                }
            }

            return new Neo4jV3QueryResult(buffer);
        }
    }

    private void extractValue(Neo4jV3Buffer buffer, Object object) {
        if (object instanceof Node) {
            Node node = (Node) object;
            buffer.addNode(node);
        }
        if (object instanceof Relationship) {
            Relationship rel = (Relationship) object;
            buffer.addRelationship(rel);
        }
        if (object instanceof Path) {
            Path path = (Path) object;
            for (Node node : path.nodes()) {
                extractValue(buffer, node);
            }
            for (Relationship relationship : path.relationships()) {
                extractValue(buffer, relationship);
            }
        }
        if (object instanceof List) {
            List list = (List) object;
            for (Object listObject : list) {
                extractValue(buffer, listObject);
            }
        }
        if (object instanceof Map) {
            Map map = (Map) object;
            for (Object mapValue : map.values()) {
                extractValue(buffer, mapValue);
            }
        }
    }
}
