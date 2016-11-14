package com.neueda.jetbrains.plugin.graphdb.language.cypher.highlight;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionInvocationBody;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherLabelName;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherParameter;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherProcedureInvocationBody;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherRelTypeName;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherShortestPathPattern;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherVariable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CypherSyntaxHighlighterAnnotator implements Annotator {

    private static final List<IElementType> SPECIAL_FUNCTIONS = new ArrayList<>();

    static {
        SPECIAL_FUNCTIONS.add(CypherTypes.K_SHORTESTPATH);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_ALLSHORTESTPATHS);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_FILTER);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_EXTRACT);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_REDUCE);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_ALL);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_ANY);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_NONE);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_SINGLE);
        SPECIAL_FUNCTIONS.add(CypherTypes.K_EXISTS);
    }

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof CypherVariable) {
            setHighlighting(element, holder, CypherSyntaxColors.VARIABLE);
        } else if (element instanceof CypherFunctionInvocationBody || element instanceof CypherProcedureInvocationBody) {
            setHighlighting(element, holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherLabelName) {
            setHighlighting(element, holder, CypherSyntaxColors.LABEL);
        } else if (element instanceof CypherRelTypeName) {
            setHighlighting(element, holder, CypherSyntaxColors.RELATIONSHIP_TYPE);
        } else if (element instanceof CypherParameter) {
            setHighlighting(element, holder, CypherSyntaxColors.PARAMETER);
        } else if (element instanceof CypherShortestPathPattern) {
            CypherShortestPathPattern pattern = (CypherShortestPathPattern) element;
            if (pattern.getKShortestpath() != null) {
                setHighlighting(pattern.getKShortestpath(), holder, CypherSyntaxColors.FUNCTION);
            } else if (pattern.getKAllshortestpaths() != null) {
                setHighlighting(pattern.getKAllshortestpaths(), holder, CypherSyntaxColors.FUNCTION);
            }
        } else {
            IElementType elementType = element.getNode().getElementType();
            if (SPECIAL_FUNCTIONS.contains(elementType)) {
                setHighlighting(element, holder, CypherSyntaxColors.FUNCTION);
            }
        }
    }

    private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder, @NotNull TextAttributesKey key) {
        holder.createInfoAnnotation(element, null).setEnforcedTextAttributes(TextAttributes.ERASE_MARKER);
        String description = ApplicationManager.getApplication().isUnitTestMode() ? key.getExternalName() : null;
        holder.createInfoAnnotation(element, description).setTextAttributes(key);
    }
}
