package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.inspection;

import com.intellij.codeInspection.LocalInspectionTool;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.inspections.CypherFunctionCallInspection;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseInspectionTest;

import java.util.Set;

import static java.util.Collections.singleton;

public class CypherFunctionCallInspectionTest extends BaseInspectionTest {

    @Override
    protected Set<Class<? extends LocalInspectionTool>> provideInspectionClasses() {
        return singleton(CypherFunctionCallInspection.class);
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
                "<error descr=\"not enough arguments in call to db.coalesce\">()</error>");
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
                "<error descr=\"too many arguments in call to db.substring\">(\"a\", 1, 2, 3)</error>");
    }

}
