// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;

public class CypherReservedWordImpl extends ASTWrapperPsiElement implements CypherReservedWord {

  public CypherReservedWordImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CypherVisitor visitor) {
    visitor.visitReservedWord(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CypherVisitor) accept((CypherVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getKAdd() {
    return findChildByType(K_ADD);
  }

  @Override
  @Nullable
  public PsiElement getKAll() {
    return findChildByType(K_ALL);
  }

  @Override
  @Nullable
  public PsiElement getKAllshortestpaths() {
    return findChildByType(K_ALLSHORTESTPATHS);
  }

  @Override
  @Nullable
  public PsiElement getKAnd() {
    return findChildByType(K_AND);
  }

  @Override
  @Nullable
  public PsiElement getKAny() {
    return findChildByType(K_ANY);
  }

  @Override
  @Nullable
  public PsiElement getKAs() {
    return findChildByType(K_AS);
  }

  @Override
  @Nullable
  public PsiElement getKAsc() {
    return findChildByType(K_ASC);
  }

  @Override
  @Nullable
  public PsiElement getKAscending() {
    return findChildByType(K_ASCENDING);
  }

  @Override
  @Nullable
  public PsiElement getKAssert() {
    return findChildByType(K_ASSERT);
  }

  @Override
  @Nullable
  public PsiElement getKBegin() {
    return findChildByType(K_BEGIN);
  }

  @Override
  @Nullable
  public PsiElement getKBy() {
    return findChildByType(K_BY);
  }

  @Override
  @Nullable
  public PsiElement getKCall() {
    return findChildByType(K_CALL);
  }

  @Override
  @Nullable
  public PsiElement getKCase() {
    return findChildByType(K_CASE);
  }

  @Override
  @Nullable
  public PsiElement getKCommit() {
    return findChildByType(K_COMMIT);
  }

  @Override
  @Nullable
  public PsiElement getKConstraint() {
    return findChildByType(K_CONSTRAINT);
  }

  @Override
  @Nullable
  public PsiElement getKContains() {
    return findChildByType(K_CONTAINS);
  }

  @Override
  @Nullable
  public PsiElement getKCount() {
    return findChildByType(K_COUNT);
  }

  @Override
  @Nullable
  public PsiElement getKCreate() {
    return findChildByType(K_CREATE);
  }

  @Override
  @Nullable
  public PsiElement getKCsv() {
    return findChildByType(K_CSV);
  }

  @Override
  @Nullable
  public PsiElement getKCypher() {
    return findChildByType(K_CYPHER);
  }

  @Override
  @Nullable
  public PsiElement getKDelete() {
    return findChildByType(K_DELETE);
  }

  @Override
  @Nullable
  public PsiElement getKDesc() {
    return findChildByType(K_DESC);
  }

  @Override
  @Nullable
  public PsiElement getKDescending() {
    return findChildByType(K_DESCENDING);
  }

  @Override
  @Nullable
  public PsiElement getKDetach() {
    return findChildByType(K_DETACH);
  }

  @Override
  @Nullable
  public PsiElement getKDistinct() {
    return findChildByType(K_DISTINCT);
  }

  @Override
  @Nullable
  public PsiElement getKDo() {
    return findChildByType(K_DO);
  }

  @Override
  @Nullable
  public PsiElement getKDrop() {
    return findChildByType(K_DROP);
  }

  @Override
  @Nullable
  public PsiElement getKElse() {
    return findChildByType(K_ELSE);
  }

  @Override
  @Nullable
  public PsiElement getKEnd() {
    return findChildByType(K_END);
  }

  @Override
  @Nullable
  public PsiElement getKEnds() {
    return findChildByType(K_ENDS);
  }

  @Override
  @Nullable
  public PsiElement getKExists() {
    return findChildByType(K_EXISTS);
  }

  @Override
  @Nullable
  public PsiElement getKExplain() {
    return findChildByType(K_EXPLAIN);
  }

  @Override
  @Nullable
  public PsiElement getKExtract() {
    return findChildByType(K_EXTRACT);
  }

  @Override
  @Nullable
  public PsiElement getKFalse() {
    return findChildByType(K_FALSE);
  }

  @Override
  @Nullable
  public PsiElement getKFieldterminator() {
    return findChildByType(K_FIELDTERMINATOR);
  }

  @Override
  @Nullable
  public PsiElement getKFilter() {
    return findChildByType(K_FILTER);
  }

  @Override
  @Nullable
  public PsiElement getKFor() {
    return findChildByType(K_FOR);
  }

  @Override
  @Nullable
  public PsiElement getKForeach() {
    return findChildByType(K_FOREACH);
  }

  @Override
  @Nullable
  public PsiElement getKFrom() {
    return findChildByType(K_FROM);
  }

  @Override
  @Nullable
  public PsiElement getKHeaders() {
    return findChildByType(K_HEADERS);
  }

  @Override
  @Nullable
  public PsiElement getKIn() {
    return findChildByType(K_IN);
  }

  @Override
  @Nullable
  public PsiElement getKIndex() {
    return findChildByType(K_INDEX);
  }

  @Override
  @Nullable
  public PsiElement getKIs() {
    return findChildByType(K_IS);
  }

  @Override
  @Nullable
  public PsiElement getKJoin() {
    return findChildByType(K_JOIN);
  }

  @Override
  @Nullable
  public PsiElement getKLimit() {
    return findChildByType(K_LIMIT);
  }

  @Override
  @Nullable
  public PsiElement getKLoad() {
    return findChildByType(K_LOAD);
  }

  @Override
  @Nullable
  public PsiElement getKMandatory() {
    return findChildByType(K_MANDATORY);
  }

  @Override
  @Nullable
  public PsiElement getKMatch() {
    return findChildByType(K_MATCH);
  }

  @Override
  @Nullable
  public PsiElement getKMerge() {
    return findChildByType(K_MERGE);
  }

  @Override
  @Nullable
  public PsiElement getKNode() {
    return findChildByType(K_NODE);
  }

  @Override
  @Nullable
  public PsiElement getKNone() {
    return findChildByType(K_NONE);
  }

  @Override
  @Nullable
  public PsiElement getKNot() {
    return findChildByType(K_NOT);
  }

  @Override
  @Nullable
  public PsiElement getKNull() {
    return findChildByType(K_NULL);
  }

  @Override
  @Nullable
  public PsiElement getKOf() {
    return findChildByType(K_OF);
  }

  @Override
  @Nullable
  public PsiElement getKOn() {
    return findChildByType(K_ON);
  }

  @Override
  @Nullable
  public PsiElement getKOptional() {
    return findChildByType(K_OPTIONAL);
  }

  @Override
  @Nullable
  public PsiElement getKOr() {
    return findChildByType(K_OR);
  }

  @Override
  @Nullable
  public PsiElement getKOrder() {
    return findChildByType(K_ORDER);
  }

  @Override
  @Nullable
  public PsiElement getKPeriodic() {
    return findChildByType(K_PERIODIC);
  }

  @Override
  @Nullable
  public PsiElement getKProfile() {
    return findChildByType(K_PROFILE);
  }

  @Override
  @Nullable
  public PsiElement getKReduce() {
    return findChildByType(K_REDUCE);
  }

  @Override
  @Nullable
  public PsiElement getKRel() {
    return findChildByType(K_REL);
  }

  @Override
  @Nullable
  public PsiElement getKRelationship() {
    return findChildByType(K_RELATIONSHIP);
  }

  @Override
  @Nullable
  public PsiElement getKRemove() {
    return findChildByType(K_REMOVE);
  }

  @Override
  @Nullable
  public PsiElement getKRequire() {
    return findChildByType(K_REQUIRE);
  }

  @Override
  @Nullable
  public PsiElement getKReturn() {
    return findChildByType(K_RETURN);
  }

  @Override
  @Nullable
  public PsiElement getKScalar() {
    return findChildByType(K_SCALAR);
  }

  @Override
  @Nullable
  public PsiElement getKScan() {
    return findChildByType(K_SCAN);
  }

  @Override
  @Nullable
  public PsiElement getKSet() {
    return findChildByType(K_SET);
  }

  @Override
  @Nullable
  public PsiElement getKShortestpath() {
    return findChildByType(K_SHORTESTPATH);
  }

  @Override
  @Nullable
  public PsiElement getKSingle() {
    return findChildByType(K_SINGLE);
  }

  @Override
  @Nullable
  public PsiElement getKSkip() {
    return findChildByType(K_SKIP);
  }

  @Override
  @Nullable
  public PsiElement getKStart() {
    return findChildByType(K_START);
  }

  @Override
  @Nullable
  public PsiElement getKStarts() {
    return findChildByType(K_STARTS);
  }

  @Override
  @Nullable
  public PsiElement getKThen() {
    return findChildByType(K_THEN);
  }

  @Override
  @Nullable
  public PsiElement getKTrue() {
    return findChildByType(K_TRUE);
  }

  @Override
  @Nullable
  public PsiElement getKUnion() {
    return findChildByType(K_UNION);
  }

  @Override
  @Nullable
  public PsiElement getKUnique() {
    return findChildByType(K_UNIQUE);
  }

  @Override
  @Nullable
  public PsiElement getKUnwind() {
    return findChildByType(K_UNWIND);
  }

  @Override
  @Nullable
  public PsiElement getKUsing() {
    return findChildByType(K_USING);
  }

  @Override
  @Nullable
  public PsiElement getKWhen() {
    return findChildByType(K_WHEN);
  }

  @Override
  @Nullable
  public PsiElement getKWhere() {
    return findChildByType(K_WHERE);
  }

  @Override
  @Nullable
  public PsiElement getKWith() {
    return findChildByType(K_WITH);
  }

  @Override
  @Nullable
  public PsiElement getKXor() {
    return findChildByType(K_XOR);
  }

  @Override
  @Nullable
  public PsiElement getKYield() {
    return findChildByType(K_YIELD);
  }

}
