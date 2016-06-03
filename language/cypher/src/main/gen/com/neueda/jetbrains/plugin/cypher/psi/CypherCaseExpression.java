// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherCaseExpression extends PsiElement {

  @NotNull
  List<CypherCaseAlternatives> getCaseAlternativesList();

  @NotNull
  List<CypherExpression> getExpressionList();

  @NotNull
  PsiElement getKCase();

  @Nullable
  PsiElement getKElse();

  @NotNull
  PsiElement getKEnd();

}
