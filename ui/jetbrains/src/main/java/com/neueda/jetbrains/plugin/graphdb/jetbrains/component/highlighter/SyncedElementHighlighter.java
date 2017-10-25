package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ui.Colors;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.util.PsiTraversalUtilities.Cypher.getCypherStatementAtOffset;
import static com.neueda.jetbrains.plugin.graphdb.platform.SupportedLanguage.isSupported;
import static java.util.Objects.nonNull;

public class SyncedElementHighlighter {

    private final Map<Editor, RangeHighlighter> queryHighlights = new HashMap<>();

    public SyncedElementHighlighter() {
    }

    public void dispose() {
        queryHighlights.clear();
    }

    public void highlightStatement(Editor editor, PsiFile psiFile) {
        if (nonNull(psiFile)) {
            String languageId = psiFile.getLanguage().getID();
            if (isSupported(languageId)) {
                Caret caret = editor.getCaretModel().getPrimaryCaret();
                PsiElement element = getCypherStatementAtOffset(psiFile, caret.getOffset());
                highlightStatementInCypherFile(editor, element);
            }
        }
    }

    private void highlightStatementInCypherFile(Editor editor, PsiElement element) {
        synchronized (this) {
            removeHighlighter(editor);
            if (nonNull(element)) {
                addHighlighter(editor, element);
            }
        }
    }

    private void removeHighlighter(Editor editor) {
        if (queryHighlights.containsKey(editor)) {
            editor.getMarkupModel().removeHighlighter(queryHighlights.get(editor));
            queryHighlights.remove(editor);
        }
    }

    private void addHighlighter(Editor editor, PsiElement element) {
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
