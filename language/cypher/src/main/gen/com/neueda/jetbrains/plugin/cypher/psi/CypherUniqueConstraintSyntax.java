// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherUniqueConstraintSyntax extends PsiElement {

  @NotNull
  CypherNodeLabel getNodeLabel();

  @NotNull
  CypherPropertyExpression getPropertyExpression();

  @NotNull
  CypherVariable getVariable();

  @NotNull
  PsiElement getKAssert();

  @NotNull
  PsiElement getKConstraint();

  @NotNull
  PsiElement getKIs();

  @NotNull
  PsiElement getKOn();

  @NotNull
  PsiElement getKUnique();

}
