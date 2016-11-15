package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.text.StringUtil;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CypherProcedureElement implements CypherElement {

    private static final Pattern FULL_SIGNATURE_REGEXP = Pattern.compile("^(\\([^)]*\\)) :: \\(?([^)]*)\\)?$");
    private final String name;
    private final String signature;
    private final String returnType;
    private final boolean hasParameters;

    public CypherProcedureElement(String name, String fullSignature) {
        this.name = name;
        String signatureWithoutName = StringUtil.trimStart(fullSignature, name);

        Matcher m = FULL_SIGNATURE_REGEXP.matcher(signatureWithoutName);
        if (m.find()) {
            signature = m.group(1);
            returnType = m.group(2);
        } else {
            // should never happen, unless Neo4j changes signature syntax
            signature = "(?)";
            returnType = "procedure";
        }
        this.hasParameters = !this.signature.startsWith("()");
    }

    public String getSignature() {
        return signature;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                .create(name)
                .bold()
                .withIcon(GraphIcons.Nodes.STORED_PROCEDURE)
                .withTailText(signature)
                .withTypeText(returnType)
                .withInsertHandler(ParenthesesInsertHandler.getInstance(hasParameters));
    }
}
