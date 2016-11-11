package com.neueda.jetbrains.plugin.graphdb.language.cypher.lang;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Regular expressions for Cypher and other misc stuff.
 *
 * TODO: this class has bad name.
 */
public final class CypherRegexp {

    public static final String SYMBOLIC_NAME_REGEXP = "(([a-zA-Z_$][a-zA-Z_$0-9]*)|(`[^`]+`))";
    public static final Set<String> KEYWORDS = Sets.newHashSet(
               "MATCH", "RETURN", "UNION", "ALL", "LOAD", "CSV", "WITH", "HEADERS", "FROM", "AS", "FIELDTERMINATOR", "CREATE",
               "CONSTRAINT", "ON", "ASSERT", "IS", "UNIQUE", "ASSERT", "EXISTS", "INDEX", "DROP", "START", "WHERE", "NODE",
               "RELATIONSHIP", "REL", "OPTIONAL", "USING", "JOIN", "SCAN", "UNWIND", "MERGE", "SET", "DELETE", "DETACH",
               "REMOVE", "FOREACH", "IN", "ORDER", "BY", "DESCENDING", "DESC", "ASCENDING", "ASC", "SKIP", "LIMIT", "PERIODIC",
               "COMMIT", "XOR", "OR", "AND", "NOT", "STARTS", "ENDS", "CONTAINS", "NULL", "TRUE", "FALSE", "COUNT", "FILTER", "EXTRACT",
               "REDUCE", "CASE", "DISTINCT", "ELSE", "END", "WHEN", "THEN", "PROFILE", "EXPLAIN", "CYPHER", "PLANNER", "CALL", "YIELD"
    );

    public static final Set<String> FUNCTIONS_SHORTEST_PATH = Sets.newHashSet(
               "shortestPath", "allShortestPaths"
    );
    public static final Set<String> FUNCTIONS_PREDICATE = Sets.newHashSet(
               "all", "any", "exists", "none", "single"
    );
    public static final Set<String> FUNCTIONS_SCALAR = Sets.newHashSet(
               "coalesce", "endNode", "head", "id", "last", "length", "properties",
               "startNode", "size", "timestamp", "toInt", "toFloat", "type"
    );
    public static final Set<String> FUNCTIONS_LIST = Sets.newHashSet(
               "extract", "filter", "keys", "labels", "nodes", "range", "reduce", "relationships", "tail"
    );
    public static final Set<String> FUNCTIONS_MATH_NUMERIC = Sets.newHashSet(
               "abs", "ceil", "floor", "rand", "round", "sign"
    );
    public static final Set<String> FUNCTIONS_MATH_LOGARITHMIC = Sets.newHashSet(
               "e", "exp", "log", "log10", "sqrt"
    );
    public static final Set<String> FUNCTIONS_MATH_TRIGONOMETRIC = Sets.newHashSet(
               "acos", "asin", "atan", "atan2", "cos", "cot", "degrees", "haversin", "pi", "radians", "sin", "tan"
    );
    public static final Set<String> FUNCTIONS_STRING = Sets.newHashSet(
               "left", "lower", "ltrim", "replace", "reverse", "right", "rtrim", "split", "substring", "toString", "trim", "upper"
    );

    public static final Set<String> FUNCTIONS = new HashSet<String>() {{
        addAll(FUNCTIONS_SHORTEST_PATH);
        addAll(FUNCTIONS_PREDICATE);
        addAll(FUNCTIONS_SCALAR);
        addAll(FUNCTIONS_LIST);
        addAll(FUNCTIONS_MATH_NUMERIC);
        addAll(FUNCTIONS_MATH_LOGARITHMIC);
        addAll(FUNCTIONS_MATH_TRIGONOMETRIC);
        addAll(FUNCTIONS_STRING);
    }};

    private CypherRegexp() {
    }
}
