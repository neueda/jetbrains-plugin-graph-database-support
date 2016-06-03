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

public class CypherProperyKeyNameReference extends CypherReferenceBase {

    public CypherProperyKeyNameReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return resolveResults(CypherUtil.findAllByName(myElement.getContainingFile(), CypherTypes.PROPERTY_KEY_NAME, name));
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return uniqueVariants(CypherUtil.findAll(myElement.getProject(), CypherTypes.PROPERTY_KEY_NAME)).stream()
                .map(cypherPropertyKeyName -> LookupElementBuilder.create(cypherPropertyKeyName)
                        .withIcon(AllIcons.Nodes.Property)
                        .withTypeText("property"))
                .toArray(Object[]::new);
    }
}
