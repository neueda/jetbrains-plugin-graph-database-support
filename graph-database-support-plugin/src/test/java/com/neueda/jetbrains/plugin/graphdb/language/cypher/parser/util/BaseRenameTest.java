package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util;

import com.google.common.io.Resources;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

public abstract class BaseRenameTest extends LightCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return Resources.getResource("rename").getFile();
    }
}
