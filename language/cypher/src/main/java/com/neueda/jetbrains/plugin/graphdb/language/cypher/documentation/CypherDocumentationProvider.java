package com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation;

import com.google.common.collect.Lists;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database.CypherDocumentation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherProcedureInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.TraverseUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CypherDocumentationProvider extends AbstractDocumentationProvider {

    private static final List<IElementType> SEARCH_TYPES = Lists.newArrayList(
            CypherTypes.FUNCTION_INVOCATION,
            CypherTypes.PROCEDURE_INVOCATION
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
        } else if (element instanceof CypherProcedureInvocation) {
            CypherProcedureInvocation invocation = (CypherProcedureInvocation) element;
            return CypherDocumentation.BUILT_IN_PROCEDURES
                    .lookup(invocation.getFullName())
                    .orElse(null);
        }

        return null;
    }
}
