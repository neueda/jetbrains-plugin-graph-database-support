package com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation;

import com.google.common.collect.Lists;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database.CypherDocumentation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.TraverseUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CypherDocumentationProvider extends AbstractDocumentationProvider {

    private static final List<IElementType> SEARCH_TYPES = Lists.newArrayList(
            CypherTypes.FUNCTION_INVOCATION,
            CypherTypes.PROCEDURE_INVOCATION,
            CypherTypes.SHORTEST_PATH_FUNCTION_INVOCATION,
            CypherTypes.ALL_SHORTEST_PATHS_FUNCTION_INVOCATION,
            CypherTypes.FILTER_FUNCTION_INVOCATION,
            CypherTypes.EXTRACT_FUNCTION_INVOCATION,
            CypherTypes.REDUCE_FUNCTION_INVOCATION,
            CypherTypes.ALL_FUNCTION_INVOCATION,
            CypherTypes.ANY_FUNCTION_INVOCATION,
            CypherTypes.NONE_FUNCTION_INVOCATION,
            CypherTypes.SINGLE_FUNCTION_INVOCATION,
            CypherTypes.EXISTS_FUNCTION_INVOCATION
    );

    @Nullable
    @Override
    public PsiElement getCustomDocumentationElement(@NotNull Editor editor,
                                                    @NotNull PsiFile file,
                                                    @Nullable PsiElement contextElement) {
        return TraverseUtil.findParent(contextElement, SEARCH_TYPES).orElse(null);
    }

    @Nullable
    @Override
    public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        if (element instanceof CypherFunctionInvocation) {
            CypherFunctionInvocation invocation = (CypherFunctionInvocation) element;
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup(invocation.getFullName())
                    .orElse(null);
        } else if (element instanceof CypherShortestPathFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("shortestpath")
                    .orElse(null);
        } else if (element instanceof CypherAllShortestPathsFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("allshortestpaths")
                    .orElse(null);
        } else if (element instanceof CypherFilterFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("filter")
                    .orElse(null);
        } else if (element instanceof CypherExtractFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("extract")
                    .orElse(null);
        } else if (element instanceof CypherReduceFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("reduce")
                    .orElse(null);
        } else if (element instanceof CypherAllFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("all")
                    .orElse(null);
        } else if (element instanceof CypherAnyFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("any")
                    .orElse(null);
        } else if (element instanceof CypherNoneFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("none")
                    .orElse(null);
        } else if (element instanceof CypherSingleFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("single")
                    .orElse(null);
        } else if (element instanceof CypherExistsFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("exists")
                    .orElse(null);
        }

        return null;
    }
}
