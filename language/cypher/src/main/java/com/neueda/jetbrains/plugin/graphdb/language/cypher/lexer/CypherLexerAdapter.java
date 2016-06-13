package com.neueda.jetbrains.plugin.graphdb.language.cypher.lexer;

import com.intellij.lexer.FlexAdapter;

/**
 * TODO: Description
 *
 * @author dmitry@vrublesvky.me
 */
public class CypherLexerAdapter extends FlexAdapter {

    public CypherLexerAdapter() {
        super(new _CypherLexer());
    }
}
