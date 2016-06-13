// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherRelationshipLookup extends PsiElement {

  @Nullable
  CypherIdLookup getIdLookup();

  @Nullable
  CypherIdentifiedIndexLookup getIdentifiedIndexLookup();

  @Nullable
  CypherIndexQuery getIndexQuery();

  @Nullable
  PsiElement getKRel();

  @Nullable
  PsiElement getKRelationship();

}
