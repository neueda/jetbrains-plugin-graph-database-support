package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.reference;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseCompletionTest;

public class LabelReferenceContributorTest extends BaseCompletionTest {

    public void testPresentInCompletion() throws Exception {
        myFixture.configureByText("test.cyp", "MATCH (n:TestLabel) RETURN n; MATCH (n:Test<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("MATCH (n:TestLabel) RETURN n; MATCH (n:TestLabel<caret>");
    }

    public void _testOneEntryPresentInCompletionIfMetadataExists() throws Exception {
        addLabel("TestLabel");
        myFixture.configureByText("test.cyp", "MATCH (n:TestLabel) RETURN n; MATCH (n:Test<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("MATCH (n:TestLabel) RETURN n; MATCH (n:TestLabel<caret>");
    }
}
