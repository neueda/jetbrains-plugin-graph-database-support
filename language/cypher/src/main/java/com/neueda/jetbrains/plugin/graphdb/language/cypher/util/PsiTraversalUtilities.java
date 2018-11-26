package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherStatementItem;

public final class PsiTraversalUtilities {

    public static PsiElement getParentOfType(PsiElement element, Class<?> type) {
        if (element == null) {
            return null;
        }
        if (type.isAssignableFrom(element.getClass())) {
            return element;
        }
        return getParentOfType(element.getParent(), type);
    }

    public static final class Cypher {

        public static PsiElement getCypherStatementAtOffset(PsiFile psiFile, int offset) {
            PsiElement elementAtCaret = psiFile.findElementAt(offset == 0 ? offset : offset - 1);
            return PsiTraversalUtilities.getParentOfType(elementAtCaret, CypherStatementItem.class);
        }

        public static PsiElement getCypherStatement(PsiElement element) {
            return PsiTraversalUtilities.getParentOfType(element, CypherStatementItem.class);
        }

    }
}
