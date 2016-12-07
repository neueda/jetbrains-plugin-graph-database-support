package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi.*;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database.CypherDocumentation;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

public class CypherBuiltInFunctionElement implements CypherElement, CypherElementWithDocumentation {

    private final String functionName;
    private final String functionSignature;
    private final String functionReturnType;
    private final boolean hasParameters;

    public CypherBuiltInFunctionElement(String functionName, String functionSignature, String functionReturnType) {
        this.functionName = functionName;
        this.functionSignature = functionSignature;
        this.functionReturnType = functionReturnType;
        this.hasParameters = !functionSignature.equals("()");
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                   .create(functionName)
                   .bold()
                   .withIcon(GraphIcons.Nodes.FUNCTION)
                   .withTailText(functionSignature)
                   .withTypeText(functionReturnType)
                   .withInsertHandler(ParenthesesInsertHandler.getInstance(hasParameters));
    }

    @Override
    public String getDocumentation() {
        return CypherDocumentation.BUILT_IN_FUNCTIONS.lookup(functionName).orElse(null);
    }

    public PsiElement getDocumentationPsiElement(Project project) {
        return determineDocumentationPsiElement(project);
    }

    private PsiElement determineDocumentationPsiElement(Project project) {
        switch(functionName.toLowerCase()) {
            case "shortestpath":
                return new CypherShortestPathFunctionInvocationDocumentationPsiElement(project);
            case "allshortestpaths":
                return new CypherAllShortestPathsFunctionInvocationDocumentationPsiElement(project);
            case "filter":
                return new CypherFilterFunctionInvocationDocumentationPsiElement(project);
            case "extract":
                return new CypherExtractFunctionInvocationDocumentationPsiElement(project);
            case "reduce":
                return new CypherReduceFunctionInvocationDocumentationPsiElement(project);
            case "all":
                return new CypherAllFunctionInvocationDocumentationPsiElement(project);
            case "any":
                return new CypherAnyFunctionInvocationDocumentationPsiElement(project);
            case "none":
                return new CypherNoneFunctionInvocationDocumentationPsiElement(project);
            case "single":
                return new CypherSingleFunctionInvocationDocumentationPsiElement(project);
            case "exists":
                return new CypherExistsFunctionInvocationDocumentationPsiElement(project);
            default:
                return new CypherFunctionInvocationDocumentationPsiElement(functionName, project);
        }
    }
}
