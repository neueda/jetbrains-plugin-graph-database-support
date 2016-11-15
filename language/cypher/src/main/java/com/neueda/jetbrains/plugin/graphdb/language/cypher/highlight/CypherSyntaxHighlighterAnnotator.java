package com.neueda.jetbrains.plugin.graphdb.language.cypher.highlight;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;
import org.jetbrains.annotations.NotNull;

public class CypherSyntaxHighlighterAnnotator implements Annotator {

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
        } else if (element instanceof CypherShortestPathFunctionInvocation) {
            CypherShortestPathFunctionInvocation invocation = (CypherShortestPathFunctionInvocation) element;
            setHighlighting(invocation.getKShortestpath(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherAllShortestPathsFunctionInvocation) {
            CypherAllShortestPathsFunctionInvocation invocation = (CypherAllShortestPathsFunctionInvocation) element;
            setHighlighting(invocation.getKAllshortestpaths(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherFilterFunctionInvocation) {
            CypherFilterFunctionInvocation invocation = (CypherFilterFunctionInvocation) element;
            setHighlighting(invocation.getKFilter(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherExtractFunctionInvocation) {
            CypherExtractFunctionInvocation invocation = (CypherExtractFunctionInvocation) element;
            setHighlighting(invocation.getKExtract(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherReduceFunctionInvocation) {
            CypherReduceFunctionInvocation invocation = (CypherReduceFunctionInvocation) element;
            setHighlighting(invocation.getKReduce(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherAllFunctionInvocation) {
            CypherAllFunctionInvocation invocation = (CypherAllFunctionInvocation) element;
            setHighlighting(invocation.getKAll(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherAnyFunctionInvocation) {
            CypherAnyFunctionInvocation invocation = (CypherAnyFunctionInvocation) element;
            setHighlighting(invocation.getKAny(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherNoneFunctionInvocation) {
            CypherNoneFunctionInvocation invocation = (CypherNoneFunctionInvocation) element;
            setHighlighting(invocation.getKNone(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherSingleFunctionInvocation) {
            CypherSingleFunctionInvocation invocation = (CypherSingleFunctionInvocation) element;
            setHighlighting(invocation.getKSingle(), holder, CypherSyntaxColors.FUNCTION);
        } else if (element instanceof CypherExistsFunctionInvocation) {
            CypherExistsFunctionInvocation invocation = (CypherExistsFunctionInvocation) element;
            setHighlighting(invocation.getKExists(), holder, CypherSyntaxColors.FUNCTION);
        }
    }

    private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder, @NotNull TextAttributesKey key) {
        holder.createInfoAnnotation(element, null).setEnforcedTextAttributes(TextAttributes.ERASE_MARKER);
        String description = ApplicationManager.getApplication().isUnitTestMode() ? key.getExternalName() : null;
        holder.createInfoAnnotation(element, description).setTextAttributes(key);
    }
}
