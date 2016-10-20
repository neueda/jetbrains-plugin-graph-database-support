// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CypherFilterExpression extends PsiElement {

  @Nullable
  CypherExpression getExpression();

  @NotNull
  CypherIdInColl getIdInColl();

  @Nullable
  PsiElement getKWhere();

}
