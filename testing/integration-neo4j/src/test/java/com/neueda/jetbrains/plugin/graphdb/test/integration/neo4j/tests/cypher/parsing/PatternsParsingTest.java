package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseParsingTest;

public class PatternsParsingTest extends BaseParsingTest {

    public PatternsParsingTest() {
        super("patterns");
    }

    public void testPatterns() {
        doTest(true);
    }
}
