package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPath;

import java.util.ArrayList;
import java.util.List;

public class Neo4jBoltPath implements GraphPath {

    private List<Object> pathComponents;

    public Neo4jBoltPath() {
        this.pathComponents = new ArrayList<>();
    }

    public Neo4jBoltPath(List<Object> pathComponents) {
        this.pathComponents = new ArrayList<>(pathComponents);
    }

    public void add(Object object) {
        pathComponents.add(object);
    }

    @Override
    public List<Object> getComponents() {
        return pathComponents;
    }
}
