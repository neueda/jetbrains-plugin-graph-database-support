package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

public class Neo4jLabelMetadata {
    private String name;
    private Long count;

    public Neo4jLabelMetadata(String name, Long count) {
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
