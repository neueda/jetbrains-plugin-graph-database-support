package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter.listener;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ui.Colors;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.psi.PsiTraversalUtilities;

import java.awt.Font;

public class AbstractQueryHighlighterListener {

    protected void highlightStatement(Editor editor, PsiFile psiFile) {
        if (psiFile == null) {
            return;
        }
        if (psiFile.getLanguage().getID().equals("Cypher")) {
            highlightStatementInCypherFile(editor, psiFile);
        }
    }

    private void highlightStatementInCypherFile(Editor editor, PsiFile psiFile) {
        editor.getMarkupModel().removeAllHighlighters();

        Caret caret = editor.getCaretModel().getPrimaryCaret();

        PsiElement element = PsiTraversalUtilities.Cypher.getCypherStatementAtOffset(psiFile, caret.getOffset());

        if (element == null) {
            return;
        }
        editor.getMarkupModel().addRangeHighlighter(element.getTextOffset(), element.getTextOffset() + element.getTextLength(),
                HighlighterLayer.ELEMENT_UNDER_CARET,
                new TextAttributes(null, null, Colors.DARK_GREEN, EffectType.BOXED, Font.PLAIN),
                HighlighterTargetArea.EXACT_RANGE);
    }
}
