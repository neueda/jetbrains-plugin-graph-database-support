package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

public abstract class BaseRenameTest extends BaseCodeInsightTest {

    public BaseRenameTest(String dataPath) {
        super("rename", dataPath);
    }

    protected void verify(String newName) {
        myFixture.configureByFiles(getTestName() + ".cyp");
        myFixture.renameElementAtCaret(newName);
        myFixture.checkResultByFile(getTestName() + "After.cyp");
    }
}
