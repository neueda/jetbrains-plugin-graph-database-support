package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherAllShortestPathsFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherPatternElement;

public class CypherAllShortestPathsFunctionInvocationDocumentationPsiElement extends NoopPsiElementAdapter
           implements CypherAllShortestPathsFunctionInvocation {

    public CypherAllShortestPathsFunctionInvocationDocumentationPsiElement(final Project project) {
        super("allShortestPaths", project);
    }

    @NotNull
    @Override
    public CypherPatternElement getPatternElement() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement getKAllshortestpaths() {
        throw new UnsupportedOperationException();
    }
}
