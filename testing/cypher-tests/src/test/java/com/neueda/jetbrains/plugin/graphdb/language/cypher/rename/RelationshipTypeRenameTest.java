package com.neueda.jetbrains.plugin.graphdb.language.cypher.rename;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseRenameTest;

public class RelationshipTypeRenameTest extends BaseRenameTest {

    public RelationshipTypeRenameTest() {
        super("relationship_type");
    }

    public void testSingleQuery() {
        verify("RENAMED_TYPE");
    }

    public void testMultipleQueries() {
        verify("RENAMED_TYPE");
    }
}
