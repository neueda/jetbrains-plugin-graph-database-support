package com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database;

import org.junit.Test;

public class CypherDocumentationTest {

    @Test
    public void testBuiltInFunctionsLoadsCorrectly() throws Exception {
        CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("toString");
    }

    @Test
    public void testBuiltInProceduresLoadsCorrectly() throws Exception {
        CypherDocumentation.BUILT_IN_PROCEDURES.lookup("dbms.procedures");
    }
}
