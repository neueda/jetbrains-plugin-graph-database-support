package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

import com.google.common.collect.Sets;
import com.intellij.codeInsight.lookup.LookupElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherKeywordElement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class CypherKeywords {

    public static final Collection<String> KEYWORDS = Sets.newHashSet(
            "MATCH", "RETURN", "UNION", "ALL", "LOAD", "CSV", "WITH", "HEADERS", "FROM", "AS",
            "FIELDTERMINATOR", "CREATE", "CONSTRAINT", "ON", "ASSERT", "IS", "UNIQUE", "ASSERT",
            "EXISTS", "INDEX", "DROP", "START", "WHERE", "NODE", "RELATIONSHIP", "REL",
            "OPTIONAL", "USING", "JOIN", "SCAN", "UNWIND", "MERGE", "SET", "DELETE", "DETACH",
            "REMOVE", "FOREACH", "IN", "ORDER", "BY", "DESCENDING", "DESC", "ASCENDING", "ASC",
            "SKIP", "LIMIT", "PERIODIC", "COMMIT", "XOR", "OR", "AND", "NOT", "STARTS", "ENDS",
            "CONTAINS", "NULL", "TRUE", "FALSE", "COUNT", "FILTER", "EXTRACT", "REDUCE", "CASE",
            "DISTINCT", "ELSE", "END", "WHEN", "THEN", "PROFILE", "EXPLAIN", "CYPHER", "PLANNER",
            "CALL", "YIELD"
    );

    public static final List<LookupElement> KEYWORD_LOOKUP_ELEMENTS = KEYWORDS.stream()
            .map(CypherKeywordElement::new)
            .map(CypherKeywordElement::getLookupElement)
            .collect(Collectors.toList());
}
