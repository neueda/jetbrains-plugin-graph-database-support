package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFilterExpression;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFilterFunctionInvocation;

public class CypherFilterFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherFilterFunctionInvocation {

    public CypherFilterFunctionInvocationDocumentationPsiElement(final Project project) {
        super("filter", project);
    }

    @NotNull
    @Override
    public CypherFilterExpression getFilterExpression() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKFilter() {
        throw new UnsupportedOperationException();
    }
}
