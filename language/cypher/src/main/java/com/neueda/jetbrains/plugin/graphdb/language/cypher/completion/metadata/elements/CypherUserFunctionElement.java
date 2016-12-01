package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import org.jetbrains.annotations.Nullable;

public class CypherUserFunctionElement implements CypherElement, CypherElementWithSignature {
    private final String name;
    private final InvokableInformation invokableInformation;

    public CypherUserFunctionElement(String name, String signature, @Nullable String description) {
        this.name = name;
        this.invokableInformation = extractInformation(signature, name);
    }

    @Override
    public InvokableInformation getInvokableInformation() {
        return invokableInformation;
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                .create(name)
                .bold()
                .withIcon(GraphIcons.Nodes.USER_FUNCTION)
                .withTailText(invokableInformation.getSignature())
                .withTypeText(invokableInformation.getReturnType())
                .withInsertHandler(ParenthesesInsertHandler.getInstance(invokableInformation.isHasParameters()));
    }
}
