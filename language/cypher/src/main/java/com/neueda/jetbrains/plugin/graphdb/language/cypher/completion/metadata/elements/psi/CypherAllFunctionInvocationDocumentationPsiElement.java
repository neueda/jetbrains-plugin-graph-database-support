package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherAllFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFilterExpression;

public class CypherAllFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherAllFunctionInvocation {

    public CypherAllFunctionInvocationDocumentationPsiElement(final Project project) {
        super("all", project);
    }

    @NotNull
    @Override
    public CypherFilterExpression getFilterExpression() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKAll() {
        throw new UnsupportedOperationException();
    }
}
