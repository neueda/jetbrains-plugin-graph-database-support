package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter.listener;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;

/**
 * Not works as expected. Disabled.
 */
public class QueryHighlighterDocumentListener extends AbstractQueryHighlighterListener implements DocumentListener {

    private final EditorFactory editorFactory;

    public QueryHighlighterDocumentListener(EditorFactory editorFactory) {
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
            highlightStatement(editor, psiFile);
        }
    }
}
