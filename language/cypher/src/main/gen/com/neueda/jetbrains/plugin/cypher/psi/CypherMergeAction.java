// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherMergeAction extends PsiElement {

  @NotNull
  CypherSetClause getSetClause();

  @Nullable
  PsiElement getKCreate();

  @Nullable
  PsiElement getKMatch();

  @NotNull
  PsiElement getKOn();

}
