// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.neueda.jetbrains.plugin.cypher.psi.CypherTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherPeriodicCommitHintImpl extends ASTWrapperPsiElement implements CypherPeriodicCommitHint {

  public CypherPeriodicCommitHintImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitPeriodicCommitHint(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherSignedIntegerLiteral getSignedIntegerLiteral() {
    return findChildByClass(CypherSignedIntegerLiteral.class);
  }

  @Override
  @NotNull
  public PsiElement getKCommit() {
    return findNotNullChildByType(K_COMMIT);
  }

  @Override
  @NotNull
  public PsiElement getKPeriodic() {
    return findNotNullChildByType(K_PERIODIC);
  }

  @Override
  @NotNull
  public PsiElement getKUsing() {
    return findNotNullChildByType(K_USING);
  }

}
