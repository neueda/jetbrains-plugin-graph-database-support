// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherFilterExpression extends PsiElement {

  @Nullable
  CypherExpression getExpression();

  @NotNull
  CypherIdInColl getIdInColl();

  @Nullable
  PsiElement getKWhere();

}
