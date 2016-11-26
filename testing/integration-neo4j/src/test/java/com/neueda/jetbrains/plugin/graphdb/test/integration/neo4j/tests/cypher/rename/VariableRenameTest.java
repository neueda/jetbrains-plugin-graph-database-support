package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.rename;


import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseRenameTest;

public class VariableRenameTest extends BaseRenameTest {

    public VariableRenameTest() {
        super("variable");
    }

    public void testSingleQuery() {
        verify("renamed");
    }

    public void testMultipleQueries() {
        verify("renamed");
    }
}
