// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherReturnItem extends PsiElement {

  @NotNull
  CypherExpression getExpression();

  @Nullable
  CypherVariable getVariable();

  @Nullable
  PsiElement getKAs();

}
