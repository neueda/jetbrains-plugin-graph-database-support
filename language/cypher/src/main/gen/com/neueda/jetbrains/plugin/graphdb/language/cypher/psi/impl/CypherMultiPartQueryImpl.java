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

public class CypherMultiPartQueryImpl extends ASTWrapperPsiElement implements CypherMultiPartQuery {

  public CypherMultiPartQueryImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitMultiPartQuery(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CypherReadingClause> getReadingClauseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherReadingClause.class);
  }

  @Override
  @NotNull
  public CypherSinglePartQuery getSinglePartQuery() {
    return findNotNullChildByClass(CypherSinglePartQuery.class);
  }

  @Override
  @NotNull
  public List<CypherUpdatingClause> getUpdatingClauseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherUpdatingClause.class);
  }

  @Override
  @NotNull
  public List<CypherWith> getWithList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherWith.class);
  }

}
