// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherCall extends PsiElement {

  @NotNull
  CypherProcedureArguments getProcedureArguments();

  @NotNull
  CypherProcedureName getProcedureName();

  @NotNull
  CypherProcedureNamespace getProcedureNamespace();

  @Nullable
  CypherProcedureResults getProcedureResults();

  @NotNull
  PsiElement getKCall();

}
