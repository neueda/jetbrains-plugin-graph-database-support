package com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.converter;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.containers.ContainerUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter.CypherPreFormatter;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTokenType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class KeywordCaseConverter extends AbstractCypherConverter {
    private static final Set<IElementType> TO_LOWER_CASE_SPECIAL = ContainerUtil.newHashSet(
            CypherTypes.K_TRUE,
            CypherTypes.K_FALSE,
            CypherTypes.K_EXISTS,
            CypherTypes.K_FILTER,
            CypherTypes.K_EXTRACT,
            CypherTypes.K_REDUCE,
            CypherTypes.K_ANY,
            CypherTypes.K_NONE,
            CypherTypes.K_SINGLE
    );

    private static final Map<IElementType, String> SPECIAL_FUNCTIONS = ContainerUtil.newHashMap(
            Pair.create(CypherTypes.K_SHORTESTPATH, "shortestPath"),
            Pair.create(CypherTypes.K_ALLSHORTESTPATHS, "allShortestPaths")
    );

    public KeywordCaseConverter(CypherPreFormatter.FormatterTask formatterTask, @NotNull Document document) {
        super(formatterTask, document);
    }

    @Override
    protected String convert(PsiElement element) {
        if (CypherTokenType.class.isAssignableFrom(element.getNode().getElementType().getClass())) {
            CypherTokenType type = (CypherTokenType) element.getNode().getElementType();
            if (type.getOriginalName().startsWith("K_")) {
                if (TreeUtil.findParent(element.getNode(), TokenSet.create(
                        CypherTypes.VARIABLE,
                        CypherTypes.LABEL_NAME,
                        CypherTypes.REL_TYPE_NAME,
                        CypherTypes.PROPERTY_KEY_NAME,
                        CypherTypes.NAMESPACE,
                        CypherTypes.PARAMETER,
                        CypherTypes.PROCEDURE_NAME)) != null) {
                    return null;
                }

                if (SPECIAL_FUNCTIONS.containsKey(type)) {
                    return SPECIAL_FUNCTIONS.get(type);
                }

                if (TO_LOWER_CASE_SPECIAL.contains(type)) {
                    return element.getText().toLowerCase();
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
