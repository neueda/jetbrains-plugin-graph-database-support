package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import org.jetbrains.annotations.Nullable;

public class CypherUserFunctionElement implements CypherElement {
    private final String name;
    private final InvokableInformation information;

    public CypherUserFunctionElement(String name, String signature, @Nullable String description) {
        this.name = name;
        this.information = extractInformation(signature, name);
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                .create(name)
                .bold()
                .withIcon(GraphIcons.Nodes.USER_FUNCTION)
                .withTailText(information.getSignature())
                .withTypeText(information.getReturnType())
                .withInsertHandler(ParenthesesInsertHandler.getInstance(information.isHasParameters()));
    }
}
