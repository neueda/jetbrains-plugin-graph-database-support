package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion;

import java.util.Collection;
import java.util.List;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherLanguage;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.lang.CypherRegexp;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherParserDefinition;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherStringLiteral;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;

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
                   .andNot(PlatformPatterns.psiElement(CypherParserDefinition.LINE_COMMENT))
                   .andNot(PlatformPatterns.psiElement(CypherParserDefinition.BLOCK_COMMENT));
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

        PsiElementPattern.Capture<PsiElement> metadataPatternCapture = PlatformPatterns
                   .psiElement()
                   .withLanguage(CypherLanguage.INSTANCE);
        extend(CompletionType.BASIC, metadataPatternCapture,
                   new CompletionProvider<CompletionParameters>() {
                       public void addCompletions(@NotNull CompletionParameters parameters,
                                                  ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {
                           Project project = parameters.getEditor().getProject();
                           if (project == null) {
                               return;
                           }

                           CypherMetadataProviderService provider = ServiceManager.getService(project, CypherMetadataProviderService.class);
                           resultSet.addAllElements(provider.getMetadata(CypherMetadataType.LABELS));
                           resultSet.addAllElements(provider.getMetadata(CypherMetadataType.RELATIONSHIP_TYPES));
                           resultSet.addAllElements(provider.getMetadata(CypherMetadataType.PROPERTY_KEYS));
                           resultSet.addAllElements(provider.getMetadata(CypherMetadataType.PROCEDURES));
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
                                     Collection<String> keywords) {
        keywords.forEach((keyword) -> resultSet.addElement(
                   LookupElementBuilder
                              .create(keyword)
                              .withTypeText(type)
                              .withIcon(icon)));
    }
}
