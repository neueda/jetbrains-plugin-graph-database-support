package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import org.jetbrains.annotations.Nullable;

import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi.CypherFunctionInvocationDocumentationPsiElement;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

public class CypherUserFunctionElement implements
           CypherElement,
           CypherElementWithSignature,
           CypherElementWithDocumentation {

    private final String name;
    @Nullable
    private final String description;
    private final InvokableInformation invokableInformation;
    private String documentation;

    public CypherUserFunctionElement(String name, String signature, @Nullable String description) {
        this.name = name;
        this.description = description;
        this.invokableInformation = extractInformation(signature, name);
    }

    public String getName() {
        return name;
    }

    @Override
    public InvokableInformation getInvokableInformation() {
        return invokableInformation;
    }

    @Override
    public String getDocumentation() {
        if (documentation == null) {
            documentation = ""
                       + "function <b>" + name + "</b><br>"
                       + "Arguments:<br>"
                       + "&nbsp;&nbsp;&nbsp;&nbsp;" + invokableInformation.getSignature() + "<br>"
                       + "Return:<br>"
                       + "&nbsp;&nbsp;&nbsp;&nbsp;" + invokableInformation.getReturnType();

            if (description != null) {
                documentation += "<br><br>"
                           + description;
            }
        }
        return documentation;
    }

    @Nullable
    @Override
    public PsiElement getDocumentationPsiElement(Project project) {
        return new CypherFunctionInvocationDocumentationPsiElement(name, project);
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                   .create(this, name)
                   .bold()
                   .withIcon(GraphIcons.Nodes.USER_FUNCTION)
                   .withTailText(invokableInformation.getSignature())
                   .withTypeText(invokableInformation.getReturnType())
                   .withInsertHandler(ParenthesesInsertHandler.getInstance(invokableInformation.isHasParameters()));
    }
}
