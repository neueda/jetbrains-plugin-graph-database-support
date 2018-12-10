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

public class CypherPropertyExpressionImpl extends ASTWrapperPsiElement implements CypherPropertyExpression {

  public CypherPropertyExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitPropertyExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherAllFunctionInvocation getAllFunctionInvocation() {
    return findChildByClass(CypherAllFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherAnyFunctionInvocation getAnyFunctionInvocation() {
    return findChildByClass(CypherAnyFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherArray getArray() {
    return findChildByClass(CypherArray.class);
  }

  @Override
  @Nullable
  public CypherBooleanLiteral getBooleanLiteral() {
    return findChildByClass(CypherBooleanLiteral.class);
  }

  @Override
  @Nullable
  public CypherCaseExpression getCaseExpression() {
    return findChildByClass(CypherCaseExpression.class);
  }

  @Override
  @Nullable
  public CypherCountStar getCountStar() {
    return findChildByClass(CypherCountStar.class);
  }

  @Override
  @Nullable
  public CypherExistsFunctionInvocation getExistsFunctionInvocation() {
    return findChildByClass(CypherExistsFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherExtractFunctionInvocation getExtractFunctionInvocation() {
    return findChildByClass(CypherExtractFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherFilterFunctionInvocation getFilterFunctionInvocation() {
    return findChildByClass(CypherFilterFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherFunctionInvocation getFunctionInvocation() {
    return findChildByClass(CypherFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherListComprehension getListComprehension() {
    return findChildByClass(CypherListComprehension.class);
  }

  @Override
  @Nullable
  public CypherMapLiteral getMapLiteral() {
    return findChildByClass(CypherMapLiteral.class);
  }

  @Override
  @Nullable
  public CypherMapProjection getMapProjection() {
    return findChildByClass(CypherMapProjection.class);
  }

  @Override
  @Nullable
  public CypherNoneFunctionInvocation getNoneFunctionInvocation() {
    return findChildByClass(CypherNoneFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherNullLiteral getNullLiteral() {
    return findChildByClass(CypherNullLiteral.class);
  }

  @Override
  @Nullable
  public CypherNumberLiteral getNumberLiteral() {
    return findChildByClass(CypherNumberLiteral.class);
  }

  @Override
  @Nullable
  public CypherParameter getParameter() {
    return findChildByClass(CypherParameter.class);
  }

  @Override
  @Nullable
  public CypherParenthesizedExpression getParenthesizedExpression() {
    return findChildByClass(CypherParenthesizedExpression.class);
  }

  @Override
  @Nullable
  public CypherPatternComprehension getPatternComprehension() {
    return findChildByClass(CypherPatternComprehension.class);
  }

  @Override
  @NotNull
  public List<CypherPropertyLookup> getPropertyLookupList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherPropertyLookup.class);
  }

  @Override
  @Nullable
  public CypherReduceFunctionInvocation getReduceFunctionInvocation() {
    return findChildByClass(CypherReduceFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherRelationshipsPattern getRelationshipsPattern() {
    return findChildByClass(CypherRelationshipsPattern.class);
  }

  @Override
  @Nullable
  public CypherShortestPathPattern getShortestPathPattern() {
    return findChildByClass(CypherShortestPathPattern.class);
  }

  @Override
  @Nullable
  public CypherSingleFunctionInvocation getSingleFunctionInvocation() {
    return findChildByClass(CypherSingleFunctionInvocation.class);
  }

  @Override
  @Nullable
  public CypherStringLiteral getStringLiteral() {
    return findChildByClass(CypherStringLiteral.class);
  }

  @Override
  @Nullable
  public CypherUnaryOperator getUnaryOperator() {
    return findChildByClass(CypherUnaryOperator.class);
  }

  @Override
  @Nullable
  public CypherVariable getVariable() {
    return findChildByClass(CypherVariable.class);
  }

}
