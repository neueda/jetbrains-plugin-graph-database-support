package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.Map;

public interface GraphMetadata {
    Map<String, Number> labels();

    Map<String, Number> relationships();

    Iterable<String> vertexProperties();

    Iterable<String> edgeProperties();
}
