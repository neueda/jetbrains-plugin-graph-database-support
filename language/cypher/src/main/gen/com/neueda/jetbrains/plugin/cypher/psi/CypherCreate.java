// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherCreate extends PsiElement {

  @NotNull
  CypherPattern getPattern();

  @NotNull
  PsiElement getKCreate();

  @Nullable
  PsiElement getKUnique();

}
