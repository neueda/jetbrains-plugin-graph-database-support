// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;

public class CypherCaseAlternativesImpl extends ASTWrapperPsiElement implements CypherCaseAlternatives {

  public CypherCaseAlternativesImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitCaseAlternatives(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CypherExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getKThen() {
    return findNotNullChildByType(K_THEN);
  }

  @Override
  @NotNull
  public PsiElement getKWhen() {
    return findNotNullChildByType(K_WHEN);
  }

}
