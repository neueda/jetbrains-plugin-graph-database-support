package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import org.jetbrains.annotations.Nullable;

import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi.CypherProcedureInvocationDocumentationPsiElement;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

public class CypherProcedureElement implements
           CypherElement,
           CypherElementWithSignature,
           CypherElementWithDocumentation {

    private final String name;
    @Nullable
    private final String description;
    private final InvokableInformation invokableInformation;
    private String documentation;

    public CypherProcedureElement(String name, String fullSignature, @Nullable String description) {
        this.name = name;
        this.description = description;
        this.invokableInformation = extractInformation(fullSignature, name);
    }

    public String getName() {
        return name;
    }

    @Override
    public InvokableInformation getInvokableInformation() {
        return invokableInformation;
    }

    public String getDocumentation() {
        if (documentation == null) {
            documentation = ""
                       + "procedure <b>" + name + "</b><br>"
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
        return new CypherProcedureInvocationDocumentationPsiElement(name, project);
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                   .create(this, name)
                   .bold()
                   .withIcon(GraphIcons.Nodes.STORED_PROCEDURE)
                   .withTailText(invokableInformation.getSignature())
                   .withTypeText(invokableInformation.getReturnType())
                   .withInsertHandler(ParenthesesInsertHandler.getInstance(invokableInformation.isHasParameters()));
    }
}
