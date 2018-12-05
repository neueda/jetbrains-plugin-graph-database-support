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

public class CypherReduceFunctionInvocationImpl extends ASTWrapperPsiElement implements CypherReduceFunctionInvocation {

  public CypherReduceFunctionInvocationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitReduceFunctionInvocation(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CypherExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CypherExpression.class);
  }

  @Override
  @NotNull
  public CypherIdInColl getIdInColl() {
    return findNotNullChildByClass(CypherIdInColl.class);
  }

  @Override
  @NotNull
  public CypherVariable getVariable() {
    return findNotNullChildByClass(CypherVariable.class);
  }

  @Override
  @NotNull
  public PsiElement getKReduce() {
    return findNotNullChildByType(K_REDUCE);
  }

  public String getFullName() {
    return CypherPsiImplUtil.getFullName(this);
  }

}
