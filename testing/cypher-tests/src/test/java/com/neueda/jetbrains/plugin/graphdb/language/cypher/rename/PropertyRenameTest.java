package com.neueda.jetbrains.plugin.graphdb.language.cypher.rename;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseRenameTest;

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
