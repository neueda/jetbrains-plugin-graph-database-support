package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFilterExpression;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherNoneFunctionInvocation;

public class CypherNoneFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherNoneFunctionInvocation {

    public CypherNoneFunctionInvocationDocumentationPsiElement(final Project project) {
        super("none", project);
    }

    @NotNull
    @Override
    public CypherFilterExpression getFilterExpression() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKNone() {
        throw new UnsupportedOperationException();
    }
}
