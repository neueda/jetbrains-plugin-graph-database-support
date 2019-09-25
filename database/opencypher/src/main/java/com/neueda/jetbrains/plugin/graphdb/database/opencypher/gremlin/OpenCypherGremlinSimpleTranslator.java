package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin;

import org.opencypher.gremlin.translation.TranslationFacade;

import java.util.Map;

public class OpenCypherGremlinSimpleTranslator {
    public String translate(String cypher, Map<String, Object> parameters) {
        TranslationFacade cfog = new TranslationFacade();
        return cfog.toGremlinGroovy(cypher, parameters);
    }
}
