package com.neueda.jetbrains.plugin.graphdb.jetbrains.languages;

import java.util.ArrayList;
import java.util.List;

public class GraphLanguages {

    public static final List<String> SUPPORTED_LANGUAGES = new ArrayList<>();
    public static final String CYPHER = "Cypher";

    static {
        SUPPORTED_LANGUAGES.add(CYPHER);
    }
}
