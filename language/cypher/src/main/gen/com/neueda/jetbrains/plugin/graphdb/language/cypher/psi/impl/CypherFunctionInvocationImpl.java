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

public class CypherFunctionInvocationImpl extends ASTWrapperPsiElement implements CypherFunctionInvocation {

  public CypherFunctionInvocationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitFunctionInvocation(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherFunctionArguments getFunctionArguments() {
    return findChildByClass(CypherFunctionArguments.class);
  }

  @Override
  @NotNull
  public CypherFunctionInvocationBody getFunctionInvocationBody() {
    return findNotNullChildByClass(CypherFunctionInvocationBody.class);
  }

  @Override
  public String getFullName() {
    return CypherPsiImplUtil.getFullName(this);
  }

}
