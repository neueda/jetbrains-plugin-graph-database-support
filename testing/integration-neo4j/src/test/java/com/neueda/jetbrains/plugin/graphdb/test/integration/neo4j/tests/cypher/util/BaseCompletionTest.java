package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util;

public abstract class BaseCompletionTest extends BaseGenericTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        component().dataSources().refreshAllMetadata();
    }
}
