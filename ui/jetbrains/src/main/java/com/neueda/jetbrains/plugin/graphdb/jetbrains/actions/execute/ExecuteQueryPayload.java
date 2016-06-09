package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.editor.Caret;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExecuteQueryPayload {

    private final String content;
    private final int caretOffset;
    private final boolean caretHasSelection;
    private final String caretSelectedText;

    public ExecuteQueryPayload(String content, Caret caret) {
        this.content = content;
        this.caretOffset = caret.getOffset();
        this.caretHasSelection = caret.hasSelection();
        this.caretSelectedText = caret.getSelectedText();
    }

    @NotNull
    public String getContent() {
        return content;
    }

    public int getCaretOffset() {
        return caretOffset;
    }

    public boolean isCaretHasSelection() {
        return caretHasSelection;
    }

    @Nullable
    public String getCaretSelectedText() {
        return caretSelectedText;
    }
}
