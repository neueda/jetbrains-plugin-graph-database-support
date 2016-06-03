package com.neueda.jetbrains.plugin.cypher.lang;

import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.neueda.jetbrains.plugin.cypher.CypherLanguage;
import org.jetbrains.annotations.NotNull;

public class CypherSpellcheckingStrategy extends SpellcheckingStrategy {

    @Override
    public boolean isMyContext(@NotNull PsiElement element) {
        return CypherLanguage.INSTANCE.is(element.getLanguage());
    }
}
