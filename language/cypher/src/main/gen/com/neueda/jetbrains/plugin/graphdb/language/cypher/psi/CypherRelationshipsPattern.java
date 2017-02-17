// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherPathYielding;

public interface CypherRelationshipsPattern extends CypherPathYielding {

  @NotNull
  CypherNodePattern getNodePattern();

  @NotNull
  List<CypherPatternElementChain> getPatternElementChainList();

}
