package com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data;

import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryNotification;

public class Neo4jBoltQueryNotification implements GraphQueryNotification {

    private String title;
    private String description;
    private Integer positionOffset;

    public Neo4jBoltQueryNotification(String title, String description, Integer positionOffset) {
        this.title = title;
        this.description = description;
        this.positionOffset = positionOffset;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getPositionOffset() {
        return positionOffset;
    }
}
