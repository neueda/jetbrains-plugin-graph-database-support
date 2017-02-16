package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.lookup.LookupElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherBuiltInFunctionElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherFunctionReturnType.*;

public final class CypherBuiltInFunctions {

    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_PREDICATE = Lists.newArrayList(
            element("all", "(variable IN list WHERE predicate :: ANY)", BOOLEAN.single()),
            element("any", "(variable IN list WHERE predicate :: ANY)", BOOLEAN.single()),
            element("none", "(variable in list WHERE predicate :: ANY)", BOOLEAN.single()),
            element("single", "(variable in list WHERE predicate :: ANY)", BOOLEAN.single()),
            element("exists", "(pattern :: ANY)", BOOLEAN.single()),
            element("exists", "(property :: ANY)", BOOLEAN.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_SHORTEST_PATH = Lists.newArrayList(
            element("shortestPath", "(pattern :: PATH)", PATH.single()),
            element("allShortestPaths", "(pattern :: PATH)", PATH.array())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_SCALAR = Lists.newArrayList(
            element("size", "(list :: LIST OF ANY)", INTEGER.single()),
            element("size", "(pattern :: ANY)", INTEGER.single()),
            element("length", "(path :: ANY)", INTEGER.single()),
            element("length", "(string :: STRING)", INTEGER.single()),
            element("type", "(relationship :: RELATIONSHIP)", STRING.single()),
            element("id", "(node :: NODE)", INTEGER.single()),
            element("id", "(relationship :: RELATIONSHIP)", INTEGER.single()),
            element("coalesce", "(expression... :: ANY)", ANY.single()),
            element("head", "(expression :: LIST OF ANY)", ANY.single()),
            element("last", "(expression :: LIST OF ANY)", ANY.single()),
            element("timestamp", "()", INTEGER.single()),
            element("startNode", "(relationship :: RELATIONSHIP)", NODE.single()),
            element("endNode", "(relationship :: RELATIONSHIP)", NODE.single()),
            element("properties", "(node :: NODE)", MAP.single()),
            element("properties", "(relationship :: RELATIONSHIP)", MAP.single()),
            element("toInt", "(expression :: STRING)", INTEGER.single()),
            element("toFloat", "(expression :: STRING)", FLOAT.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_LIST = Lists.newArrayList(
            element("nodes", "(path :: PATH)", NODE.array()),
            element("relationships", "(path :: PATH)", RELATIONSHIP.array()),
            element("labels", "(node :: NODE)", STRING.array()),
            element("keys", "(node :: NODE)", STRING.array()),
            element("keys", "(relationship :: RELATIONSHIP)", STRING.array()),
            element("extract", "(variable IN list | expression :: ANY)", ANY.array()),
            element("filter", "(variable IN list WHERE predicate :: ANY)", ANY.array()),
            element("tail", "(expression :: LIST OF ANY)", ANY.array()),
            element("range", "(start :: INTEGER, end :: INTEGER, step = 1 :: INTEGER)", INTEGER.array()),
            element("reduce", "(accumulator = initial :: ANY, variable IN list | expression :: ANY)", ANY.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_NUMERIC = Lists.newArrayList(
            element("abs", "(expression :: NUMBER)", INTEGER.single()),
            element("ceil", "(expression :: NUMBER)", INTEGER.single()),
            element("floor", "(expression :: NUMBER)", INTEGER.single()),
            element("round", "(expression :: NUMBER)", INTEGER.single()),
            element("sign", "(expression :: NUMBER)", INTEGER.single()),
            element("rand", "()", FLOAT.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_LOGARITHMIC = Lists.newArrayList(
            element("log", "(expression :: NUMBER)", FLOAT.single()),
            element("log10", "(expression :: NUMBER)", FLOAT.single()),
            element("exp", "(expression :: NUMBER)", FLOAT.single()),
            element("e", "()", FLOAT.single()),
            element("sqrt", "(expression :: NUMBER)", FLOAT.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_TRIGONOMETRIC = Lists.newArrayList(
            element("sin", "(expression :: NUMBER)", FLOAT.single()),
            element("cos", "(expression :: NUMBER)", FLOAT.single()),
            element("tan", "(expression :: NUMBER)", FLOAT.single()),
            element("cot", "(expression :: NUMBER)", FLOAT.single()),
            element("asin", "(expression :: NUMBER)", FLOAT.single()),
            element("acos", "(expression :: NUMBER)", FLOAT.single()),
            element("atan", "(expression :: NUMBER)", FLOAT.single()),
            element("atan2", "(expression :: NUMBER, expression :: NUMBER)", FLOAT.single()),
            element("pi", "()", FLOAT.single()),
            element("degrees", "(expression :: NUMBER)", FLOAT.single()),
            element("radians", "(expression :: NUMBER)", FLOAT.single()),
            element("haversin", "(expression :: NUMBER)", FLOAT.single())
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_STRING = Lists.newArrayList(
            element("replace", "(original :: STRING, search :: STRING, replace :: STRING)", STRING.single()),
            element("substring", "(original :: STRING, start :: INTEGER, length = length(original) :: INTEGER)", STRING.single()),
            element("left", "(original :: STRING, length :: INTEGER)", STRING.single()),
            element("right", "(original :: STRING, length :: INTEGER)", STRING.single()),
            element("ltrim", "(original :: STRING)", STRING.single()),
            element("rtrim", "(original :: STRING)", STRING.single()),
            element("trim", "(original :: STRING)", STRING.single()),
            element("lower", "(original :: STRING)", STRING.single()),
            element("upper", "(original :: STRING)", STRING.single()),
            element("split", "(original :: STRING, splitPattern :: STRING)", STRING.array()),
            element("reverse", "(original :: STRING)", STRING.single()),
            element("toString", "(expression :: STRING)", STRING.single())
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
            .map(CypherBuiltInFunctionElement::getInvokable)
            .map(InvokableInformation::getName)
            .distinct()
            .collect(Collectors.toList());

    private static CypherBuiltInFunctionElement element(String functionName,
                                                        String functionSignature,
                                                        String functionReturnType) {
        return new CypherBuiltInFunctionElement(
                new InvokableInformation(functionName, functionSignature, functionReturnType));
    }
}
