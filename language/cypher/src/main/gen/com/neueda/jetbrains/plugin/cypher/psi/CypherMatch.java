// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherMatch extends PsiElement {

  @NotNull
  List<CypherHint> getHintList();

  @NotNull
  CypherPattern getPattern();

  @Nullable
  CypherWhere getWhere();

  @NotNull
  PsiElement getKMatch();

  @Nullable
  PsiElement getKOptional();

}
