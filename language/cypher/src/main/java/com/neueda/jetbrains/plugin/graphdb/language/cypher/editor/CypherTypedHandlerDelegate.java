package com.neueda.jetbrains.plugin.graphdb.language.cypher.editor;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherStringLiteral;
import org.jetbrains.annotations.NotNull;

public class CypherTypedHandlerDelegate extends TypedHandlerDelegate {

    @NotNull
    @Override
    public Result checkAutoPopup(char charTyped, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        final int offset = editor.getCaretModel().getOffset() - 1;
        if (offset >= 0) {
            final PsiElement element = file.findElementAt(offset);
            if (element instanceof CypherStringLiteral) {
                return Result.STOP;
            }
        }
        return charTyped == ':'
                || charTyped == '.'
                || charTyped == '('
                || charTyped == '['? Result.CONTINUE : Result.STOP;
    }
}
