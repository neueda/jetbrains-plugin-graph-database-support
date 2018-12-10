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

public class CypherRelationshipPatternImpl extends ASTWrapperPsiElement implements CypherRelationshipPattern {

  public CypherRelationshipPatternImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitRelationshipPattern(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CypherDash> getDashList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherDash.class);
  }

  @Override
  @Nullable
  public CypherLeftArrowHead getLeftArrowHead() {
    return findChildByClass(CypherLeftArrowHead.class);
  }

  @Override
  @Nullable
  public CypherRelationshipDetail getRelationshipDetail() {
    return findChildByClass(CypherRelationshipDetail.class);
  }

  @Override
  @Nullable
  public CypherRightArrowHead getRightArrowHead() {
    return findChildByClass(CypherRightArrowHead.class);
  }

}
