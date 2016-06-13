package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.EditorFactory;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.highlighter.listener.QueryHighlighterCaretListener;
import org.jetbrains.annotations.NotNull;

public class QueryHighlighterComponentImpl implements QueryHighlighterComponent {

    private QueryHighlighterCaretListener queryHighlighterCaretListener;
    //private QueryHighlighterDocumentListener queryHighlighterDocumentListener;

    @Override
    public void initComponent() {
        EditorFactory editorFactory = ApplicationManager.getApplication().getComponent(EditorFactory.class);

        queryHighlighterCaretListener = new QueryHighlighterCaretListener();
        //queryHighlighterDocumentListener = new QueryHighlighterDocumentListener(editorFactory);

        editorFactory.getEventMulticaster().addCaretListener(queryHighlighterCaretListener);
        //editorFactory.getEventMulticaster().addDocumentListener(queryHighlighterDocumentListener);
    }

    @Override
    public void disposeComponent() {
        EditorFactory editorFactory = ApplicationManager.getApplication().getComponent(EditorFactory.class);
        if (queryHighlighterCaretListener != null) {
            editorFactory.getEventMulticaster().removeCaretListener(queryHighlighterCaretListener);
        }
        //if (queryHighlighterDocumentListener != null) {
        //    editorFactory.getEventMulticaster().removeDocumentListener(queryHighlighterDocumentListener);
        //}
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "GraphDatabaseSupport.QueryHighlighter";
    }
}
