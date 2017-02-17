package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

import org.jetbrains.annotations.NotNull;

public final class CypherList implements CypherType {

    private final CypherSimpleType type;

    private CypherList(CypherSimpleType type) {
        this.type = type;
    }

    public static CypherList of(@NotNull CypherSimpleType t) {
        return new CypherList(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CypherList that = (CypherList) o;

        return type == that.type;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LIST OF " + type.toString();
    }
}
