package com.neueda.jetbrains.plugin.graphdb.language.cypher;

import com.intellij.lang.Language;

import static com.neueda.jetbrains.plugin.graphdb.platform.SupportedLanguage.CYPHER;

/**
 * @author dmitry@vrublesvky.me
 */
public final class CypherLanguage extends Language {

    public static final CypherLanguage INSTANCE = new CypherLanguage();

    private CypherLanguage() {
        super(CYPHER.getLanguageId());
    }
}
