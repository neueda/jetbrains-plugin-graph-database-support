package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

public class ExecuteQueryPayload {

    private final String content;

    private final Editor editor;

    public ExecuteQueryPayload(String content, Editor editor) {
        this.content = content;
        this.editor = editor;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    @NotNull
    public Editor getEditor() {
        return editor;
    }

}
