// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherParameter extends PsiElement {

  @Nullable
  CypherNewParameter getNewParameter();

  @Nullable
  CypherOldParameter getOldParameter();

  String getParameterName();

}
