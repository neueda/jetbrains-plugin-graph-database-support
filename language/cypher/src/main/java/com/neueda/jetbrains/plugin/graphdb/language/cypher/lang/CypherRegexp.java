package com.neueda.jetbrains.plugin.graphdb.language.cypher.lang;

/**
 * Regular expressions for Cypher.
 */
public final class CypherRegexp {

    public static final String SYMBOLIC_NAME_REGEXP = "(([a-zA-Z_][a-zA-Z_$0-9]*)|(`[^`]+`))";

    private CypherRegexp() {
    }
}
