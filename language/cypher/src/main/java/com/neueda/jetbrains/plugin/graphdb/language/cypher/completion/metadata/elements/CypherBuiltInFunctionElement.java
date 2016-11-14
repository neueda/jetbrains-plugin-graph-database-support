package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

public class CypherBuiltInFunctionElement implements CypherElement {

    private final String functionName;
    private final String functionSignature;
    private final String functionReturnType;
    private final boolean hasParameters;

    public CypherBuiltInFunctionElement(String functionName, String functionSignature, String functionReturnType) {
        this.functionName = functionName;
        this.functionSignature = functionSignature;
        this.functionReturnType = functionReturnType;
        this.hasParameters = !functionSignature.equals("()");
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                .create(functionName)
                .bold()
                .withIcon(GraphIcons.Nodes.FUNCTION)
                .withTailText(functionSignature)
                .withTypeText(functionReturnType)
                .withInsertHandler(ParenthesesInsertHandler.getInstance(hasParameters));
    }
}
