package com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation;

import com.google.common.collect.Lists;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherProcedureElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherUserFunctionElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database.CypherDocumentation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.TraverseUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

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
    public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
        return super.getQuickNavigateInfo(element, originalElement);
    }

    @Nullable
    @Override
    public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        Optional<String> builtInFunctionDocumentation = builtInFunctionDocumentation(element);
        if (builtInFunctionDocumentation.isPresent()) {
            return builtInFunctionDocumentation.get();
        }

        Optional<String> specialFunctionDocumentation = specialFunctionDocumentation(element);
        if (specialFunctionDocumentation.isPresent()) {
            return specialFunctionDocumentation.get();
        }

        Optional<String> storedProcedureDocumentation = storedProcedureDocumentation(element);
        if (storedProcedureDocumentation.isPresent()) {
            return storedProcedureDocumentation.get();
        }

        Optional<String> userFunctionDocumentation = userFunctionDocumentation(element);
        return userFunctionDocumentation.orElse(null);

    }

    private Optional<String> builtInFunctionDocumentation(PsiElement element) {
        if (element instanceof CypherFunctionInvocation) {
            CypherFunctionInvocation invocation = (CypherFunctionInvocation) element;
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup(invocation.getFullName());
        }
        return Optional.empty();
    }

    private Optional<String> specialFunctionDocumentation(PsiElement element) {
        if (element instanceof CypherShortestPathFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("shortestpath");
        } else if (element instanceof CypherAllShortestPathsFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("allshortestpaths");
        } else if (element instanceof CypherFilterFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("filter");
        } else if (element instanceof CypherExtractFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("extract");
        } else if (element instanceof CypherReduceFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("reduce");
        } else if (element instanceof CypherAllFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("all");
        } else if (element instanceof CypherAnyFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("any");
        } else if (element instanceof CypherNoneFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("none");
        } else if (element instanceof CypherSingleFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("single");
        } else if (element instanceof CypherExistsFunctionInvocation) {
            return CypherDocumentation.BUILT_IN_FUNCTIONS
                    .lookup("exists");
        }
        return Optional.empty();
    }

    private Optional<String> storedProcedureDocumentation(PsiElement element) {
        if (element instanceof CypherProcedureInvocation) {
            CypherProcedureInvocation cypherProcedureInvocation = (CypherProcedureInvocation) element;
            return getMetadataService(element)
                    .findProcedure(cypherProcedureInvocation.getFullName())
                    .map(CypherProcedureElement::getDocumentation);
        }
        return Optional.empty();
    }

    private Optional<String> userFunctionDocumentation(PsiElement element) {
        if (element instanceof CypherFunctionInvocation) {
            CypherFunctionInvocation cypherFunctionInvocation = (CypherFunctionInvocation) element;
            return getMetadataService(element)
                    .findUserFunction(cypherFunctionInvocation.getFullName())
                    .map(CypherUserFunctionElement::getDocumentation);
        }
        return Optional.empty();
    }

    private CypherMetadataProviderService getMetadataService(PsiElement element) {
        return ServiceManager.getService(element.getProject(), CypherMetadataProviderService.class);
    }
}
