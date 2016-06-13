package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import org.jetbrains.annotations.NotNull;

public class ExecuteQueryPayload {

    private final String content;

    public ExecuteQueryPayload(String content) {
        this.content = content;
    }

    @NotNull
    public String getContent() {
        return content;
    }
}
