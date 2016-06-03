// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherReturnBodyImpl extends ASTWrapperPsiElement implements CypherReturnBody {

  public CypherReturnBodyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitReturnBody(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherLimit getLimit() {
    return findChildByClass(CypherLimit.class);
  }

  @Override
  @Nullable
  public CypherOrder getOrder() {
    return findChildByClass(CypherOrder.class);
  }

  @Override
  @NotNull
  public CypherReturnItems getReturnItems() {
    return findNotNullChildByClass(CypherReturnItems.class);
  }

  @Override
  @Nullable
  public CypherSkip getSkip() {
    return findChildByClass(CypherSkip.class);
  }

}
