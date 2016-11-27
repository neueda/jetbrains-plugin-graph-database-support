package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util;

import com.google.common.io.Resources;
import org.jetbrains.annotations.NotNull;

abstract class BaseCodeInsightTest extends BaseGenericTest {

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
