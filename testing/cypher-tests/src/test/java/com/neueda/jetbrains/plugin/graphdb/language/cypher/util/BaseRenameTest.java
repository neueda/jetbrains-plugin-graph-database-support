package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

import org.jetbrains.annotations.NotNull;

import com.google.common.io.Resources;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

public abstract class BaseRenameTest extends LightCodeInsightFixtureTestCase {

    private final String dataPath;

    public BaseRenameTest(String dataPath) {
        this.dataPath = dataPath;
    }

    @Override
    protected String getTestDataPath() {
        return Resources.getResource("rename").getFile() + "/" + dataPath;
    }

    protected void verify(String newName) {
        myFixture.configureByFiles(getTestName() + ".cyp");
        myFixture.renameElementAtCaret(newName);
        myFixture.checkResultByFile(getTestName() + "After.cyp");
    }

    @NotNull
    private String getTestName() {
        return getTestName(false);
    }
}
