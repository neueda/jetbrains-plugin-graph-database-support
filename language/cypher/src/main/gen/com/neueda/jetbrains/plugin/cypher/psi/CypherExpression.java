// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherExpression extends PsiElement {

  @NotNull
  List<CypherCaseExpression> getCaseExpressionList();

  @NotNull
  List<CypherExpression> getExpressionList();

  @NotNull
  List<CypherFilterExpression> getFilterExpressionList();

  @NotNull
  List<CypherFunctionInvocation> getFunctionInvocationList();

  @NotNull
  List<CypherIdInColl> getIdInCollList();

  @NotNull
  List<CypherListComprehension> getListComprehensionList();

  @NotNull
  List<CypherMapLiteral> getMapLiteralList();

  @NotNull
  List<CypherNodeLabels> getNodeLabelsList();

  @NotNull
  List<CypherNumberLiteral> getNumberLiteralList();

  @NotNull
  List<CypherParameter> getParameterList();

  @NotNull
  List<CypherPropertyLookup> getPropertyLookupList();

  @NotNull
  List<CypherRelationshipsPattern> getRelationshipsPatternList();

  @NotNull
  List<CypherShortestPathPattern> getShortestPathPatternList();

  @NotNull
  List<CypherStringLiteral> getStringLiteralList();

  @NotNull
  List<CypherVariable> getVariableList();

  @NotNull
  List<CypherParenthesizedExpression> getParenthesizedExpressionList();

}
