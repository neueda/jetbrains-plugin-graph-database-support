package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.reference;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseCompletionTest;

public class PropertyReferenceContributorTest extends BaseCompletionTest {

    public void testPresentInCompletion() throws Exception {
        myFixture.configureByText("test.cyp", "MATCH (n) WHERE n.testProperty = 1 RETURN n.testProp<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("MATCH (n) WHERE n.testProperty = 1 RETURN n.testProperty<caret>");
    }

    public void _testOneEntryPresentInCompletionIfMetadataExists() throws Exception {
        addProperty("testProperty");
        myFixture.configureByText("test.cyp", "MATCH (n) WHERE n.testProperty = 1 RETURN n.testProp<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("MATCH (n) WHERE n.testProperty = 1 RETURN n.testProperty<caret>");
    }
}
