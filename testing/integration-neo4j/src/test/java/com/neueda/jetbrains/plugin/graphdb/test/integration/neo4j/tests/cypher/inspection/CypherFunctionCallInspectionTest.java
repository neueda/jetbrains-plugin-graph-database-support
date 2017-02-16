package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.inspection;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.openapi.util.Pair;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.inspections.CypherFunctionCallInspection;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseInspectionTest;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;

public class CypherFunctionCallInspectionTest extends BaseInspectionTest {

    @Override
    protected Set<Class<? extends LocalInspectionTool>> provideInspectionClasses() {
        return singleton(CypherFunctionCallInspection.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        metadata.addProcedure("test.stringq", "(a :: STRING?)", null);
        metadata.addProcedure("test.numq", "(a :: NUMBER?)", null);
        metadata.addProcedure("test.intq", "(a :: INTEGER?)", null);
        metadata.addProcedure("test.bool", "(a :: BOOLEAN)", null);
        metadata.addProcedure("test.boolq", "(a :: BOOLEAN?)", null);
        metadata.addProcedure("test.float", "(a :: FLOAT)", null);
        metadata.addProcedure("test.floatq", "(a :: FLOAT?)", null);

    }

    public void testTooManyArguments() {
        addDataSourceFileAndCheck("CALL db.awaitIndex" +
                "<error descr=\"too many arguments in call to db.awaitIndex\">(\"12\", 2, 5)</error>");
    }

    public void testNotEnoughArguments() {
        addDataSourceFileAndCheck("CALL db.awaitIndex" +
                "<error descr=\"not enough arguments in call to db.awaitIndex\">()</error>");
    }

    public void testArgumentsExactMatch() {
        addDataSourceFileAndCheck("RETURN toFloat(\"1.2\")");
    }

    public void testVarargsNoArguments() {
        addDataSourceFileAndCheck("RETURN coalesce" +
                "<error descr=\"not enough arguments in call to coalesce\">()</error>");
    }

    public void testVarargsOneArgument() {
        addDataSourceFileAndCheck("RETURN coalesce(a)");
    }

    public void testVarargsManyArguments() {
        addDataSourceFileAndCheck("RETURN coalesce(a, b, c, d)");
    }

    public void testOptionalArgumentPresent() {
        addDataSourceFileAndCheck("return substring(\"cypher\", 2, 5);");
    }

    public void testOptionalArgumentMissing() {
        addDataSourceFileAndCheck("return substring(\"cypher\", 3);");
    }

    public void testOptionalArgumentTooManyArguments() {
        addDataSourceFileAndCheck("return substring" +
                "<error descr=\"too many arguments in call to substring\">(\"a\", 1, 2, 3)</error>");
    }

    private final List<Pair<String, String>> TYPE_EXAMPLES = asList(
            Pair.pair("INTEGER", "1"),
            Pair.pair("INTEGER", "-1"),
            Pair.pair("INTEGER", "+1"),
            Pair.pair("DOUBLE", "1.4"),
            Pair.pair("DOUBLE", "-1.4"),
            Pair.pair("DOUBLE", "+1.4"),
            Pair.pair("TRUE", "true"),
            Pair.pair("FALSE", "false"),
            Pair.pair("NULL", "null"),
            Pair.pair("STRING", "\"str\"")
    );

    public void testStringTypeCheck() {
        String call = "return ltrim(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (Objects.equals(p.first, "STRING")) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected STRING, got " + p.first + "\">" + p.second + "</error>"));

            }
            deletFile();
        });
    }

    public void testNullableStringTypeCheck() {
        String call = "call test.stringq(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("STRING", "NULL").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected STRING?, got " + p.first + "\">" + p.second + "</error>"));

            }
            deletFile();
        });
    }

    public void testNumberTypeCheck() {
        String call = "return sin(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("INTEGER", "DOUBLE").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected NUMBER, got " + p.first + "\">" + p.second + "</error>"));
            }
            deletFile();
        });
    }

    public void testNullableNumberTypeCheck() {
        String call = "call test.numq(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("INTEGER", "DOUBLE", "NULL").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected NUMBER?, got " + p.first + "\">" + p.second + "</error>"));
            }
            deletFile();
        });
    }

    public void testIntegerTypeCheck() {
        String call = "return substring(\"a\", %s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("INTEGER").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected INTEGER, got " + p.first + "\">" + p.second + "</error>"));
            }
            deletFile();
        });
    }

    public void testNullableIntegerTypeCheck() {
        String call = "call test.intq(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("INTEGER", "NULL").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected INTEGER?, got " + p.first + "\">" + p.second + "</error>"));
            }
            deletFile();
        });
    }

    public void testBooleanTypeCheck() {
        String call = "call test.bool(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("TRUE", "FALSE").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected BOOLEAN, got " + p.first + "\">" + p.second + "</error>"));
            }
            deletFile();
        });
    }

    public void testNullableBooleanTypeCheck() {
        String call = "call test.boolq(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("NULL", "TRUE", "FALSE").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected BOOLEAN?, got " + p.first + "\">" + p.second + "</error>"));
            }
            deletFile();
        });
    }

    public void testFloatTypeCheck() {
        String call = "call test.float(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("DOUBLE").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected FLOAT, got " + p.first + "\">" + p.second + "</error>"));
            }
            deletFile();
        });
    }

    public void testNullableFloatTypeCheck() {
        String call = "call test.floatq(%s)";

        TYPE_EXAMPLES.forEach(p -> {
            if (asList("NULL", "DOUBLE").contains(p.first)) {
                addDataSourceFileAndCheck(String.format(call, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(call,
                        "<error descr=\"expected FLOAT?, got " + p.first + "\">" + p.second + "</error>"));
            }
            deletFile();
        });
    }
}
