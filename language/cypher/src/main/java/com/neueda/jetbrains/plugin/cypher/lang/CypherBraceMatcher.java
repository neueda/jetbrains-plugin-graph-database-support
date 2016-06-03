package com.neueda.jetbrains.plugin.cypher.lang;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * TODO: Description
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherBraceMatcher implements PairedBraceMatcher {
    private static BracePair[] PAIRS = {
            new BracePair(CypherTypes.PARENTHESE_OPEN, CypherTypes.PARENTHESE_CLOSE, true),
            new BracePair(CypherTypes.BRACKET_SQUAREOPEN, CypherTypes.BRACKET_SQUARECLOSE, true),
            // todo: curly braces are a bit buggy?
            new BracePair(CypherTypes.BRACKET_CURLYOPEN, CypherTypes.BRACKET_CURLYCLOSE, true),
    };

    @Override
    public BracePair[] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
