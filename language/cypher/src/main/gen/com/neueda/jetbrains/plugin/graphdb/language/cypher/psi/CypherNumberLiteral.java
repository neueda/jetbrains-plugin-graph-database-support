// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTypePropagator;

public interface CypherNumberLiteral extends CypherTypePropagator {

  @Nullable
  CypherDoubleLiteral getDoubleLiteral();

  @Nullable
  CypherIntegerLiteral getIntegerLiteral();

}
