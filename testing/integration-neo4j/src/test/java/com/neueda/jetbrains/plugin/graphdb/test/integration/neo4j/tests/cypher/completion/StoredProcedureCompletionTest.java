package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.completion;

import com.intellij.codeInsight.lookup.Lookup;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseCompletionTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StoredProcedureCompletionTest extends BaseCompletionTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        addStoredProcedure("test.myProcedure", "()", null);
        addStoredProcedure("some.anotherProcedure", "(signatureAnother)", null);
    }

    public void testContainsProceduresProcedure() throws Exception {
        myFixture.configureByText("test.cyp", "CALL <caret>");
        myFixture.completeBasic();
        List<String> strings = myFixture.getLookupElementStrings();
        assertThat(strings)
                   .contains("test.myProcedure")
                   .contains("some.anotherProcedure");
    }

    public void testCompletionCaretAfterParentheses() throws Exception {
        myFixture.configureByText("test.cyp", "CALL myProce<caret>");
        myFixture.completeBasic();
        myFixture.finishLookup(Lookup.NORMAL_SELECT_CHAR);
        myFixture.checkResult("CALL test.myProcedure()<caret>");
    }
}
