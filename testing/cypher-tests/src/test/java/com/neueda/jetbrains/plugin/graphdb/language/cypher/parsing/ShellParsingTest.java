package com.neueda.jetbrains.plugin.graphdb.language.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseParsingTest;

public class ShellParsingTest extends BaseParsingTest {

    public ShellParsingTest() {
        super("shell");
    }

    public void testKeywords() {
        doTest(true);
    }
}
