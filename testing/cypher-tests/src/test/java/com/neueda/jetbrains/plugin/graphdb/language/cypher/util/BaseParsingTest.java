package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

import com.google.common.io.Resources;
import com.intellij.testFramework.ParsingTestCase;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherParserDefinition;

/**
 * Base for all parsing test cases.
 */
public abstract class BaseParsingTest  extends ParsingTestCase {

    public BaseParsingTest(String testDataFolder) {
        super(testDataFolder, "cyp", new CypherParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return Resources.getResource("parsing").getFile();
    }

    @Override
    protected boolean skipSpaces() {
        return true;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }
}
