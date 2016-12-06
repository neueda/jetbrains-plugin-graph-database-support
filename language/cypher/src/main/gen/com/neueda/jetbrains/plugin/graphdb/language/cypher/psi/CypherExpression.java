// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherExpression extends PsiElement {

  @NotNull
  List<CypherAllFunctionInvocation> getAllFunctionInvocationList();

  @NotNull
  List<CypherAnyFunctionInvocation> getAnyFunctionInvocationList();

  @NotNull
  List<CypherCaseExpression> getCaseExpressionList();

  @NotNull
  List<CypherExistsFunctionInvocation> getExistsFunctionInvocationList();

  @NotNull
  List<CypherExpression> getExpressionList();

  @NotNull
  List<CypherExtractFunctionInvocation> getExtractFunctionInvocationList();

  @NotNull
  List<CypherFilterFunctionInvocation> getFilterFunctionInvocationList();

  @NotNull
  List<CypherFunctionInvocation> getFunctionInvocationList();

  @NotNull
  List<CypherListComprehension> getListComprehensionList();

  @NotNull
  List<CypherMapLiteral> getMapLiteralList();

  @NotNull
  List<CypherMapProjection> getMapProjectionList();

  @NotNull
  List<CypherNodeLabels> getNodeLabelsList();

  @NotNull
  List<CypherNoneFunctionInvocation> getNoneFunctionInvocationList();

  @NotNull
  List<CypherNumberLiteral> getNumberLiteralList();

  @NotNull
  List<CypherParameter> getParameterList();

  @NotNull
  List<CypherPatternComprehension> getPatternComprehensionList();

  @NotNull
  List<CypherPropertyLookup> getPropertyLookupList();

  @NotNull
  List<CypherReduceFunctionInvocation> getReduceFunctionInvocationList();

  @NotNull
  List<CypherRelationshipsPattern> getRelationshipsPatternList();

  @NotNull
  List<CypherShortestPathPattern> getShortestPathPatternList();

  @NotNull
  List<CypherSingleFunctionInvocation> getSingleFunctionInvocationList();

  @NotNull
  List<CypherStringLiteral> getStringLiteralList();

  @NotNull
  List<CypherVariable> getVariableList();

  @NotNull
  List<CypherParenthesizedExpression> getParenthesizedExpressionList();

}
