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

        metadata.addUserFunction("uany", "() :: (ANY)", null);
        metadata.addUserFunction("ufint", "() :: (INTEGER)", null);
        metadata.addUserFunction("uffloat", "() :: (FLOAT)", null);
        metadata.addUserFunction("ufbool", "() :: (BOOLEAN)", null);
        metadata.addUserFunction("ufstr", "() :: (STRING)", null);
        metadata.addUserFunction("ufnode", "() :: (NODE)", null);
        metadata.addUserFunction("ufpath", "() :: (PATH)", null);
        metadata.addUserFunction("ufrel", "() :: (RELATIONSHIP)", null);

        metadata.addUserFunction("ufrnode", "(a :: NODE) :: (NODE)", null);
        metadata.addUserFunction("ufrpath", "(a :: PATH) :: (NODE)", null);
        metadata.addUserFunction("ufrrel", "(a :: RELATIONSHIP) :: (NODE)", null);

        metadata.addProcedure("test.stringq", "(a :: STRING?) :: VOID", null);
        metadata.addProcedure("test.numq", "(a :: NUMBER?) :: VOID", null);
        metadata.addProcedure("test.intq", "(a :: INTEGER?) :: VOID", null);
        metadata.addProcedure("test.bool", "(a :: BOOLEAN) :: VOID", null);
        metadata.addProcedure("test.boolq", "(a :: BOOLEAN?) :: VOID", null);
        metadata.addProcedure("test.float", "(a :: FLOAT) :: VOID", null);
        metadata.addProcedure("test.floatq", "(a :: FLOAT?) :: VOID", null);

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
            Pair.pair("INTEGER", "ufint()"),
            Pair.pair("FLOAT", "1.4"),
            Pair.pair("FLOAT", "-1.4"),
            Pair.pair("FLOAT", "+1.4"),
            Pair.pair("FLOAT", "uffloat()"),
            Pair.pair("BOOLEAN", "true"),
            Pair.pair("BOOLEAN", "false"),
            Pair.pair("BOOLEAN", "ufbool()"),
            Pair.pair("NULL", "null"),
            Pair.pair("STRING", "\"str\""),
            Pair.pair("STRING", "ufstr()"),
            Pair.pair("NODE", "ufnode()"),
            Pair.pair("NODE", "n"),
            Pair.pair("PATH", "ufpath()"),
            Pair.pair("PATH", "p"),
            Pair.pair("RELATIONSHIP", "ufrel()"),
            Pair.pair("RELATIONSHIP", "r"),
            Pair.pair("ANY", "uany()")
    );

    private void generateTypeCompatibilityTests(String query, String expected, List<String> allowed) {
        TYPE_EXAMPLES.forEach(p -> {
            if (Objects.equals(p.first, "ANY") || allowed.contains(p.first)) {
                addDataSourceFileAndCheck(String.format(query, p.second));
            } else {
                addDataSourceFileAndCheck(String.format(query,
                        "<error descr=\"expected " + expected + ", got " + p.first + "\">" + p.second + "</error>"));

            }
            deleteFile();
        });
    }

    public void testStringTypeCheck() {
        String query = "MATCH p=(n)-[r]-() RETURN ltrim(%s)";
        generateTypeCompatibilityTests(query, "STRING", asList("STRING"));
    }

    public void testNullableStringTypeCheck() {
        String query = "MATCH p=(n)-[r]-() CALL test.stringq(%s) RETURN p";
        generateTypeCompatibilityTests(query, "STRING?", asList("STRING", "NULL"));
    }

    public void testNumberTypeCheck() {
        String query = "MATCH p=(n)-[r]-() RETURN sin(%s)";
        generateTypeCompatibilityTests(query, "NUMBER", asList("INTEGER", "FLOAT"));
    }

    public void testNullableNumberTypeCheck() {
        String query = "MATCH p=(n)-[r]-() CALL test.numq(%s) RETURN p";
        generateTypeCompatibilityTests(query, "NUMBER?", asList("INTEGER", "FLOAT", "NULL"));
    }

    public void testIntegerTypeCheck() {
        String query = "MATCH p=(n)-[r]-() RETURN substring(\"a\", %s)";
        generateTypeCompatibilityTests(query, "INTEGER", asList("INTEGER"));
    }

    public void testNullableIntegerTypeCheck() {
        String query = "MATCH p=(n)-[r]-() CALL test.intq(%s) RETURN p";
        generateTypeCompatibilityTests(query, "INTEGER?", asList("INTEGER", "NULL"));
    }

    public void testBooleanTypeCheck() {
        String query = "MATCH p=(n)-[r]-() CALL test.bool(%s) RETURN p";
        generateTypeCompatibilityTests(query, "BOOLEAN", asList("BOOLEAN"));
    }

    public void testNullableBooleanTypeCheck() {
        String query = "MATCH p=(n)-[r]-() CALL test.boolq(%s) RETURN p";
        generateTypeCompatibilityTests(query, "BOOLEAN?", asList("BOOLEAN", "NULL"));
    }

    public void testFloatTypeCheck() {
        String query = "MATCH p=(n)-[r]-() CALL test.float(%s) RETURN p";
        generateTypeCompatibilityTests(query, "FLOAT", asList("INTEGER", "FLOAT"));
    }

    public void testNullableFloatTypeCheck() {
        String query = "MATCH p=(n)-[r]-() CALL test.floatq(%s) RETURN p";
        generateTypeCompatibilityTests(query, "FLOAT?", asList("INTEGER", "FLOAT", "NULL"));
    }

    public void testPathTypeCheck() {
        String query = "MATCH p=(n)-[r]-() RETURN ufrpath(%s)";
        generateTypeCompatibilityTests(query, "PATH", asList("PATH"));
    }

    public void testNodeTypeCheck() {
        String query = "MATCH p=(n)-[r]-() RETURN ufrnode(%s)";
        generateTypeCompatibilityTests(query, "NODE", asList("NODE"));
    }

    public void testRelationshipTypeCheck() {
        String query = "MATCH p=(n)-[r]-() RETURN ufrrel(%s)";
        generateTypeCompatibilityTests(query, "RELATIONSHIP", asList("RELATIONSHIP"));
    }

    public void testSizeArray() {
        addDataSourceFileAndCheck("RETURN size([])");
    }

    public void testSizePatternExpression() {
        addDataSourceFileAndCheck("MATCH (a) RETURN size((a)-[]-())");
    }

    public void testSizeString() {
        addDataSourceFileAndCheck("RETURN size('')");
    }

    public void testRelationshipSizeWithoutVariableLength() {
        addDataSourceFileAndCheck("MATCH (a)-[c]->(b) WITH size(<error descr=\"expected LIST OF ANY, " +
            "got RELATIONSHIP\">c</error>) as derp RETURN derp");
    }

    public void testRelationshipSizeWithVariableLength() {
        addDataSourceFileAndCheck("MATCH (a)-[c*1..1]->(b) WITH size(c) as derp RETURN derp");
    }

    public void testRelationshipSizeWithStar() {
        addDataSourceFileAndCheck("MATCH (a)-[c*]->(b) WITH size(c) as derp RETURN derp");
    }

}
