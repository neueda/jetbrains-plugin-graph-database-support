package com.neueda.jetbrains.plugin.graphdb.language.cypher.rename;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseRenameTest;

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
