// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CypherCommand extends PsiElement {

  @Nullable
  CypherCreateIndex getCreateIndex();

  @Nullable
  CypherCreateNodeKeyConstraint getCreateNodeKeyConstraint();

  @Nullable
  CypherCreateNodePropertyExistenceConstraint getCreateNodePropertyExistenceConstraint();

  @Nullable
  CypherCreateRelationshipPropertyExistenceConstraint getCreateRelationshipPropertyExistenceConstraint();

  @Nullable
  CypherCreateUniqueConstraint getCreateUniqueConstraint();

  @Nullable
  CypherDropIndex getDropIndex();

  @Nullable
  CypherDropNodeKeyConstraint getDropNodeKeyConstraint();

  @Nullable
  CypherDropNodePropertyExistenceConstraint getDropNodePropertyExistenceConstraint();

  @Nullable
  CypherDropRelationshipPropertyExistenceConstraint getDropRelationshipPropertyExistenceConstraint();

  @Nullable
  CypherDropUniqueConstraint getDropUniqueConstraint();

}
