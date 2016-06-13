package com.neueda.jetbrains.plugin.graphdb.language.cypher;

import com.intellij.lang.Language;

/**
 * @author dmitry@vrublesvky.me
 */
public class CypherLanguage extends Language {

    public static final CypherLanguage INSTANCE = new CypherLanguage();

    private CypherLanguage() {
        super("Cypher");
    }
}
