package com.neueda.jetbrains.plugin.cypher.references.impl;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.neueda.jetbrains.plugin.cypher.psi.CypherTypes;
import com.neueda.jetbrains.plugin.cypher.references.CypherReferenceBase;
import com.neueda.jetbrains.plugin.cypher.util.CypherUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Cypher label name reference.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherRelTypeNameReference extends CypherReferenceBase {

    public CypherRelTypeNameReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return resolveResults(CypherUtil.findAllByName(myElement.getContainingFile(), CypherTypes.REL_TYPE_NAME, name));
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return uniqueVariants(CypherUtil.findAll(myElement.getProject(), CypherTypes.REL_TYPE_NAME)).stream()
                .map(cypherLabelName -> LookupElementBuilder.create(cypherLabelName)
                        .withIcon(AllIcons.Nodes.DataTables)
                        .withTypeText("relationship type"))
                .toArray(Object[]::new);
    }
}
