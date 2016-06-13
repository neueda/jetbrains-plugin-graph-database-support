package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

/**
 * TODO: Description
 *
 * @author dmitry@vrublevsky.me
 */
public final class PsiUtil {

    private PsiUtil() {
    }

    public static TextRange rangeFrom(PsiElement element) {
        return new TextRange(0, element.getText().length());
    }
}
