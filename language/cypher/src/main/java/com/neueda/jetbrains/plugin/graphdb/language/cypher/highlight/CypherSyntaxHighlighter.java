package com.neueda.jetbrains.plugin.graphdb.language.cypher.highlight;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.lexer.CypherLexerAdapter;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTokenType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;


/**
 * Syntax highlight.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherSyntaxHighlighter extends SyntaxHighlighterBase {

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.BAD_CHARACTER};

    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.LINE_COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.BLOCK_COMMENT};

    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.KEYWORD};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.IDENTIFIER};
    private static final TextAttributesKey[] FUNCTION_NAME_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.FUNCTION_NAME};

    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.NUMBER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.STRING};

    private static final TextAttributesKey[] OPERATION_SIGN_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.OPERATION_SIGN};
    private static final TextAttributesKey[] SEMICOLON_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.SEMICOLON};
    private static final TextAttributesKey[] PARENTHESES_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.PARENTHESES};
    private static final TextAttributesKey[] CURLY_BRACES_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.CURLY_BRACES};
    private static final TextAttributesKey[] SQUARE_BRACES_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.SQUARE_BRACES};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.COMMA};
    private static final TextAttributesKey[] DOT_KEYS = new TextAttributesKey[]{CypherTextAttributeKey.DOT};

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(CypherTypes.LINECOMMENT)) {
            return LINE_COMMENT_KEYS;
        }
        if (tokenType.equals(CypherTypes.BLOCKCOMMENT)) {
            return BLOCK_COMMENT_KEYS;
        }
        if (isKeywordTokenType(tokenType)) {
            return KEYWORD_KEYS;
        }
        if (tokenType.equals(CypherTypes.L_IDENTIFIER) || tokenType.equals(CypherTypes.L_IDENTIFIER_TEXT)) {
            return IDENTIFIER_KEYS;
        }
        // disabled: functions are replaced with identifiers
//        if (tokenType.equals(CypherTypes.L_FUNCTION)) {
//            return FUNCTION_NAME_KEYS;
//        }
        if (tokenType.equals(CypherTypes.L_DECIMAL) || tokenType.equals(CypherTypes.L_INTEGER)) {
            return NUMBER_KEYS;
        }
        if (tokenType.equals(CypherTypes.L_STRING)) {
            return STRING_KEYS;
        }
        if (tokenType.equals(CypherTypes.SEMICOLON)) {
            return SEMICOLON_KEYS;
        }
        if (tokenType.equals(CypherTypes.PARENTHESE_OPEN) || tokenType.equals(CypherTypes.PARENTHESE_CLOSE)) {
            return PARENTHESES_KEYS;
        }
        if (tokenType.equals(CypherTypes.BRACKET_CURLYOPEN)
                || tokenType.equals(CypherTypes.BRACKET_CURLYCLOSE)) {
            return CURLY_BRACES_KEYS;
        }
        if (tokenType.equals(CypherTypes.BRACKET_SQUAREOPEN)
                || tokenType.equals(CypherTypes.BRACKET_SQUARECLOSE)) {
            return SQUARE_BRACES_KEYS;
        }
        if (tokenType.equals(CypherTypes.OP_COMMA)) {
            return COMMA_KEYS;
        }
        if (tokenType.equals(CypherTypes.OP_DOT)) {
            return DOT_KEYS;
        }
        if (isOperationTokenType(tokenType)) {
            return OPERATION_SIGN_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }

    private boolean isKeywordTokenType(IElementType tokenType) {
        if (tokenType instanceof CypherTokenType) {
            CypherTokenType cypherTokenType = (CypherTokenType) tokenType;
            return cypherTokenType.getOriginalName().startsWith("K_");
        }
        return false;
    }

    private boolean isOperationTokenType(IElementType tokenType) {
        return tokenType.equals(CypherTypes.OP_BACTICK)
                || tokenType.equals(CypherTypes.OP_COLON)
                || tokenType.equals(CypherTypes.OP_DIVIDE)
                || tokenType.equals(CypherTypes.OP_EQUAL)
                || tokenType.equals(CypherTypes.OP_GREATERTHANEQUALS)
                || tokenType.equals(CypherTypes.OP_GREATHERTHEN)
                || tokenType.equals(CypherTypes.OP_INVALIDNOTEQUALS)
                || tokenType.equals(CypherTypes.OP_LESSTHANEQUALS)
                || tokenType.equals(CypherTypes.OP_LESSTHEN)
                || tokenType.equals(CypherTypes.OP_MINUS)
                || tokenType.equals(CypherTypes.OP_MODULO)
                || tokenType.equals(CypherTypes.OP_MUL)
                || tokenType.equals(CypherTypes.OP_NOTEQUALS)
                || tokenType.equals(CypherTypes.OP_PIPE)
                || tokenType.equals(CypherTypes.OP_PLUS)
                || tokenType.equals(CypherTypes.OP_PLUSEQUALS)
                || tokenType.equals(CypherTypes.OP_POW)
                || tokenType.equals(CypherTypes.OP_QUESTIONSIGN)
                || tokenType.equals(CypherTypes.OP_RANGE)
                || tokenType.equals(CypherTypes.OP_REGEXMATCH);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new CypherLexerAdapter();
    }
}
