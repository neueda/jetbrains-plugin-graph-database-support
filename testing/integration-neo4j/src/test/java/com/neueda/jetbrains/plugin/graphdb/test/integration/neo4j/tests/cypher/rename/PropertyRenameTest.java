package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.rename;


import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseRenameTest;

public class PropertyRenameTest extends BaseRenameTest {

    public PropertyRenameTest() {
        super("property");
    }

    public void testSingleQuery() {
        verify("renamedProperty");
    }

    public void testMultipleQueries() {
        verify("renamedProperty");
    }
}
