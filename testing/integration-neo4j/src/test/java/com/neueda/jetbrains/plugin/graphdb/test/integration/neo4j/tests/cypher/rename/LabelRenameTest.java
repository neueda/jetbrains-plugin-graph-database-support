package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.rename;


import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseRenameTest;

public class LabelRenameTest extends BaseRenameTest {

    public LabelRenameTest() {
        super("label");
    }

    public void testSingleQuery() {
        verify("RENAMED_LABEL");
    }

    public void testMultipleQueries() {
        verify("RENAMED_LABEL");
    }
}
