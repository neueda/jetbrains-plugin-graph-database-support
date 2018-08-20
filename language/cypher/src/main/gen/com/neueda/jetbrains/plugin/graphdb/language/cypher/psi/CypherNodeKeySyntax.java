// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherNodeKeySyntax extends PsiElement {

  @NotNull
  CypherNodeLabel getNodeLabel();

  @NotNull
  List<CypherPropertyExpression> getPropertyExpressionList();

  @NotNull
  CypherVariable getVariable();

  @NotNull
  PsiElement getKAssert();

  @NotNull
  PsiElement getKConstraint();

  @NotNull
  PsiElement getKIs();

  @NotNull
  PsiElement getKKey();

  @NotNull
  PsiElement getKNode();

  @NotNull
  PsiElement getKOn();

}
