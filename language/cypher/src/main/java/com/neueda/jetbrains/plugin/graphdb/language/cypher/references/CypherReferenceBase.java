package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * TODO: Description
 *
 * @author dmitry@vrublevsky.me
 */
public abstract class CypherReferenceBase extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    protected String name;

    public CypherReferenceBase(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        name = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public abstract ResolveResult[] multiResolve(boolean incompleteCode);

    @Nullable
    @Override
    public PsiElement resolve() {
        // assume that first identifier is target
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length >= 1 ? resolveResults[0].getElement() : null;
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        ((PsiNamedElement) myElement).setName(newElementName);
        return myElement;
    }

    @NotNull
    @Override
    public abstract Object[] getVariants();

    protected <T extends CypherNamedElement> Collection<T> uniqueVariants(List<T> data) {
        Map<String, T> variants = new HashMap<>();

        data.stream()
                .filter(Objects::nonNull)
                .forEach(element -> {
                    String name = element.getName();
                    if (name != null && name.length() > 0) {
                        String watName = name + "IntellijIdeaRulezzz";

                        // WAT check for "IntellijIdeaRulezzz" postfix
                        if (this.name.equals(name) || this.name.equals(watName)) {
                            return;
                        }

                        if (!variants.containsKey(name)) {
                            variants.put(name, element);
                        }
                    }
                });

        return variants.values();
    }

    protected <T extends CypherNamedElement> ResolveResult[] resolveResults(List<T> data) {
        return data.stream()
                .map(PsiElementResolveResult::new)
                .toArray(ResolveResult[]::new);
    }
}
