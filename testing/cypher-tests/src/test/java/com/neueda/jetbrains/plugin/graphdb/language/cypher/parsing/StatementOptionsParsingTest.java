package com.neueda.jetbrains.plugin.graphdb.language.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseParsingTest;

public class StatementOptionsParsingTest extends BaseParsingTest {

    public StatementOptionsParsingTest() {
        super("statement-options");
    }

    public void testStatementOptions() {
        doTest(true);
    }
}
