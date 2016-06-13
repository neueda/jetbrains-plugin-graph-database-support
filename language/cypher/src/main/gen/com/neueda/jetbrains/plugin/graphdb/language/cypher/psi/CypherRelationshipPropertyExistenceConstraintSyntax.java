// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherRelationshipPropertyExistenceConstraintSyntax extends PsiElement {

  @NotNull
  CypherPropertyExpression getPropertyExpression();

  @NotNull
  CypherRelationshipPatternSyntax getRelationshipPatternSyntax();

  @NotNull
  PsiElement getKAssert();

  @NotNull
  PsiElement getKConstraint();

  @NotNull
  PsiElement getKExists();

  @NotNull
  PsiElement getKOn();

}
