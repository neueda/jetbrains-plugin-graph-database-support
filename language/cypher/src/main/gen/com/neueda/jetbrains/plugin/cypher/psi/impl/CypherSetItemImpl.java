// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherSetItemImpl extends ASTWrapperPsiElement implements CypherSetItem {

  public CypherSetItemImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitSetItem(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherExpression getExpression() {
    return findChildByClass(CypherExpression.class);
  }

  @Override
  @Nullable
  public CypherNodeLabels getNodeLabels() {
    return findChildByClass(CypherNodeLabels.class);
  }

  @Override
  @Nullable
  public CypherPropertyExpression getPropertyExpression() {
    return findChildByClass(CypherPropertyExpression.class);
  }

  @Override
  @Nullable
  public CypherVariable getVariable() {
    return findChildByClass(CypherVariable.class);
  }

}
