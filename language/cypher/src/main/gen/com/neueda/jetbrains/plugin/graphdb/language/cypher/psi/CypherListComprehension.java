// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherListYielding;

public interface CypherListComprehension extends CypherListYielding {

  @Nullable
  CypherExpression getExpression();

  @NotNull
  CypherFilterExpression getFilterExpression();

}
