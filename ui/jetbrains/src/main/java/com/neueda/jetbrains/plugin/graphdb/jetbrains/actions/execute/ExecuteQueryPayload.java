package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ExecuteQueryPayload {

    private final String content;
    private final Editor editor;

    public ExecuteQueryPayload(String content) {
        this.content = content;
        this.editor = null;
    }

    public ExecuteQueryPayload(String content, Editor editor) {
        this.content = content;
        this.editor = editor;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    @NotNull
    public Optional<Editor> getEditor() {
        return Optional.ofNullable(editor);
    }

}
