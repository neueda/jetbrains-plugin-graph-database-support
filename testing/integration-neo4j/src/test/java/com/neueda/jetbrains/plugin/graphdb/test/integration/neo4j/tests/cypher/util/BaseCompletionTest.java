package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataContainer;

public abstract class BaseCompletionTest extends BaseGenericTest {

    protected CypherMetadataContainer metadata;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        metadata = services().cypherMetadataProvider().getContainer("documentationTest");
    }

    public void addStoredProcedure(String name, String signature, String description) {
        metadata.addProcedure(name, signature, description);
    }
}
