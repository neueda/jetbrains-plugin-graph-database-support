// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;

public interface CypherReduceFunctionInvocation extends CypherInvocation {

  @NotNull
  List<CypherExpression> getExpressionList();

  @NotNull
  CypherIdInColl getIdInColl();

  @NotNull
  CypherVariable getVariable();

  @NotNull
  PsiElement getKReduce();

  String getFullName();

}
