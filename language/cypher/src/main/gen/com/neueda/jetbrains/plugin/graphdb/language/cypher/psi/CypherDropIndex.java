// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherDropIndex extends PsiElement {

  @NotNull
  CypherNodeLabel getNodeLabel();

  @NotNull
  CypherPropertyKeyNames getPropertyKeyNames();

  @NotNull
  PsiElement getKDrop();

  @NotNull
  PsiElement getKIndex();

  @NotNull
  PsiElement getKOn();

}
