package com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.converter;

import com.google.common.base.CaseFormat;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.CypherPreFormatter;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCypherConverter extends PsiRecursiveElementVisitor {
    private CypherPreFormatter.FormatterTask formatterTask;
    private final Document document;
    private int delta = 0;

    public AbstractCypherConverter(CypherPreFormatter.FormatterTask formatterTask, @NotNull Document document) {
        this.formatterTask = formatterTask;
        this.document = document;
    }

    protected abstract String convert(PsiElement element);

    public int getDelta() {
        return delta;
    }

    @Override
    public void visitElement(PsiElement element) {
        super.visitElement(element);

        String newValue = convert(element);
        if (newValue != null) {
            int startOffset = element.getTextRange().getStartOffset() + delta;
            int endOffset = element.getTextRange().getEndOffset() + delta;
            document.replaceString(startOffset, endOffset, newValue);
            delta += newValue.length() - element.getTextLength();
        }
    }

    String convertCase(@NotNull String text, @NotNull CaseFormat targetFormat) {
        if (text.contains("-")) {
            return CaseFormat.LOWER_HYPHEN.to(targetFormat, text.toLowerCase());
        } else if (text.contains("_")) {
            return CaseFormat.LOWER_UNDERSCORE.to(targetFormat, text.toLowerCase());
        } else {
            char first = text.charAt(0);
            if (Character.isUpperCase(first)) {
                return CaseFormat.UPPER_CAMEL.to(targetFormat, text);
            } else {
                return CaseFormat.LOWER_CAMEL.to(targetFormat, text);
            }
        }
    }
}
