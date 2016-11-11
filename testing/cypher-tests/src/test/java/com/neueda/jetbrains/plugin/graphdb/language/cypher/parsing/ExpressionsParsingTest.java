package com.neueda.jetbrains.plugin.graphdb.language.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseParsingTest;

public class ExpressionsParsingTest extends BaseParsingTest {

    public ExpressionsParsingTest() {
        super("expressions");
    }

    public void testExpressions() {
        doTest(true);
    }

    public void testUserFunction() {
        doTest(true);
    }

    public void testParameters() {
        doTest(true);
    }

    public void testKeywordInIdentifier() {
        doTest(true);
    }

    public void testSpecialFunctions() {
        doTest(true);
    }
}
