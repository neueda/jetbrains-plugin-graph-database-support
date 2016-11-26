package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseParsingTest;

public class ShellParsingTest extends BaseParsingTest {

    public ShellParsingTest() {
        super("shell");
    }

    public void testKeywords() {
        doTest(true);
    }
}
