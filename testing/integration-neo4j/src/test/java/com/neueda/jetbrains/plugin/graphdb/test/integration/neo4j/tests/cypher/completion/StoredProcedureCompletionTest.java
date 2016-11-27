package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.completion;

import com.intellij.codeInsight.lookup.Lookup;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseCompletionTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StoredProcedureCompletionTest extends BaseCompletionTest {

    public void testContainsProceduresProcedure() throws Exception {
        myFixture.configureByText("test.cyp", "CALL <caret>");
        myFixture.completeBasic();
        List<String> strings = myFixture.getLookupElementStrings();
        assertThat(strings)
                .contains("dbms.procedures");
    }

    public void testCompletionCaretAfterParentheses() throws Exception {
        myFixture.configureByText("test.cyp", "CALL procedu<caret>");
        myFixture.completeBasic();
        myFixture.finishLookup(Lookup.REPLACE_SELECT_CHAR);
        myFixture.checkResult("CALL dbms.procedures()<caret>");
    }
}
