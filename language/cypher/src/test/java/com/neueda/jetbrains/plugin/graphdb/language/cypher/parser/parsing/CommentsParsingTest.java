package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util.BaseParsingTest;

public class CommentsParsingTest extends BaseParsingTest {

    public CommentsParsingTest() {
        super("comments");
    }

    public void testBlock() {
        doTest(true);
    }

    public void testLine() {
        doTest(true);
    }
}
