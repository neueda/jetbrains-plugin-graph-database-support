// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.neueda.jetbrains.plugin.cypher.psi.CypherTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherDropRelationshipPropertyExistenceConstraintImpl extends ASTWrapperPsiElement implements CypherDropRelationshipPropertyExistenceConstraint {

  public CypherDropRelationshipPropertyExistenceConstraintImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitDropRelationshipPropertyExistenceConstraint(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CypherRelationshipPropertyExistenceConstraintSyntax getRelationshipPropertyExistenceConstraintSyntax() {
    return findNotNullChildByClass(CypherRelationshipPropertyExistenceConstraintSyntax.class);
  }

  @Override
  @NotNull
  public PsiElement getKDrop() {
    return findNotNullChildByType(K_DROP);
  }

}
