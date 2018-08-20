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

public class CypherNodeKeySyntaxImpl extends ASTWrapperPsiElement implements CypherNodeKeySyntax {

  public CypherNodeKeySyntaxImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitNodeKeySyntax(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CypherNodeLabel getNodeLabel() {
    return findNotNullChildByClass(CypherNodeLabel.class);
  }

  @Override
  @NotNull
  public List<CypherPropertyExpression> getPropertyExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherPropertyExpression.class);
  }

  @Override
  @NotNull
  public CypherVariable getVariable() {
    return findNotNullChildByClass(CypherVariable.class);
  }

  @Override
  @NotNull
  public PsiElement getKAssert() {
    return findNotNullChildByType(K_ASSERT);
  }

  @Override
  @NotNull
  public PsiElement getKConstraint() {
    return findNotNullChildByType(K_CONSTRAINT);
  }

  @Override
  @NotNull
  public PsiElement getKIs() {
    return findNotNullChildByType(K_IS);
  }

  @Override
  @NotNull
  public PsiElement getKKey() {
    return findNotNullChildByType(K_KEY);
  }

  @Override
  @NotNull
  public PsiElement getKNode() {
    return findNotNullChildByType(K_NODE);
  }

  @Override
  @NotNull
  public PsiElement getKOn() {
    return findNotNullChildByType(K_ON);
  }

}
