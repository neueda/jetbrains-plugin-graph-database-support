package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.reference;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseCompletionTest;

public class RelationshipTypeReferenceContributorTest extends BaseCompletionTest {

    public void testPresentInCompletion() throws Exception {
        myFixture.configureByText("test.cyp", "MATCH ()-[:TESTRELTYPE]-() MATCH ()-[:TESTREL<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("MATCH ()-[:TESTRELTYPE]-() MATCH ()-[:TESTRELTYPE<caret>");
    }

    public void _testOneEntryPresentInCompletionIfMetadataExists() throws Exception {
        addRelationshipType("TESTRELTYPE");
        myFixture.configureByText("test.cyp", "MATCH ()-[:TESTRELTYPE]-() MATCH ()-[:TESTREL<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("MATCH ()-[:TESTRELTYPE]-() MATCH ()-[:TESTRELTYPE<caret>");
    }
}
