// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.neueda.jetbrains.plugin.cypher.psi.CypherTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherShortestPathPatternImpl extends ASTWrapperPsiElement implements CypherShortestPathPattern {

  public CypherShortestPathPatternImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitShortestPathPattern(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CypherPatternElement getPatternElement() {
    return findNotNullChildByClass(CypherPatternElement.class);
  }

  @Override
  @Nullable
  public PsiElement getKAllshortestpaths() {
    return findChildByType(K_ALLSHORTESTPATHS);
  }

  @Override
  @Nullable
  public PsiElement getKShortestpath() {
    return findChildByType(K_SHORTESTPATH);
  }

}
