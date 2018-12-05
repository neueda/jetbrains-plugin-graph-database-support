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

public class CypherHintImpl extends ASTWrapperPsiElement implements CypherHint {

  public CypherHintImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitHint(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherNodeLabel getNodeLabel() {
    return findChildByClass(CypherNodeLabel.class);
  }

  @Override
  @Nullable
  public CypherPropertyKeyName getPropertyKeyName() {
    return findChildByClass(CypherPropertyKeyName.class);
  }

  @Override
  @NotNull
  public List<CypherVariable> getVariableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherVariable.class);
  }

  @Override
  @Nullable
  public PsiElement getKIndex() {
    return findChildByType(K_INDEX);
  }

  @Override
  @Nullable
  public PsiElement getKJoin() {
    return findChildByType(K_JOIN);
  }

  @Override
  @Nullable
  public PsiElement getKOn() {
    return findChildByType(K_ON);
  }

  @Override
  @Nullable
  public PsiElement getKScan() {
    return findChildByType(K_SCAN);
  }

  @Override
  @NotNull
  public PsiElement getKUsing() {
    return findNotNullChildByType(K_USING);
  }

}
