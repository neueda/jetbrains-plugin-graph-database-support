// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes.*;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherNamedElementImpl;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;
import com.intellij.psi.PsiReference;

public class CypherPropertyKeyNameImpl extends CypherNamedElementImpl implements CypherPropertyKeyName {

  public CypherPropertyKeyNameImpl(@NotNull ASTNode node) {
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

  @Override
  public String getName() {
    return CypherPsiImplUtil.getName(this);
  }

  @Override
  public CypherPropertyKeyName setName(String newName) {
    return CypherPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return CypherPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @NotNull
  public PsiReference[] getReferences() {
    return CypherPsiImplUtil.getReferences(this);
  }

}
