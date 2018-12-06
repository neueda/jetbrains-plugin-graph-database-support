// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherForeach extends PsiElement {

  @NotNull
  CypherExpression getExpression();

  @NotNull
  List<CypherUpdatingClause> getUpdatingClauseList();

  @NotNull
  CypherVariable getVariable();

  @NotNull
  PsiElement getKForeach();

  @NotNull
  PsiElement getKIn();

}
