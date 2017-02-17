package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.lookup.LookupElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherBuiltInFunctionElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.*;

public final class CypherBuiltInFunctions {

    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_PREDICATE = Lists.newArrayList(
            element("all", "(variable IN list WHERE predicate :: ANY)", BOOLEAN),
            element("any", "(variable IN list WHERE predicate :: ANY)", BOOLEAN),
            element("none", "(variable in list WHERE predicate :: ANY)", BOOLEAN),
            element("single", "(variable in list WHERE predicate :: ANY)", BOOLEAN),
            element("exists", "(pattern :: ANY)", BOOLEAN),
            element("exists", "(property :: ANY)", BOOLEAN)
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_SHORTEST_PATH = Lists.newArrayList(
            element("shortestPath", "(pattern :: PATH)", PATH),
            element("allShortestPaths", "(pattern :: PATH)", CypherList.of(PATH))
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_SCALAR = Lists.newArrayList(
            element("size", "(list :: LIST OF ANY)", INTEGER),
            element("size", "(pattern :: ANY)", INTEGER),
            element("length", "(path :: ANY)", INTEGER),
            element("length", "(string :: STRING)", INTEGER),
            element("type", "(relationship :: RELATIONSHIP)", STRING),
            element("id", "(node :: NODE)", INTEGER),
            element("id", "(relationship :: RELATIONSHIP)", INTEGER),
            element("coalesce", "(expression... :: ANY)", ANY),
            element("head", "(expression :: LIST OF ANY)", ANY),
            element("last", "(expression :: LIST OF ANY)", ANY),
            element("timestamp", "()", INTEGER),
            element("startNode", "(relationship :: RELATIONSHIP)", NODE),
            element("endNode", "(relationship :: RELATIONSHIP)", NODE),
            element("properties", "(node :: NODE)", MAP),
            element("properties", "(relationship :: RELATIONSHIP)", MAP),
            element("toInt", "(expression :: STRING)", INTEGER),
            element("toFloat", "(expression :: STRING)", FLOAT)
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_LIST = Lists.newArrayList(
            element("nodes", "(path :: PATH)", CypherList.of(NODE)),
            element("relationships", "(path :: PATH)", CypherList.of(RELATIONSHIP)),
            element("labels", "(node :: NODE)", CypherList.of(STRING)),
            element("keys", "(node :: NODE)", CypherList.of(STRING)),
            element("keys", "(relationship :: RELATIONSHIP)", CypherList.of(STRING)),
            element("extract", "(variable IN list | expression :: ANY)", CypherList.of(ANY)),
            element("filter", "(variable IN list WHERE predicate :: ANY)", CypherList.of(ANY)),
            element("tail", "(expression :: LIST OF ANY)", CypherList.of(ANY)),
            element("range", "(start :: INTEGER, end :: INTEGER, step = 1 :: INTEGER)", CypherList.of(INTEGER)),
                    element("reduce", "(accumulator = initial :: ANY, variable IN list | expression :: ANY)", ANY)
            );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_NUMERIC = Lists.newArrayList(
            element("abs", "(expression :: NUMBER)", INTEGER),
            element("ceil", "(expression :: NUMBER)", INTEGER),
            element("floor", "(expression :: NUMBER)", INTEGER),
            element("round", "(expression :: NUMBER)", INTEGER),
            element("sign", "(expression :: NUMBER)", INTEGER),
            element("rand", "()", FLOAT)
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_LOGARITHMIC = Lists.newArrayList(
            element("log", "(expression :: NUMBER)", FLOAT),
            element("log10", "(expression :: NUMBER)", FLOAT),
            element("exp", "(expression :: NUMBER)", FLOAT),
            element("e", "()", FLOAT),
            element("sqrt", "(expression :: NUMBER)", FLOAT)
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_MATH_TRIGONOMETRIC = Lists.newArrayList(
            element("sin", "(expression :: NUMBER)", FLOAT),
            element("cos", "(expression :: NUMBER)", FLOAT),
            element("tan", "(expression :: NUMBER)", FLOAT),
            element("cot", "(expression :: NUMBER)", FLOAT),
            element("asin", "(expression :: NUMBER)", FLOAT),
            element("acos", "(expression :: NUMBER)", FLOAT),
            element("atan", "(expression :: NUMBER)", FLOAT),
            element("atan2", "(expression :: NUMBER, expression :: NUMBER)", FLOAT),
            element("pi", "()", FLOAT),
            element("degrees", "(expression :: NUMBER)", FLOAT),
            element("radians", "(expression :: NUMBER)", FLOAT),
            element("haversin", "(expression :: NUMBER)", FLOAT)
    );
    private static final List<CypherBuiltInFunctionElement> FUNCTIONS_STRING = Lists.newArrayList(
            element("replace", "(original :: STRING, search :: STRING, replace :: STRING)", STRING),
            element("substring", "(original :: STRING, start :: INTEGER, length = length(original) :: INTEGER)", STRING),
            element("left", "(original :: STRING, length :: INTEGER)", STRING),
            element("right", "(original :: STRING, length :: INTEGER)", STRING),
            element("ltrim", "(original :: STRING)", STRING),
            element("rtrim", "(original :: STRING)", STRING),
            element("trim", "(original :: STRING)", STRING),
            element("lower", "(original :: STRING)", STRING),
            element("upper", "(original :: STRING)", STRING),
            element("split", "(original :: STRING, splitPattern :: STRING)", CypherList.of(STRING)),
            element("reverse", "(original :: STRING)", STRING),
            element("toString", "(expression :: STRING)", STRING)
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

    private static CypherBuiltInFunctionElement element(String name, String signature, CypherType returnType) {
        return new CypherBuiltInFunctionElement(
                new InvokableInformation(name, signature, returnType));
    }
}
