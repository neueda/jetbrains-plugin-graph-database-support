package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltNode;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltPath;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltPropertyContainer;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltRelationship;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;
import static org.opencypher.gremlin.translation.ReturnProperties.*;

class OpenCypherGremlinValueConverter {
    GraphQueryResultRow toRecord(Map<String, Object> map) {
        Set<GraphNode> nodes = new HashSet<>();
        Set<GraphRelationship> relationships = new HashSet<>();

        Map<String, Object> results = map.entrySet().stream()
                .collect(HashMap::new,
                        (m, v) -> m.put(v.getKey(),
                                toCypherValue(v.getValue(), nodes, relationships)),
                        HashMap::putAll);

        relationships.forEach((rel) -> {
            Neo4jBoltRelationship boltRel = (Neo4jBoltRelationship) rel;

            boltRel.setStartNode(findNodeById(nodes, boltRel.getStartNodeId()).orElse(null));
            boltRel.setEndNode(findNodeById(nodes, boltRel.getEndNodeId()).orElse(null));
        });

        return new OpenCypherGremlinGraphQueryResultRow(results, nodes, relationships);
    }

    private Object toCypherValue(Object value, Set<GraphNode> nodes, Set<GraphRelationship> relationships) {
        if (isNode(value)) {
            Neo4jBoltNode node = toCypherNode((Map<?, ?>) value);
            nodes.add(node);
            return node;
        } else if (isRelationship(value)) {
            Neo4jBoltRelationship relationship = toCypherRelationship((Map<?, ?>) value);
            relationships.add(relationship);
            return relationship;
        } else if (isPath(value)) {
            return toCypherPath((List) value, nodes, relationships);
        } else if (value instanceof Map) {
            return toCypherPropertyMap((Map) value, nodes, relationships);
        } else if (value instanceof List) {
            return toCypherList((List) value, nodes, relationships);
        } else {
            return value;
        }
    }

    private List<Object> toCypherList(List<?> l, Set<GraphNode> nodes, Set<GraphRelationship> relationships) {
        return l.stream()
                .map(v -> toCypherValue(v, nodes, relationships))
                .collect(Collectors.toList());
    }

    private Neo4jBoltPath toCypherPath(List p, Set<GraphNode> nodes, Set<GraphRelationship> relationships) {
        List<Object> pathComponents = toCypherList(p, nodes, relationships);
        return new Neo4jBoltPath(pathComponents);
    }

    private Neo4jBoltRelationship toCypherRelationship(Map<?, ?> e) {
        String id = String.valueOf(e.get(ID));
        String start = String.valueOf(e.get(OUTV));
        String end = String.valueOf(e.get(INV));

        Neo4jBoltPropertyContainer properties = toCypherPropertyContainer(e);

        String label = String.valueOf(e.get(LABEL));

        return new Neo4jBoltRelationship(id, singletonList(label), properties, start, end);
    }

    private Neo4jBoltNode toCypherNode(Map<?, ?> v) {
        List<String> labels = new ArrayList<>();
        String label = String.valueOf(v.get(LABEL));
        if (!Vertex.DEFAULT_LABEL.equals(label)) {
            labels.add(label);
        }

        String id = String.valueOf(v.get(ID));
        Neo4jBoltPropertyContainer properties = toCypherPropertyContainer(v);

        return new Neo4jBoltNode(id, labels, properties);
    }

    private Neo4jBoltPropertyContainer toCypherPropertyContainer(Map<?, ?> e) {
        Map<String, Object> map = toCypherPropertyMap(e, emptySet(), emptySet());
        return new Neo4jBoltPropertyContainer(map);
    }

    private Map<String, Object> toCypherPropertyMap(Map<?, ?> e, Set<GraphNode> nodes, Set<GraphRelationship> relationships) {
        Map<String, Object> properties = new HashMap<>();
        e.entrySet().stream()
                .filter((n) -> !ALL_PROPERTIES.contains(String.valueOf(n.getKey())))
                .forEach((n) -> properties.put(
                        String.valueOf(n.getKey()),
                        toCypherValue(n.getValue(), nodes, relationships)));

        return properties;
    }

    private Optional<GraphNode> findNodeById(Collection<GraphNode> nodes, String id) {
        return nodes.stream().filter((node) -> node.getId().equals(id)).findFirst();
    }
}
