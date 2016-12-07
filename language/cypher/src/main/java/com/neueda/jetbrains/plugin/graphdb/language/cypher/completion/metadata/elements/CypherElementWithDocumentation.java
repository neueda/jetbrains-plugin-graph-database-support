package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

public interface CypherElementWithDocumentation {

    @Nullable
    String getDocumentation();

    @Nullable
    PsiElement getDocumentationPsiElement(Project project);
}
