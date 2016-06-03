// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherProcedureResultImpl extends ASTWrapperPsiElement implements CypherProcedureResult {

  public CypherProcedureResultImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitProcedureResult(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherAliasedProcedureResult getAliasedProcedureResult() {
    return findChildByClass(CypherAliasedProcedureResult.class);
  }

  @Override
  @Nullable
  public CypherSimpleProcedureResult getSimpleProcedureResult() {
    return findChildByClass(CypherSimpleProcedureResult.class);
  }

}
