package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherLanguage;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherParserDefinition;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.lang.CypherAtoms;
import org.jetbrains.annotations.NotNull;

public final class KeywordCompletionProvider extends BaseCompletionProvider {

    public static final ElementPattern<PsiElement> PATTERN = PlatformPatterns
            .psiElement()
            .withLanguage(CypherLanguage.INSTANCE)
            .andNot(PlatformPatterns.psiElement(CypherParserDefinition.LINE_COMMENT))
            .andNot(PlatformPatterns.psiElement(CypherParserDefinition.BLOCK_COMMENT));

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters,
                                  ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        CypherAtoms.KEYWORDS.forEach((keyword) -> result.addElement(
                LookupElementBuilder
                        .create(keyword)
                        .bold()));
    }
}
