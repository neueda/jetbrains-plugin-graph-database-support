package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Range;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public final class InvokableInformation {

    public static class Argument {
        public final String name;
        public final CypherType type;
        public final String typeString;
        public final boolean nullable;
        public final boolean optional;
        public final boolean varArgs;

        Argument(String definition) {
            String[] s = definition.trim().split("::");
            name = s[0].split("=")[0].split("\\.\\.\\.")[0].trim();
            typeString = s.length > 1 ? s[1].trim() : "ANY";
            nullable = typeString.contains("?");
            type = CypherType.parse(typeString);
            optional = s[0].contains("=");
            varArgs = s[0].contains("...");
        }
    }

    private final String name;
    private final String signature;
    private final String returnTypeString;
    private final CypherType returnType;

    private final boolean hasParameters;
    private final List<Argument> arguments;
    private final Range<Integer> arity;

    public InvokableInformation(String fullSignature, String name) {
        String signatureWithoutName = StringUtil.trimStart(fullSignature, name);
        Matcher m = CypherElementWithSignature.FULL_SIGNATURE_REGEXP.matcher(signatureWithoutName);
        if (m.find()) {
            signature = m.group(1);
            returnTypeString = m.group(2);
        } else {
            // should never happen, unless Neo4j changes signature syntax
            signature = fullSignature;
            returnTypeString = "ANY?";
        }

        returnType = CypherType.parse(returnTypeString);

        this.name = name;
        this.hasParameters = !this.signature.startsWith("()");

        this.arguments = parseArguments();
        this.arity = calculateArity();
    }

    public InvokableInformation(String name, String signature, CypherType returnType) {
        this.name = name;
        this.signature = signature;
        this.returnType = returnType;
        this.returnTypeString = returnType.toString();

        this.hasParameters = !Objects.equals(signature, "()");

        this.arguments = parseArguments();
        this.arity = calculateArity();
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    public CypherType getReturnType() {
        return returnType;
    }

    public String getReturnTypeString() {
        return returnTypeString;
    }

    public boolean hasParameters() {
        return hasParameters;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public Range<Integer> getArity() {
        return arity;
    }

    private List<Argument> parseArguments() {
        if (!hasParameters) {
            return emptyList();
        }

        return Stream.of(signature.substring(1, signature.length() - 1).split(","))
                .map(Argument::new)
                .collect(toList());
    }

    private Range<Integer> calculateArity() {
        if (arguments.isEmpty()) {
            return new Range<>(0, 0);
        }

        int from = 0;
        int to = 0;
        for (Argument arg : arguments) {
            if (arg.varArgs) {
                from++;
                to = Integer.MAX_VALUE;
                break;
            } else if (arg.optional) {
                to++;
            } else {
                from++;
                to++;
            }
        }

        return new Range<>(from, to);
    }

}
