package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.completion;

import com.intellij.codeInsight.lookup.Lookup;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherBuiltInFunctions;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseCompletionTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BuiltInFunctionsCompletionTest extends BaseCompletionTest {

    public void testBuiltInFunctionsPresentInLookup() throws Exception {
        myFixture.configureByText("test.cyp", "MATCH (n) RETURN <caret>");
        myFixture.completeBasic();
        List<String> strings = myFixture.getLookupElementStrings();
        assertThat(strings)
                .containsAll(CypherBuiltInFunctions.FUNCTION_NAMES);
    }

    public void testCompletionCaretInsideParentheses() throws Exception {
        myFixture.configureByText("test.cyp", "RETURN toStrin<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("RETURN toString(<caret>)");
    }

    public void testCompletionCaretAfterParentheses() throws Exception {
        myFixture.configureByText("test.cyp", "RETURN rand<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("RETURN rand()<caret>");
    }

    public void testCompletionLookupChar() throws Exception {
        myFixture.configureByText("test.cyp", "RETURN s<caret>");
        myFixture.completeBasic();
        myFixture.finishLookup(Lookup.REPLACE_SELECT_CHAR);
        myFixture.checkResult("RETURN shortestPath(<caret>)");
    }
}
