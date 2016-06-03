// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherParameterImpl extends ASTWrapperPsiElement implements CypherParameter {

  public CypherParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitParameter(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherSymbolicNameString getSymbolicNameString() {
    return findChildByClass(CypherSymbolicNameString.class);
  }

  @Override
  @Nullable
  public CypherUnsignedDecimalInteger getUnsignedDecimalInteger() {
    return findChildByClass(CypherUnsignedDecimalInteger.class);
  }

}
