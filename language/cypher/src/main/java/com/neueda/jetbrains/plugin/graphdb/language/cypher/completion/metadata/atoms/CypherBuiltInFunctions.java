package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.lookup.LookupElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherBuiltInFunctionElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.ANY;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.BOOLEAN;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.FLOAT;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.INTEGER;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.MAP;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.NODE;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.PATH;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.RELATIONSHIP;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.STRING;

public final class CypherBuiltInFunctions {

    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_PREDICATE = Lists.newArrayList(
            element("all", "(variable IN list WHERE predicate)", BOOLEAN.single()),
            element("any", "(variable IN list WHERE predicate)", BOOLEAN.single()),
            element("none", "(variable in list WHERE predicate)", BOOLEAN.single()),
            element("single", "(variable in list WHERE predicate)", BOOLEAN.single()),
            element("exists", "(pattern)", BOOLEAN.single()),
            element("exists", "(property)", BOOLEAN.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_SHORTEST_PATH = Lists.newArrayList(
            element("shortestPath", "(pattern)", PATH.single()),
            element("allShortestPaths", "(pattern)", PATH.array())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_SCALAR = Lists.newArrayList(
            element("size", "(list)", INTEGER.single()),
            element("size", "(pattern)", INTEGER.single()),
            element("length", "(path)", INTEGER.single()),
            element("length", "(string)", INTEGER.single()),
            element("type", "(relationship)", STRING.single()),
            element("id", "(node)", INTEGER.single()),
            element("id", "(relationship)", INTEGER.single()),
            element("coalesce", "(expression [, expression]*)", ANY.single()),
            element("head", "(expression)", ANY.single()),
            element("last", "(expression)", ANY.single()),
            element("timestamp", "()", INTEGER.single()),
            element("startNode", "(relationship)", NODE.single()),
            element("endNode", "(relationship", NODE.single()),
            element("properties", "(node)", MAP.single()),
            element("properties", "(relationship)", MAP.single()),
            element("toInt", "(expression)", INTEGER.single()),
            element("toFloat", "(expression)", FLOAT.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_LIST = Lists.newArrayList(
            element("nodes", "(path)", NODE.array()),
            element("relationship", "(path)", RELATIONSHIP.array()),
            element("labels", "(node)", STRING.array()),
            element("keys", "(node)", STRING.array()),
            element("keys", "(relationship)", STRING.array()),
            element("extract", "(variable IN list | expression)", ANY.array()),
            element("filter", "(variable IN list WHERE predicate)", ANY.array()),
            element("tail", "(expression)", ANY.array()),
            element("range", "(start, end [, step])", INTEGER.array()),
            element("reduce", "(accumulator = initial, variable IN list | expression)", ANY.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_NUMERIC = Lists.newArrayList(
            element("abs", "(expression)", INTEGER.single()),
            element("ceil", "(expression)", INTEGER.single()),
            element("floor", "(expression)", INTEGER.single()),
            element("round", "(expression)", INTEGER.single()),
            element("sign", "(expression)", INTEGER.single()),
            element("rand", "()", FLOAT.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_LOGARITHMIC = Lists.newArrayList(
            element("log", "(expression)", FLOAT.single()),
            element("log10", "(expression)", FLOAT.single()),
            element("exp", "(expression)", FLOAT.single()),
            element("e", "()", FLOAT.single()),
            element("sqrt", "(expression)", FLOAT.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_TRIGONOMETRIC = Lists.newArrayList(
            element("sin", "(expression)", FLOAT.single()),
            element("cos", "(expression)", FLOAT.single()),
            element("tan", "(expression)", FLOAT.single()),
            element("cot", "(expression)", FLOAT.single()),
            element("asin", "(expression)", FLOAT.single()),
            element("acos", "(expression)", FLOAT.single()),
            element("atan", "(expression)", FLOAT.single()),
            element("atan", "(expression, expression)", FLOAT.single()),
            element("pi", "()", FLOAT.single()),
            element("degrees", "(expression)", FLOAT.single()),
            element("radians", "(expression)", FLOAT.single()),
            element("haversin", "(expression)", FLOAT.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_STRING = Lists.newArrayList(
            element("replace", "(original, search, replace)", STRING.single()),
            element("substring", "(original, start [, length])", STRING.single()),
            element("left", "(original, length)", STRING.single()),
            element("right", "(original, length)", STRING.single()),
            element("ltrim", "(original)", STRING.single()),
            element("rtrim", "(original)", STRING.single()),
            element("trim", "(original)", STRING.single()),
            element("lower", "(original)", STRING.single()),
            element("upper", "(original)", STRING.single()),
            element("split", "(original, splitPattern)", STRING.array()),
            element("reverse", "(original)", STRING.single()),
            element("toString", "(expression)", STRING.single())
    );

    public static final List<CypherBuiltInFunctionElement> FUNCTIONS = new ArrayList<CypherBuiltInFunctionElement>() {{
        addAll(FUNCTIONS_PREDICATE);
        addAll(FUNCTIONS_SHORTEST_PATH);
        addAll(FUNCTIONS_SCALAR);
        addAll(FUNCTIONS_LIST);
        addAll(FUNCTIONS_MATH_NUMERIC);
        addAll(FUNCTIONS_MATH_LOGARITHMIC);
        addAll(FUNCTIONS_MATH_TRIGONOMETRIC);
        addAll(FUNCTIONS_STRING);
    }};

    public static final List<LookupElement> FUNCTION_LOOKUP_ELEMENTS = FUNCTIONS.stream()
            .map(CypherBuiltInFunctionElement::getLookupElement)
            .collect(Collectors.toList());

    public static final List<String> FUNCTION_NAMES = FUNCTIONS.stream()
            .map(CypherBuiltInFunctionElement::getFunctionName)
            .collect(Collectors.toList());

    private static CypherBuiltInFunctionElement element(String functionName,
                                                        String functionSignature,
                                                        String functionReturnType) {
        return new CypherBuiltInFunctionElement(functionName, functionSignature, functionReturnType);
    }
}
