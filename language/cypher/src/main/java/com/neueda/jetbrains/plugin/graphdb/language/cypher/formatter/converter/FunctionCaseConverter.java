package com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.converter;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.CypherPreFormatter;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;

public class FunctionCaseConverter extends AbstractCypherConverter {

    public FunctionCaseConverter(CypherPreFormatter.FormatterTask formatterTask, @NotNull Document document) {
        super(formatterTask, document);
    }

    @Override
    protected String convert(PsiElement element) {
        if (isLookupFunction(element.getNode()) || isNonStarCount(element.getNode())) {
            return element.getText().toLowerCase();
        }

        if (element.getNode().getElementType() == CypherTypes.FUNCTION_NAME &&
                TreeUtil.findChildBackward(element.getNode(), CypherTypes.K_COUNT) == null) {
            String text = element.getText();
            char first = text.charAt(0);
            return Character.toLowerCase(first) + text.substring(1);
        }

        return null;
    }

    private boolean isLookupFunction(@NotNull ASTNode node) {
        IElementType type = node.getElementType();
        return (type == CypherTypes.K_NODE || type == CypherTypes.K_REL || type == CypherTypes.K_RELATIONSHIP)
                && TreeUtil.findParent(node, CypherTypes.LOOKUP) != null;

    }

    private boolean isNonStarCount(@NotNull ASTNode node) {
        IElementType type = node.getElementType();
        return type == CypherTypes.K_COUNT && TreeUtil.findParent(node, CypherTypes.COUNT_STAR) == null;

    }
}
