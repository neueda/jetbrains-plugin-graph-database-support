package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPropertyContainer;
import org.neo4j.driver.internal.util.Iterables;
import org.neo4j.driver.v1.types.Node;

import java.util.List;
import java.util.Objects;

public class Neo4jBoltNode implements GraphNode {

    private final String id;
    private final Neo4jBoltPropertyContainer propertyContainer;
    private final List<String> types;

    public Neo4jBoltNode(Node value) {
        this.id = String.valueOf(value.id());
        this.types = Iterables.asList(value.labels());
        this.propertyContainer = new Neo4jBoltPropertyContainer(value.asMap());
    }

    public Neo4jBoltNode(String id, List<String> types, Neo4jBoltPropertyContainer propertyContainer) {
        this.id = id;
        this.propertyContainer = propertyContainer;
        this.types = types;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<String> getTypes() {
        return types;
    }

    @Override
    public String getTypesName() {
        return "labels";
    }

    @Override
    public boolean isTypesSingle() {
        return false;
    }

    @Override
    public GraphPropertyContainer getPropertyContainer() {
        return propertyContainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Neo4jBoltNode that = (Neo4jBoltNode) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
