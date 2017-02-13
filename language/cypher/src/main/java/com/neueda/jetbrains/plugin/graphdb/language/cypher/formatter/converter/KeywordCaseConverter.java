package com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.converter;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.CypherPreFormatter;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTokenType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;

public class KeywordCaseConverter extends AbstractCypherConverter {
    private static final IElementType[] TO_LOWER_CASE_SPECIAL = {
            CypherTypes.K_TRUE,
            CypherTypes.K_FALSE

    };

    public KeywordCaseConverter(CypherPreFormatter.FormatterTask formatterTask, @NotNull Document document) {
        super(formatterTask, document);
    }

    @Override
    protected String convert(PsiElement element) {
        if (CypherTokenType.class.isAssignableFrom(element.getNode().getElementType().getClass())) {
            CypherTokenType type = (CypherTokenType) element.getNode().getElementType();
            if (type.getOriginalName().startsWith("K_")) {
                for (IElementType t: TO_LOWER_CASE_SPECIAL) {
                    if (t == type) {
                        return element.getText().toLowerCase();
                    }
                }

                if (type == CypherTypes.K_NULL) {
                    // if no K_IS sibling to K_NULL -> to lower case
                    ASTNode keywordIS = TreeUtil.findSiblingBackward(element.getNode(), CypherTypes.K_IS);
                    if (keywordIS == null) {
                        return element.getText().toLowerCase();
                    }
                }

                return element.getText().toUpperCase();
            }
        }

        return null;
    }
}
