package com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database.CypherDocumentation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseDocumentationTest;

public class BuiltInFunctionDocumentationTest extends BaseDocumentationTest {

    private String expectedDocumentation;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expectedDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("toString").get();
    }

    public void testFunctionBody() throws Exception {
        configure("RETURN toStr<caret>ing(42);");
        verify(expectedDocumentation);
    }

    public void testFunctionParameters() throws Exception {
        configure("RETURN toString(4<caret>2);");
        verify(expectedDocumentation);
    }

    public void testIgnoreCase() throws Exception {
        configure("RETURN to<caret>STRING(42);");
        verify(expectedDocumentation);
    }

    public void testNoDocumentation() {
        configure("RETURN nonexistingf<caret>unction();");
        verify(null);
    }
}
