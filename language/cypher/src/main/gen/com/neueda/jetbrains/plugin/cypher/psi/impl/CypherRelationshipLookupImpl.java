// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.neueda.jetbrains.plugin.cypher.psi.CypherTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.*;

public class CypherRelationshipLookupImpl extends ASTWrapperPsiElement implements CypherRelationshipLookup {

  public CypherRelationshipLookupImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitRelationshipLookup(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherIdLookup getIdLookup() {
    return findChildByClass(CypherIdLookup.class);
  }

  @Override
  @Nullable
  public CypherIdentifiedIndexLookup getIdentifiedIndexLookup() {
    return findChildByClass(CypherIdentifiedIndexLookup.class);
  }

  @Override
  @Nullable
  public CypherIndexQuery getIndexQuery() {
    return findChildByClass(CypherIndexQuery.class);
  }

  @Override
  @Nullable
  public PsiElement getKRel() {
    return findChildByType(K_REL);
  }

  @Override
  @Nullable
  public PsiElement getKRelationship() {
    return findChildByType(K_RELATIONSHIP);
  }

}
