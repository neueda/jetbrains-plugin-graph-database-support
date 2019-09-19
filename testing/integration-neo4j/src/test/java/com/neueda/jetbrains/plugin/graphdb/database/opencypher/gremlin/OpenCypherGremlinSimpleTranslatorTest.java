package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class OpenCypherGremlinSimpleTranslatorTest {
    @Test
    public void name() {


        new OpenCypherGremlinSimpleTranslator().translate("ter", Collections.emptyMap());
    }
}