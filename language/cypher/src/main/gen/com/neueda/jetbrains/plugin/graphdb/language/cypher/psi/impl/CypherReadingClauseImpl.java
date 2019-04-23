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

public class CypherReadingClauseImpl extends ASTWrapperPsiElement implements CypherReadingClause {

  public CypherReadingClauseImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitReadingClause(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherInQueryCall getInQueryCall() {
    return findChildByClass(CypherInQueryCall.class);
  }

  @Override
  @Nullable
  public CypherLoadCSV getLoadCSV() {
    return findChildByClass(CypherLoadCSV.class);
  }

  @Override
  @Nullable
  public CypherMatch getMatch() {
    return findChildByClass(CypherMatch.class);
  }

  @Override
  @Nullable
  public CypherStart getStart() {
    return findChildByClass(CypherStart.class);
  }

  @Override
  @Nullable
  public CypherUnwind getUnwind() {
    return findChildByClass(CypherUnwind.class);
  }

}
