package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util.BaseParsingTest;

public class PatternsParsingTest extends BaseParsingTest{

    public PatternsParsingTest() {
        super("patterns");
    }

    public void testPatterns() {
        doTest(true);
    }
}
