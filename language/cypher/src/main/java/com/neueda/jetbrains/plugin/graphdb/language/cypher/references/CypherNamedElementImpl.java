package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.ContributedReferenceHost;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Description
 *
 * @author dmitry@vrublevsky.me
 */
public abstract class CypherNamedElementImpl extends ASTWrapperPsiElement
        implements CypherNamedElement, ContributedReferenceHost {

    public CypherNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
