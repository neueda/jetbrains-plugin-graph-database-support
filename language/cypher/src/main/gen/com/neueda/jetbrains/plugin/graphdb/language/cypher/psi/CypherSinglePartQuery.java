// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherSinglePartQuery extends PsiElement {

  @NotNull
  List<CypherReadingClause> getReadingClauseList();

  @Nullable
  CypherReadingWithReturn getReadingWithReturn();

  @Nullable
  CypherReturn getReturn();

  @NotNull
  List<CypherUpdatingClause> getUpdatingClauseList();

}
