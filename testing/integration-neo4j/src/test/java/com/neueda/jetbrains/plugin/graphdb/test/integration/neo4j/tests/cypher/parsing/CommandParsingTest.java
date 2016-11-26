package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.parsing;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseParsingTest;

public class CommandParsingTest extends BaseParsingTest {

    public CommandParsingTest() {
        super("command");
    }

    public void testConstraintPropertyExistsNodeCreate() {
        doTest(true);
    }

    public void testConstraintPropertyExistsNodeDrop() {
        doTest(true);
    }

    public void testConstraintPropertyExistsRelCreate() {
        doTest(true);
    }

    public void testConstraintPropertyExistsRelDrop() {
        doTest(true);
    }

    public void testConstraintPropertyUniqueCreate() {
        doTest(true);
    }

    public void testConstraintPropertyUniqueDrop() {
        doTest(true);
    }

    public void testIndexCreate() {
        doTest(true);
    }

    public void testIndexDrop() {
        doTest(true);
    }
}
