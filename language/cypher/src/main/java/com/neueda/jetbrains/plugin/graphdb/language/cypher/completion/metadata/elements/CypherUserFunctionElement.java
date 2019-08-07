package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import icons.GraphIcons;
import org.jetbrains.annotations.Nullable;

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
        this.invokableInformation = new InvokableInformation(signature, name);
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
                    + "&nbsp;&nbsp;&nbsp;&nbsp;" + invokableInformation.getReturnTypeString();

            if (description != null) {
                documentation += "<br><br>"
                        + description;
            }
        }
        return documentation;
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                .create(name)
                .bold()
                .withIcon(GraphIcons.Nodes.USER_FUNCTION)
                .withTailText(invokableInformation.getSignature())
                .withTypeText(invokableInformation.getReturnTypeString())
                .withInsertHandler(ParenthesesInsertHandler.getInstance(invokableInformation.hasParameters()));
    }
}
