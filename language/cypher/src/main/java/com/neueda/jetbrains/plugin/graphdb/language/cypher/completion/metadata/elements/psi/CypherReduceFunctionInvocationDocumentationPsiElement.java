package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;

public class CypherReduceFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherReduceFunctionInvocation {

    public CypherReduceFunctionInvocationDocumentationPsiElement(final Project project) {
        super("reduce", project);
    }

    @NotNull
    @Override
    public List<CypherExpression> getExpressionList() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public CypherIdInColl getIdInColl() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public CypherVariable getVariable() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKReduce() {
        throw new UnsupportedOperationException();
    }
}
