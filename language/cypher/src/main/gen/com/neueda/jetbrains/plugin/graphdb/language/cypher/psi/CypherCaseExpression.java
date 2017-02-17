// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherAnyYielding;

public interface CypherCaseExpression extends CypherAnyYielding {

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
