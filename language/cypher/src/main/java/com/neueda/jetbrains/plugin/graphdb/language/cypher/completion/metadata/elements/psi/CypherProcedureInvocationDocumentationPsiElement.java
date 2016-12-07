package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;

public class CypherProcedureInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherProcedureInvocation {

    public CypherProcedureInvocationDocumentationPsiElement(final String name, Project project) {
        super(name, project);
    }

    @NotNull
    @Override
    public CypherProcedureArguments getProcedureArguments() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public CypherProcedureInvocationBody getProcedureInvocationBody() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFullName() {
        return getText();
    }
}
