package com.neueda.jetbrains.plugin.graphdb.language.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseParsingTest;

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
