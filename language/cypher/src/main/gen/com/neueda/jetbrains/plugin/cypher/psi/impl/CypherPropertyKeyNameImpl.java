// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.neueda.jetbrains.plugin.cypher.references.CypherNamedElementImpl;
import com.neueda.jetbrains.plugin.cypher.psi.*;
import com.intellij.psi.PsiReference;

public class CypherPropertyKeyNameImpl extends CypherNamedElementImpl implements CypherPropertyKeyName {

  public CypherPropertyKeyNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitPropertyKeyName(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CypherSymbolicNameString getSymbolicNameString() {
    return findNotNullChildByClass(CypherSymbolicNameString.class);
  }

  public String getName() {
    return CypherPsiImplUtil.getName(this);
  }

  public CypherPropertyKeyName setName(String newName) {
    return CypherPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return CypherPsiImplUtil.getNameIdentifier(this);
  }

  @NotNull
  public PsiReference[] getReferences() {
    return CypherPsiImplUtil.getReferences(this);
  }

}
