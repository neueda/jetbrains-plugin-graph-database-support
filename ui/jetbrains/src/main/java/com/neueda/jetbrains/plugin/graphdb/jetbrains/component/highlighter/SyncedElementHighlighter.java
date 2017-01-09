package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ui.Colors;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.psi.PsiTraversalUtilities;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphLanguages;

public class SyncedElementHighlighter {

    private final Map<Editor, RangeHighlighter> queryHighlights = new HashMap<>();

    public void highlightStatement(Editor editor, PsiFile psiFile) {
        if (psiFile == null) {
            return;
        }
        if (psiFile.getLanguage().getID().equals(GraphLanguages.CYPHER)) {
            highlightStatementInCypherFile(editor, psiFile);
        }
        // int offset = lastEditor.getCaretModel().getOffset();
        // PsiElement parent = psiFile.findElementAt(offset);
        // PsiElement inside = InjectedLanguageUtil.findInside(parent, psiFile, offset, PsiDocumentManager.getInstance(lastEditor.getProject()));
        // PsiTraversalUtilities.getParentOfType(inside, CypherStatementItem.class);
        // Editor e = InjectedLanguageUtil.getInjectedEditorForInjectedFile(lastEditor, lastEditor.getCaretModel().getPrimaryCaret(), null);
        // PsiFile pf = PsiDocumentManager.getInstance(e.getProject()).getPsiFile(e.getDocument());
    }

    public void dispose() {
        queryHighlights.clear();
    }

    private void highlightStatementInCypherFile(Editor editor, PsiFile psiFile) {
        Caret caret = editor.getCaretModel().getPrimaryCaret();

        PsiElement element = PsiTraversalUtilities.Cypher.getCypherStatementAtOffset(psiFile, caret.getOffset());
        synchronized (this) {
            if (queryHighlights.containsKey(editor)) {
                editor.getMarkupModel().removeHighlighter(queryHighlights.get(editor));
                queryHighlights.remove(editor);
            }

            if (element == null) {
                return;
            }

            try {
                RangeHighlighter rangeHighlighter = editor.getMarkupModel()
                           .addRangeHighlighter(
                                      element.getTextOffset(),
                                      element.getTextOffset() + element.getTextLength(),
                                      HighlighterLayer.FIRST,
                                      new TextAttributes(null, null, Colors.DARK_GREEN, EffectType.ROUNDED_BOX, Font.PLAIN),
                                      HighlighterTargetArea.EXACT_RANGE);
                queryHighlights.put(editor, rangeHighlighter);
            } catch (Exception e) {
                // Ignore exceptions here.
                // Sometimes when we are adding range document already changed it's state
                // TODO: find out how exactly we should handle such cases
            }
        }
    }

}
