// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherExpressionImpl extends ASTWrapperPsiElement implements CypherExpression {

  public CypherExpressionImpl(ASTNode node) {
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
  public List<CypherCaseExpression> getCaseExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherCaseExpression.class);
  }

  @Override
  @NotNull
  public List<CypherExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherExpression.class);
  }

  @Override
  @NotNull
  public List<CypherFilterExpression> getFilterExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherFilterExpression.class);
  }

  @Override
  @NotNull
  public List<CypherFunctionInvocation> getFunctionInvocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherFunctionInvocation.class);
  }

  @Override
  @NotNull
  public List<CypherIdInColl> getIdInCollList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherIdInColl.class);
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
  public List<CypherNodeLabels> getNodeLabelsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherNodeLabels.class);
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
  public List<CypherPropertyLookup> getPropertyLookupList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherPropertyLookup.class);
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
  public List<CypherStringLiteral> getStringLiteralList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherStringLiteral.class);
  }

  @Override
  @NotNull
  public List<CypherVariable> getVariableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherVariable.class);
  }

  @Override
  @NotNull
  public List<CypherParenthesizedExpression> getParenthesizedExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherParenthesizedExpression.class);
  }

}
