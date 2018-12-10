// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;

public class CypherExpressionImpl extends ASTWrapperPsiElement implements CypherExpression {

  public CypherExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CypherAllFunctionInvocation> getAllFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherAllFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherAnyFunctionInvocation> getAnyFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherAnyFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherArray> getArrayList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherArray.class);
  }

  @Override
  @NotNull
  public List<CypherBooleanLiteral> getBooleanLiteralList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherBooleanLiteral.class);
  }

  @Override
  @NotNull
  public List<CypherCaseExpression> getCaseExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherCaseExpression.class);
  }

  @Override
  @NotNull
  public List<CypherCountStar> getCountStarList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherCountStar.class);
  }

  @Override
  @NotNull
  public List<CypherExistsFunctionInvocation> getExistsFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherExistsFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherExpression.class);
  }

  @Override
  @NotNull
  public List<CypherExtractFunctionInvocation> getExtractFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherExtractFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherFilterFunctionInvocation> getFilterFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherFilterFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherFunctionInvocation> getFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherListComprehension> getListComprehensionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherListComprehension.class);
  }

  @Override
  @NotNull
  public List<CypherMapLiteral> getMapLiteralList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherMapLiteral.class);
  }

  @Override
  @NotNull
  public List<CypherMapProjection> getMapProjectionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherMapProjection.class);
  }

  @Override
  @NotNull
  public List<CypherNodeLabels> getNodeLabelsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherNodeLabels.class);
  }

  @Override
  @NotNull
  public List<CypherNoneFunctionInvocation> getNoneFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherNoneFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherNullLiteral> getNullLiteralList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherNullLiteral.class);
  }

  @Override
  @NotNull
  public List<CypherNumberLiteral> getNumberLiteralList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherNumberLiteral.class);
  }

  @Override
  @NotNull
  public List<CypherParameter> getParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherParameter.class);
  }

  @Override
  @NotNull
  public List<CypherParenthesizedExpression> getParenthesizedExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherParenthesizedExpression.class);
  }

  @Override
  @NotNull
  public List<CypherPatternComprehension> getPatternComprehensionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherPatternComprehension.class);
  }

  @Override
  @NotNull
  public List<CypherPropertyLookup> getPropertyLookupList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherPropertyLookup.class);
  }

  @Override
  @NotNull
  public List<CypherReduceFunctionInvocation> getReduceFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherReduceFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherRelationshipsPattern> getRelationshipsPatternList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherRelationshipsPattern.class);
  }

  @Override
  @NotNull
  public List<CypherShortestPathPattern> getShortestPathPatternList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherShortestPathPattern.class);
  }

  @Override
  @NotNull
  public List<CypherSingleFunctionInvocation> getSingleFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherSingleFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherStringLiteral> getStringLiteralList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherStringLiteral.class);
  }

  @Override
  @NotNull
  public List<CypherUnaryOperator> getUnaryOperatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherUnaryOperator.class);
  }

  @Override
  @NotNull
  public List<CypherVariable> getVariableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherVariable.class);
  }

}
