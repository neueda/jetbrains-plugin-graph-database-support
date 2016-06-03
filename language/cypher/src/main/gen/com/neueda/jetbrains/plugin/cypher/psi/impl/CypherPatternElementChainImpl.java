// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherPatternElementChainImpl extends ASTWrapperPsiElement implements CypherPatternElementChain {

  public CypherPatternElementChainImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitPatternElementChain(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherNodePattern getNodePattern() {
    return findChildByClass(CypherNodePattern.class);
  }

  @Override
  @NotNull
  public CypherRelationshipPattern getRelationshipPattern() {
    return findNotNullChildByClass(CypherRelationshipPattern.class);
  }

}
