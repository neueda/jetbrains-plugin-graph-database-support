// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherLoadCSV extends PsiElement {

  @NotNull
  CypherExpression getExpression();

  @Nullable
  CypherStringLiteral getStringLiteral();

  @NotNull
  CypherVariable getVariable();

  @NotNull
  PsiElement getKAs();

  @NotNull
  PsiElement getKCsv();

  @Nullable
  PsiElement getKFieldterminator();

  @NotNull
  PsiElement getKFrom();

  @Nullable
  PsiElement getKHeaders();

  @NotNull
  PsiElement getKLoad();

  @Nullable
  PsiElement getKWith();

}
