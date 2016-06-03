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
 * Cypher identifier reference. Responsible for providing references and handle renaming.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherVariableReference extends CypherReferenceBase {

    public CypherVariableReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return resolveResults(CypherUtil.findAllByName(myElement.getContainingFile(), CypherTypes.VARIABLE, name));
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return uniqueVariants(CypherUtil.findAll(myElement.getContainingFile(), CypherTypes.VARIABLE)).stream()
                .map(cypherIdentifier -> LookupElementBuilder.create(cypherIdentifier)
                        .withIcon(AllIcons.Nodes.Variable)
                        .withTypeText("variable"))
                .toArray(Object[]::new);
    }
}
