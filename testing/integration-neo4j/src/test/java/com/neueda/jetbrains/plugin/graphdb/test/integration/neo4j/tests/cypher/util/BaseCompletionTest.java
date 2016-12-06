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

    public void addLabel(String name) {
        metadata.addLabel(name);
    }

    public void addRelationshipType(String name) {
        metadata.addRelationshipType(name);
    }

    public void addProperty(String name) {
        metadata.addPropertyKey(name);
    }
}
