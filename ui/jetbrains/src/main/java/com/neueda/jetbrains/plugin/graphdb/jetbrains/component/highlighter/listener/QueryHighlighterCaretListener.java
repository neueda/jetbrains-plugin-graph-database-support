package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter.listener;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;

public class QueryHighlighterCaretListener extends AbstractQueryHighlighterListener implements CaretListener {

    public QueryHighlighterCaretListener() {
    }

    @Override
    public void caretPositionChanged(CaretEvent e) {
        Editor editor = e.getEditor();
        Project project = editor.getProject();
        if (project == null) {
            return;
        }

        Document document = editor.getDocument();
        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);

        highlightStatement(editor, psiFile);
    }


    @Override
    public void caretAdded(CaretEvent e) {
    }

    @Override
    public void caretRemoved(CaretEvent e) {
    }
}
