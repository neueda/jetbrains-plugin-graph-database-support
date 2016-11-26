package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.editor;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseGenericTest;

public class BraceMatcherTest extends BaseGenericTest {

    public void testCloseParentheses() throws Exception {
        myFixture.configureByText("test.cyp", "MATCH <caret>");
        myFixture.type("(");
        myFixture.checkResult("MATCH (<caret>)");
    }

    public void testCloseSquare() throws Exception {
        myFixture.configureByText("test.cyp", "RETURN <caret>");
        myFixture.type("[");
        myFixture.checkResult("RETURN [<caret>]");
    }

    public void testCloseCurly() throws Exception {
        myFixture.configureByText("test.cyp", "RETURN <caret>");
        myFixture.type("{");
        myFixture.checkResult("RETURN {<caret>}");
    }
}
