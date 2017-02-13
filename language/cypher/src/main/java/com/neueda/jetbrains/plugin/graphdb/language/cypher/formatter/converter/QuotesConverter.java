package com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.converter;

import com.google.common.base.CharMatcher;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.CypherPreFormatter;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;

public class QuotesConverter extends AbstractCypherConverter {

    public QuotesConverter(CypherPreFormatter.FormatterTask formatterTask, @NotNull Document document) {
        super(formatterTask, document);
    }

    @Override
    protected String convert(PsiElement element) {
        if (element.getNode().getElementType() == CypherTypes.STRING_LITERAL) {
            String text = element.getText().substring(1, element.getTextLength() - 1);
            int singleCount = CharMatcher.is('\'').countIn(text);
            int doubleCount = CharMatcher.is('"').countIn(text);

            text = text.replace("\\", "");

            if (singleCount <= doubleCount) {
                text = text
                        .replace("'", "\\'");
                return "'" + text + "'";
            } else {
                text = text
                        .replace("\"", "\\\"")
                        .replace("\\'", "'");
                return "\"" + text + "\"";
            }
        }

        return null;
    }

}
