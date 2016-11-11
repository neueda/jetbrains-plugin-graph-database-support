package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.providers.BuiltInFunctionCompletionProvider;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.providers.KeywordCompletionProvider;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.providers.LabelsCompletionProvider;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.providers.ProceduresCompletionProvider;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.providers.PropertyKeyCompletionProvider;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.providers.RelationshipTypeCompletionProvider;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherStringLiteral;
import org.jetbrains.annotations.NotNull;

/**
 * Entry point for Cypher auto completion.
 */
public class CypherCompletionContributor extends CompletionContributor {

    public CypherCompletionContributor() {
        extend(CompletionType.BASIC,
                KeywordCompletionProvider.PATTERN,
                new KeywordCompletionProvider());

        extend(CompletionType.BASIC,
                BuiltInFunctionCompletionProvider.PATTERN,
                new BuiltInFunctionCompletionProvider());

        extend(CompletionType.BASIC,
                LabelsCompletionProvider.PATTERN,
                new LabelsCompletionProvider());

        extend(CompletionType.BASIC,
                RelationshipTypeCompletionProvider.PATTERN,
                new RelationshipTypeCompletionProvider());

        extend(CompletionType.BASIC,
                PropertyKeyCompletionProvider.PATTERN,
                new PropertyKeyCompletionProvider());

        extend(CompletionType.BASIC,
                ProceduresCompletionProvider.PATTERN,
                new ProceduresCompletionProvider());
    }

    @Override
    public boolean invokeAutoPopup(@NotNull PsiElement position, char typeChar) {
        if (position instanceof CypherStringLiteral) {
            return false;
        }
        return typeChar == ':'
                   || typeChar == '.'
                   || typeChar == '('
                   || typeChar == '[';
    }
}
