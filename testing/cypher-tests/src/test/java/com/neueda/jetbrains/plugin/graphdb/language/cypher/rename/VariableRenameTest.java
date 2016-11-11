package com.neueda.jetbrains.plugin.graphdb.language.cypher.rename;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseRenameTest;

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
