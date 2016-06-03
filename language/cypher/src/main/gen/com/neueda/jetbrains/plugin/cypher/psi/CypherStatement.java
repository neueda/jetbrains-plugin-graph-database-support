// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherStatement extends PsiElement {

  @Nullable
  CypherCommand getCommand();

  @Nullable
  CypherQuery getQuery();

  @Nullable
  CypherQueryOptions getQueryOptions();

  @Nullable
  PsiElement getKBegin();

  @Nullable
  PsiElement getKCommit();

}
