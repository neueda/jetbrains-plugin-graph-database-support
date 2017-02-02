package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

public class Neo4jRelationshipTypeMetadata {
    private String name;
    private Long count;

    public Neo4jRelationshipTypeMetadata(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
