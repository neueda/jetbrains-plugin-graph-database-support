package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.ContributedReferenceHost;
import org.jetbrains.annotations.NotNull;

public abstract class CypherVariableElementImpl extends ASTWrapperPsiElement
        implements CypherVariableElement, ContributedReferenceHost {

    public CypherVariableElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
