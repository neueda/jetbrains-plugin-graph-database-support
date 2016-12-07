package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherExistsFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherExpression;

public class CypherExistsFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherExistsFunctionInvocation {

    public CypherExistsFunctionInvocationDocumentationPsiElement(final Project project) {
        super("exists", project);
    }

    @NotNull
    @Override
    public CypherExpression getExpression() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKExists() {
        throw new UnsupportedOperationException();
    }
}
