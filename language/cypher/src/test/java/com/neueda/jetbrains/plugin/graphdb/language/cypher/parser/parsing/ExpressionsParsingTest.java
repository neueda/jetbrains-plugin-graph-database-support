package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util.BaseParsingTest;

public class ExpressionsParsingTest extends BaseParsingTest {

    public ExpressionsParsingTest() {
        super("expressions");
    }

    public void testExpressions() {
        doTest(true);
    }
}
