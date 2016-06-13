package com.neueda.jetbrains.plugin.graphdb.jetbrains.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.cypher.psi.CypherStatement;

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
            PsiElement elementAtCaret = psiFile.findElementAt(offset);
            return PsiTraversalUtilities.getParentOfType(elementAtCaret, CypherStatement.class);
        }
    }
}
