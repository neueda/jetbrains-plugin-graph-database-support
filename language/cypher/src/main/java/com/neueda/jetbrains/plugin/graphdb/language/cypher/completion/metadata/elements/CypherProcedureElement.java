package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.text.StringUtil;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

public class CypherProcedureElement implements CypherElement {

    private final String procedureName;
    private final String procedureSignature;

    public CypherProcedureElement(String procedureName, String procedureSignature) {
        this.procedureName = procedureName;
        this.procedureSignature = StringUtil.trimStart(procedureSignature, procedureName);
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                .create(procedureName)
                .bold()
                .withIcon(GraphIcons.Nodes.STORED_PROCEDURE)
                .withTailText(procedureSignature)
                .withTypeText("procedure");
    }
}
