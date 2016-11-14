package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

public enum CypherFunctionReturnType {
    BOOLEAN("boolean"),
    INTEGER("integer"),
    FLOAT("float"),
    STRING("string"),
    MAP("map"),
    ANY("any"),

    NODE("node"),
    RELATIONSHIP("relationship"),
    PATH("path");

    private final String returnType;

    CypherFunctionReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String array() {
        return returnType + "[]";
    }

    public String single() {
        return returnType;
    }
}
