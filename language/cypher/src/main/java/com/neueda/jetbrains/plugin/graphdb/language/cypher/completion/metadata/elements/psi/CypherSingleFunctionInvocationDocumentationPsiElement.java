package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFilterExpression;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherSingleFunctionInvocation;

public class CypherSingleFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherSingleFunctionInvocation {

    public CypherSingleFunctionInvocationDocumentationPsiElement(final Project project) {
        super("single", project);
    }

    @NotNull
    @Override
    public CypherFilterExpression getFilterExpression() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKSingle() {
        throw new UnsupportedOperationException();
    }
}
