// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherPropertyExpression extends PsiElement {

  @Nullable
  CypherAllFunctionInvocation getAllFunctionInvocation();

  @Nullable
  CypherAnyFunctionInvocation getAnyFunctionInvocation();

  @Nullable
  CypherCaseExpression getCaseExpression();

  @Nullable
  CypherExistsFunctionInvocation getExistsFunctionInvocation();

  @NotNull
  List<CypherExpression> getExpressionList();

  @Nullable
  CypherExtractFunctionInvocation getExtractFunctionInvocation();

  @Nullable
  CypherFilterFunctionInvocation getFilterFunctionInvocation();

  @Nullable
  CypherFunctionInvocation getFunctionInvocation();

  @Nullable
  CypherListComprehension getListComprehension();

  @Nullable
  CypherMapLiteral getMapLiteral();

  @Nullable
  CypherMapProjection getMapProjection();

  @Nullable
  CypherNoneFunctionInvocation getNoneFunctionInvocation();

  @Nullable
  CypherNumberLiteral getNumberLiteral();

  @Nullable
  CypherParameter getParameter();

  @NotNull
  List<CypherPropertyLookup> getPropertyLookupList();

  @Nullable
  CypherReduceFunctionInvocation getReduceFunctionInvocation();

  @Nullable
  CypherRelationshipsPattern getRelationshipsPattern();

  @Nullable
  CypherShortestPathPattern getShortestPathPattern();

  @Nullable
  CypherSingleFunctionInvocation getSingleFunctionInvocation();

  @Nullable
  CypherStringLiteral getStringLiteral();

  @Nullable
  CypherVariable getVariable();

  @Nullable
  CypherParenthesizedExpression getParenthesizedExpression();

  @Nullable
  PsiElement getKFalse();

  @Nullable
  PsiElement getKNull();

  @Nullable
  PsiElement getKTrue();

}
