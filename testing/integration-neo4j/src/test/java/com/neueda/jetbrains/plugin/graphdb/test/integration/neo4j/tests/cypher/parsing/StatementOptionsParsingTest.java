package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseParsingTest;

public class StatementOptionsParsingTest extends BaseParsingTest {

    public StatementOptionsParsingTest() {
        super("statement-options");
    }

    public void testStatementOptions() {
        doTest(true);
    }
}
