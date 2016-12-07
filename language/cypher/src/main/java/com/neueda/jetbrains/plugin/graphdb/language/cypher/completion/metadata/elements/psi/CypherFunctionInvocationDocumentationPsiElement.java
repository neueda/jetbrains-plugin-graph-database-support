package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;

public class CypherFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherFunctionInvocation {

    public CypherFunctionInvocationDocumentationPsiElement(final String functionName, Project project) {
        super(functionName, project);
    }

    @NotNull
    @Override
    public List<CypherExpression> getExpressionList() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public CypherFunctionInvocationBody getFunctionInvocationBody() {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public PsiElement getKDistinct() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFullName() {
        return getText();
    }
}
