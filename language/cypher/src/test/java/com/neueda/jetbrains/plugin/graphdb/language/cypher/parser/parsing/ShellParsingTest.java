package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util.BaseParsingTest;

public class ShellParsingTest extends BaseParsingTest {

    public ShellParsingTest() {
        super("shell");
    }

    public void testKeywords() {
        doTest(true);
    }
}
