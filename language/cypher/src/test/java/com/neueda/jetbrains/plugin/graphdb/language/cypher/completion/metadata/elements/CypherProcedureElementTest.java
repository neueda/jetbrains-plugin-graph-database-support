package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CypherProcedureElementTest {

    @Test
    public void testEmptySignatureAndVoidReturnType() throws Exception {
        CypherProcedureElement element = element("namespace.name", "namespace.name() :: VOID");
        assertThat(element.getSignature()).isEqualTo("()");
        assertThat(element.getReturnType()).isEqualTo("VOID");
    }

    @Test
    public void testNonEmptySignatureAndVoidReturnType() throws Exception {
        CypherProcedureElement element = element("namespace.name", "namespace.name(node :: NODE?) :: VOID");
        assertThat(element.getSignature()).isEqualTo("(node :: NODE?)");
        assertThat(element.getReturnType()).isEqualTo("VOID");
    }

    @Test
    public void testEmptySignatureAndValueReturnType() throws Exception {
        CypherProcedureElement element = element("namespace.name", "namespace.name() :: (value :: STRING?)");
        assertThat(element.getSignature()).isEqualTo("()");
        assertThat(element.getReturnType()).isEqualTo("value :: STRING?");
    }

    @Test
    public void testNonSignatureAndValueReturnType() throws Exception {
        CypherProcedureElement element = element("namespace.name", "namespace.name(node :: NODE?) :: (value :: STRING?)");
        assertThat(element.getSignature()).isEqualTo("(node :: NODE?)");
        assertThat(element.getReturnType()).isEqualTo("value :: STRING?");
    }

    private CypherProcedureElement element(String name, String signature) {
        return new CypherProcedureElement(name, signature);
    }
}