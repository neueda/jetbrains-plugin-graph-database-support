package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

public abstract class BaseCompletionTest extends BaseCodeInsightTest {

    public BaseCompletionTest(String dataPath) {
        super("completion", dataPath);
    }
}
