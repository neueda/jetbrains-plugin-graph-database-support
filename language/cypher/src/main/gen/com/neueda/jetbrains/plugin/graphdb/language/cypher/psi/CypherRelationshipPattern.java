// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherRelationshipPattern extends PsiElement {

  @NotNull
  List<CypherDash> getDashList();

  @Nullable
  CypherLeftArrowHead getLeftArrowHead();

  @Nullable
  CypherRelationshipDetail getRelationshipDetail();

  @Nullable
  CypherRightArrowHead getRightArrowHead();

}
