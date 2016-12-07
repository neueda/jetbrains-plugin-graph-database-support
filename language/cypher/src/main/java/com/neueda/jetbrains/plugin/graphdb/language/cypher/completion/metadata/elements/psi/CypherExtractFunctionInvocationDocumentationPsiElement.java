package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;

public class CypherExtractFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherExtractFunctionInvocation {

    public CypherExtractFunctionInvocationDocumentationPsiElement(final Project project) {
        super("extract", project);
    }

    @NotNull
    @Override
    public CypherExpression getExpression() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public CypherFilterExpression getFilterExpression() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKExtract() {
        throw new UnsupportedOperationException();
    }
}
