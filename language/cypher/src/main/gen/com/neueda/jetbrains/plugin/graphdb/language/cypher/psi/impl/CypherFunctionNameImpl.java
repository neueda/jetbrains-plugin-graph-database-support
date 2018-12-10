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

public class CypherFunctionNameImpl extends ASTWrapperPsiElement implements CypherFunctionName {

  public CypherFunctionNameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitFunctionName(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherEscapedSymbolicNameString getEscapedSymbolicNameString() {
    return findChildByClass(CypherEscapedSymbolicNameString.class);
  }

  @Override
  @Nullable
  public CypherUnescapedSymbolicNameString getUnescapedSymbolicNameString() {
    return findChildByClass(CypherUnescapedSymbolicNameString.class);
  }

  @Override
  @Nullable
  public PsiElement getKCount() {
    return findChildByType(K_COUNT);
  }

}
