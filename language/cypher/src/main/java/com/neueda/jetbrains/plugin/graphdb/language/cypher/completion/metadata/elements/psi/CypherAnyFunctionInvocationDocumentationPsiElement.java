package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherAnyFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFilterExpression;

public class CypherAnyFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherAnyFunctionInvocation {

    public CypherAnyFunctionInvocationDocumentationPsiElement(final Project project) {
        super("any", project);
    }

    @NotNull
    @Override
    public CypherFilterExpression getFilterExpression() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKAny() {
        throw new UnsupportedOperationException();
    }
}
