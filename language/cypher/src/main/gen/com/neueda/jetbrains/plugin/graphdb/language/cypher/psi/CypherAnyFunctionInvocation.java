// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;

public interface CypherAnyFunctionInvocation extends CypherInvocation {

  @NotNull
  CypherFilterExpression getFilterExpression();

  @NotNull
  PsiElement getKAny();

  String getFullName();

}
