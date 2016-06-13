// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherNodePropertyExistenceConstraintSyntax extends PsiElement {

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
  PsiElement getKExists();

  @NotNull
  PsiElement getKOn();

}
