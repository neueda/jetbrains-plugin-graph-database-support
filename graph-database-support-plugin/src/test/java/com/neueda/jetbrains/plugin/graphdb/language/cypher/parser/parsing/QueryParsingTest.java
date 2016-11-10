package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util.BaseParsingTest;

public class QueryParsingTest extends BaseParsingTest {

    public QueryParsingTest() {
        super("query");
    }

    public void testMultipleQueries() {
        doTest(true);
    }

    public void testBulkImport() {
        doTest(true);
    }
}
