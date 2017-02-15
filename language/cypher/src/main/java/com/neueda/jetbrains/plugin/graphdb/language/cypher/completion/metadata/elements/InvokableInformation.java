package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Range;

import java.util.Objects;
import java.util.regex.Matcher;

public final class InvokableInformation {

    private final String name;
    private final String signature;
    private final String returnType;
    private final boolean hasParameters;
    private final Range<Integer> arity;

    public InvokableInformation(String fullSignature, String name) {
        String signatureWithoutName = StringUtil.trimStart(fullSignature, name);
        Matcher m = CypherElementWithSignature.FULL_SIGNATURE_REGEXP.matcher(signatureWithoutName);
        if (m.find()) {
            signature = m.group(1);
            returnType = m.group(2);
        } else {
            // should never happen, unless Neo4j changes signature syntax
            signature = fullSignature;
            returnType = "<?>";
        }

        this.name = name;
        this.hasParameters = !this.signature.startsWith("()");
        this.arity = calculateArity();
    }

    public InvokableInformation(String name, String signature, String returnType) {
        this.name = name;
        this.signature = signature;
        this.returnType = returnType;
        this.hasParameters = !Objects.equals(signature, "()");
        this.arity = calculateArity();
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    public String getReturnType() {
        return returnType;
    }

    public boolean hasParameters() {
        return hasParameters;
    }

    public Range<Integer> getArity() {
        return arity;
    }

    private Range<Integer> calculateArity() {
        if (!hasParameters) {
            return new Range<>(0, 0);
        }

        int from = 0;
        int to = 0;
        String[] args = signature.substring(1, signature.length() - 1).split(",");
        for (String arg : args) {
            String p = arg.trim();
            if (p.endsWith("...")) {
                from++;
                to = Integer.MAX_VALUE;
                break;
            } else if (p.contains("=")) {
                to++;
            } else {
                from++;
                to++;
            }
        }

        return new Range<>(from, to);
    }
}
