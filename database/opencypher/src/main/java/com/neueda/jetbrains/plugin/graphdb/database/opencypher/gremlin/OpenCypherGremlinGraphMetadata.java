package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphMetadata;

import java.util.List;
import java.util.Map;

public class OpenCypherGremlinGraphMetadata implements GraphMetadata {
    private final Map<String, Number> labels;
    private final Map<String, Number> relationships;
    private final List<String> vertexProperties;
    private final List<String> edgeProperties;

    OpenCypherGremlinGraphMetadata(Map<String, Number> labels, Map<String, Number> relationships, List<String> vertexProperties, List<String> edgeProperties) {
        this.labels = labels;
        this.relationships = relationships;
        this.vertexProperties = vertexProperties;
        this.edgeProperties = edgeProperties;
    }

    @Override
    public Map<String, Number> labels() {
        return labels;
    }

    @Override
    public Map<String, Number> relationships() {
        return relationships;
    }

    @Override
    public Iterable<String> vertexProperties() {
        return vertexProperties;
    }

    @Override
    public Iterable<String> edgeProperties() {
        return edgeProperties;
    }
}
