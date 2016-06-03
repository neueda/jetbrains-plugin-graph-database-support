package com.neueda.jetbrains.plugin.cypher.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.neueda.jetbrains.plugin.cypher.CypherLanguage;
import com.neueda.jetbrains.plugin.cypher.lang.CypherRegexp;
import com.neueda.jetbrains.plugin.cypher.psi.CypherStringLiteral;
import com.neueda.jetbrains.plugin.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;
import java.util.List;

/**
 * TODO: Description
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherCompletionContributor extends CompletionContributor {

    public CypherCompletionContributor() {
        // Basic file-level keyword completion
        PsiElementPattern.Capture<PsiElement> keywordPatternCapture = PlatformPatterns
                .psiElement()
                .withLanguage(CypherLanguage.INSTANCE)
                .andNot(PlatformPatterns.psiElement(CypherTypes.LINECOMMENT))
                .andNot(PlatformPatterns.psiElement(CypherTypes.BLOCKCOMMENT));
        extend(CompletionType.BASIC, keywordPatternCapture,
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        addCompletionResult(resultSet,
                                "keyword",
                                null,
                                CypherRegexp.KEYWORDS
                        );
                    }
                }
        );

        // Basic function-level completion
        PsiElementPattern.Capture<PsiElement> functionPatternCapture = PlatformPatterns
                .psiElement()
                .inside(PlatformPatterns.psiElement(CypherTypes.EXPRESSION))
                .withLanguage(CypherLanguage.INSTANCE);
        extend(CompletionType.BASIC, functionPatternCapture,
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        addCompletionResult(resultSet,
                                "function",
                                AllIcons.Nodes.Function,
                                CypherRegexp.FUNCTIONS
                        );
                    }
                }
        );
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

    private void addCompletionResult(CompletionResultSet resultSet,
                                     String type, Icon icon,
                                     List<String> keywords) {
        keywords.forEach((keyword) -> resultSet.addElement(
                LookupElementBuilder
                        .create(keyword)
                        .withTypeText(type)
                        .withIcon(icon)));
    }

    private void addCompletionResult(CompletionResultSet resultSet,
                                     String type, Icon icon,
                                     String... keywords) {
        for (String keyword : keywords) {
            resultSet.addElement(LookupElementBuilder
                    .create(keyword)
                    .withTypeText(type)
                    .withIcon(icon));
        }
    }
}
