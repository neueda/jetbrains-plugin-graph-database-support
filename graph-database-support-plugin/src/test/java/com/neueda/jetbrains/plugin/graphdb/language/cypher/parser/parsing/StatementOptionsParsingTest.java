package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util.BaseParsingTest;

public class StatementOptionsParsingTest extends BaseParsingTest {

    public StatementOptionsParsingTest() {
        super("statement-options");
    }

    public void testStatementOptions() {
        doTest(true);
    }
}
