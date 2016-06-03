// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherPeriodicCommitHint extends PsiElement {

  @Nullable
  CypherSignedIntegerLiteral getSignedIntegerLiteral();

  @NotNull
  PsiElement getKCommit();

  @NotNull
  PsiElement getKPeriodic();

  @NotNull
  PsiElement getKUsing();

}
