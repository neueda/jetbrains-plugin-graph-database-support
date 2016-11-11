package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

import org.jetbrains.annotations.NotNull;

import com.google.common.io.Resources;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

public class BaseCodeInsightTest extends LightCodeInsightFixtureTestCase {

    private String namespace;
    private final String dataPath;

    public BaseCodeInsightTest(String namespace, String dataPath) {
        this.namespace = namespace;
        this.dataPath = dataPath;
    }

    @Override
    protected String getTestDataPath() {
        return Resources.getResource(namespace).getFile() + "/" + dataPath;
    }

    @NotNull
    protected String getTestName() {
        return getTestName(false);
    }
}
