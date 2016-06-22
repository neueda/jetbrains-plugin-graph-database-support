package com.neueda.jetbrains.plugin.graphdb.language.cypher;

import com.intellij.lang.Language;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphLanguages;

/**
 * @author dmitry@vrublesvky.me
 */
public class CypherLanguage extends Language {

    public static final CypherLanguage INSTANCE = new CypherLanguage();

    private CypherLanguage() {
        super(GraphLanguages.CYPHER);
    }
}
