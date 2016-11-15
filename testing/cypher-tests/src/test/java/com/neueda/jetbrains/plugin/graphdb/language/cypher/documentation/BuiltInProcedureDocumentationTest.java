package com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database.CypherDocumentation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseDocumentationTest;

public class BuiltInProcedureDocumentationTest extends BaseDocumentationTest {

    private String expectedDocumentation;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expectedDocumentation = CypherDocumentation.BUILT_IN_PROCEDURES.lookup("dbms.procedures").get();
    }

    public void testProcedureNamespace() throws Exception {
        configure("CALL db<caret>ms.procedures();");
        verify(expectedDocumentation);
    }

    public void testProcedureBody() throws Exception {
        configure("CALL dbms.procedu<caret>res();");
        verify(expectedDocumentation);
    }

    public void testProcedureParameters() throws Exception {
        configure("CALL dbms.procedures(<caret>);");
        verify(expectedDocumentation);
    }

    public void testNoDocumentation() throws Exception {
        configure("CALL namespace.nonexist<caret>ing();");
        verify(null);
    }
}
