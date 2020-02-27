package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.completion;

import com.intellij.codeInsight.lookup.Lookup;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseCompletionTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserFunctionCompletionTest extends BaseCompletionTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        dataSource().neo4j40();
    }

    public void testContainsTestUserFunction() throws Exception {
        myFixture.configureByText("test.cyp", "RETURN testFunc<caret>");
        myFixture.completeBasic();
        List<String> strings = myFixture.getLookupElementStrings();
        assertThat(strings)
                .contains(
                        "com.neueda.jetbrains.plugin.graphdb.test.database.neo4j_4_0.firstTestFunction",
                        "com.neueda.jetbrains.plugin.graphdb.test.database.neo4j_4_0.secondTestFunction"
                );
    }

    public void testCompletionCaretAfterParentheses() throws Exception {
        myFixture.configureByText("test.cyp", "RETURN firstTestFuncti<caret>");
        myFixture.completeBasic();
        myFixture.finishLookup(Lookup.REPLACE_SELECT_CHAR);
        myFixture.checkResult("RETURN com.neueda.jetbrains.plugin.graphdb.test.database.neo4j_4_0.firstTestFunction()<caret>");
    }

    public void testCompletionCaretAtParentheses() throws Exception {
        myFixture.configureByText("test.cyp", "RETURN secondTestFuncti<caret>");
        myFixture.completeBasic();
        myFixture.finishLookup(Lookup.REPLACE_SELECT_CHAR);
        myFixture.checkResult("RETURN com.neueda.jetbrains.plugin.graphdb.test.database.neo4j_4_0.secondTestFunction(<caret>)");
    }
}
