package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.Collections;
import java.util.Map;

import static java.util.Collections.*;

public interface GraphMetadata {
    GraphMetadata EMPTY = new GraphMetadata() {

        @Override
        public Map<String, Number> labels() {
            return emptyMap();
        }

        @Override
        public Map<String, Number> relationships() {
            return emptyMap();
        }

        @Override
        public Iterable<String> vertexProperties() {
            return emptyList();
        }

        @Override
        public Iterable<String> edgeProperties() {
            return emptyList();
        }
    };

    Map<String, Number> labels();

    Map<String, Number> relationships();

    Iterable<String> vertexProperties();

    Iterable<String> edgeProperties();
}
