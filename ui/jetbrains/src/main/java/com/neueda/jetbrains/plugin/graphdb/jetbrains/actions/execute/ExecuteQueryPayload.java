package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class ExecuteQueryPayload {

    private final String content;

    private final Map<String, Object> parameters;

    private final Editor editor;

    public ExecuteQueryPayload(String content) {
        this.content = content;
        this.editor = null;
        this.parameters = Collections.emptyMap();
    }

    public ExecuteQueryPayload(String content, Map<String, Object> parameters, Editor editor) {
        this.content = content;
        this.parameters = parameters;
        this.editor = editor;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @NotNull
    public Optional<Editor> getEditor() {
        return Optional.ofNullable(editor);
    }

}
