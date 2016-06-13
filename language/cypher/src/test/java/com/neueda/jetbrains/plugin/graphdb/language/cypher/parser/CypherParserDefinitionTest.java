package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser;

import com.google.common.io.Resources;
import com.intellij.testFramework.ParsingTestCase;

import java.io.File;

/**
 * Test that Cypher parser work correctly.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherParserDefinitionTest extends ParsingTestCase {

    public CypherParserDefinitionTest() {
        super("", "cyp", new CypherParserDefinition());
    }

    public void testCommonCypherParsing() {
        doTest(true);
    }

    @Override
    protected String getTestDataPath() {
        return new File(Resources.getResource("parsingTestData/.root").getFile())
                .getAbsoluteFile().getParent();
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }
}
