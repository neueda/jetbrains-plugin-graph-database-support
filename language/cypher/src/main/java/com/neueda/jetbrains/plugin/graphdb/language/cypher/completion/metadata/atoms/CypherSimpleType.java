package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

public enum CypherSimpleType implements CypherType {
    BOOLEAN,
    INTEGER,
    FLOAT,
    NUMBER,
    STRING,
    MAP,
    ANY,
    NULL,

    NODE,
    RELATIONSHIP,
    PATH
}
