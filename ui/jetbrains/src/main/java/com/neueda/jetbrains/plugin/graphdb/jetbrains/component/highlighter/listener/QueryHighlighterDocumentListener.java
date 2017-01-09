package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter.listener;

import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter.SyncedElementHighlighter;

/**
 * Not works as expected. Disabled.
 */
public class QueryHighlighterDocumentListener implements DocumentListener {

    private final SyncedElementHighlighter syncedElementHighlighter;
    private final EditorFactory editorFactory;

    public QueryHighlighterDocumentListener(SyncedElementHighlighter syncedElementHighlighter, EditorFactory editorFactory) {
        this.syncedElementHighlighter = syncedElementHighlighter;
        this.editorFactory = editorFactory;
    }

    @Override
    public void beforeDocumentChange(DocumentEvent event) {
    }

    @Override
    public void documentChanged(DocumentEvent event) {
        Document document = event.getDocument();
        Editor[] editors = editorFactory.getEditors(document);
        for (Editor editor : editors) {
            Project project = editor.getProject();
            if (project == null) {
                return;
            }
            PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
            syncedElementHighlighter.highlightStatement(editor, psiFile);
        }
    }
}
