package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherPatternElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherShortestPathFunctionInvocation;

public class CypherShortestPathFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherShortestPathFunctionInvocation {

    public CypherShortestPathFunctionInvocationDocumentationPsiElement(final Project project) {
        super("shortestPath", project);
    }

    @NotNull
    @Override
    public CypherPatternElement getPatternElement() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKShortestpath() {
        throw new UnsupportedOperationException();
    }
}
