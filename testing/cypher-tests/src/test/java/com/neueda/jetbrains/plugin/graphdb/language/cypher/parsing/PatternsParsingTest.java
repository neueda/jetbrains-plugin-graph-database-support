package com.neueda.jetbrains.plugin.graphdb.language.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseParsingTest;

public class PatternsParsingTest extends BaseParsingTest{

    public PatternsParsingTest() {
        super("patterns");
    }

    public void testPatterns() {
        doTest(true);
    }
}
