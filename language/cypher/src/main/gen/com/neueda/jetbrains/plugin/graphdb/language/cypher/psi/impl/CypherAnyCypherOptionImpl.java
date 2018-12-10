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

public class CypherAnyCypherOptionImpl extends ASTWrapperPsiElement implements CypherAnyCypherOption {

  public CypherAnyCypherOptionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitAnyCypherOption(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherCypherOption getCypherOption() {
    return findChildByClass(CypherCypherOption.class);
  }

  @Override
  @Nullable
  public CypherExplain getExplain() {
    return findChildByClass(CypherExplain.class);
  }

  @Override
  @Nullable
  public CypherProfile getProfile() {
    return findChildByClass(CypherProfile.class);
  }

}
