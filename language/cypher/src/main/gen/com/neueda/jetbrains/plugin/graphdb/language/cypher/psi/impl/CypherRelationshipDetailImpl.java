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

public class CypherRelationshipDetailImpl extends ASTWrapperPsiElement implements CypherRelationshipDetail {

  public CypherRelationshipDetailImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitRelationshipDetail(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CypherMaybeVariableLength getMaybeVariableLength() {
    return findChildByClass(CypherMaybeVariableLength.class);
  }

  @Override
  @Nullable
  public CypherProperties getProperties() {
    return findChildByClass(CypherProperties.class);
  }

  @Override
  @Nullable
  public CypherRelationshipTypes getRelationshipTypes() {
    return findChildByClass(CypherRelationshipTypes.class);
  }

  @Override
  @Nullable
  public CypherVariable getVariable() {
    return findChildByClass(CypherVariable.class);
  }

}
