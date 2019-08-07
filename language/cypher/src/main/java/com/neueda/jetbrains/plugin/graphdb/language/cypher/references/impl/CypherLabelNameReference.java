package com.neueda.jetbrains.plugin.graphdb.language.cypher.references.impl;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherReferenceBase;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.CypherUtil;
import icons.GraphIcons;
import org.jetbrains.annotations.NotNull;

/**
 * Cypher label name reference.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherLabelNameReference extends CypherReferenceBase {

    public CypherLabelNameReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return resolveResults(CypherUtil.findAllByName(myElement.getContainingFile(), CypherTypes.LABEL_NAME, name));
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return uniqueVariants(CypherUtil.findAll(myElement.getProject(), CypherTypes.LABEL_NAME)).stream()
                .map(cypherLabelName -> LookupElementBuilder.create(cypherLabelName)
                        .withIcon(GraphIcons.Nodes.LABEL)
                        .withTypeText("label (reference)"))
                .toArray(Object[]::new);
    }
}
