// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherReadingClause extends PsiElement {

  @Nullable
  CypherCall getCall();

  @Nullable
  CypherLoadCSV getLoadCSV();

  @Nullable
  CypherMatch getMatch();

  @Nullable
  CypherStart getStart();

  @Nullable
  CypherUnwind getUnwind();

}
