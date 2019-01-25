// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherMapYielding;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTypePropagator;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherListYielding;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherPathYielding;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherBooleanYielding;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherNamedElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherParenthesized;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherNullYielding;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherAnyYielding;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherVariableElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherIntegerYielding;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherStringYielding;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherArgumentList;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherFloatYielding;

public class CypherVisitor extends PsiElementVisitor {

  public void visitAliasedProcedureResult(@NotNull CypherAliasedProcedureResult o) {
    visitPsiElement(o);
  }

  public void visitAllFunctionInvocation(@NotNull CypherAllFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitAllPropertiesSelector(@NotNull CypherAllPropertiesSelector o) {
    visitPsiElement(o);
  }

  public void visitAllShortestPathsFunctionInvocation(@NotNull CypherAllShortestPathsFunctionInvocation o) {
    visitListYielding(o);
  }

  public void visitAnonymousPatternPart(@NotNull CypherAnonymousPatternPart o) {
    visitPsiElement(o);
  }

  public void visitAnyCypherOption(@NotNull CypherAnyCypherOption o) {
    visitPsiElement(o);
  }

  public void visitAnyFunctionInvocation(@NotNull CypherAnyFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitArray(@NotNull CypherArray o) {
    visitListYielding(o);
  }

  public void visitBooleanLiteral(@NotNull CypherBooleanLiteral o) {
    visitBooleanYielding(o);
  }

  public void visitBulkImportQuery(@NotNull CypherBulkImportQuery o) {
    visitPsiElement(o);
  }

  public void visitCall(@NotNull CypherCall o) {
    visitPsiElement(o);
  }

  public void visitCaseAlternatives(@NotNull CypherCaseAlternatives o) {
    visitPsiElement(o);
  }

  public void visitCaseExpression(@NotNull CypherCaseExpression o) {
    visitAnyYielding(o);
  }

  public void visitCommand(@NotNull CypherCommand o) {
    visitPsiElement(o);
  }

  public void visitConfigurationOption(@NotNull CypherConfigurationOption o) {
    visitPsiElement(o);
  }

  public void visitCountStar(@NotNull CypherCountStar o) {
    visitIntegerYielding(o);
  }

  public void visitCreate(@NotNull CypherCreate o) {
    visitPsiElement(o);
  }

  public void visitCreateIndex(@NotNull CypherCreateIndex o) {
    visitPsiElement(o);
  }

  public void visitCreateNodePropertyExistenceConstraint(@NotNull CypherCreateNodePropertyExistenceConstraint o) {
    visitPsiElement(o);
  }

  public void visitCreateRelationshipPropertyExistenceConstraint(@NotNull CypherCreateRelationshipPropertyExistenceConstraint o) {
    visitPsiElement(o);
  }

  public void visitCreateUniqueConstraint(@NotNull CypherCreateUniqueConstraint o) {
    visitPsiElement(o);
  }

  public void visitCypherOption(@NotNull CypherCypherOption o) {
    visitPsiElement(o);
  }

  public void visitDash(@NotNull CypherDash o) {
    visitPsiElement(o);
  }

  public void visitDelete(@NotNull CypherDelete o) {
    visitPsiElement(o);
  }

  public void visitDoubleLiteral(@NotNull CypherDoubleLiteral o) {
    visitFloatYielding(o);
  }

  public void visitDropIndex(@NotNull CypherDropIndex o) {
    visitPsiElement(o);
  }

  public void visitDropNodePropertyExistenceConstraint(@NotNull CypherDropNodePropertyExistenceConstraint o) {
    visitPsiElement(o);
  }

  public void visitDropRelationshipPropertyExistenceConstraint(@NotNull CypherDropRelationshipPropertyExistenceConstraint o) {
    visitPsiElement(o);
  }

  public void visitDropUniqueConstraint(@NotNull CypherDropUniqueConstraint o) {
    visitPsiElement(o);
  }

  public void visitEscapedSymbolicNameString(@NotNull CypherEscapedSymbolicNameString o) {
    visitPsiElement(o);
  }

  public void visitExistsFunctionInvocation(@NotNull CypherExistsFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitExplain(@NotNull CypherExplain o) {
    visitPsiElement(o);
  }

  public void visitExpression(@NotNull CypherExpression o) {
    visitTypePropagator(o);
  }

  public void visitExtractFunctionInvocation(@NotNull CypherExtractFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitFilterExpression(@NotNull CypherFilterExpression o) {
    visitPsiElement(o);
  }

  public void visitFilterFunctionInvocation(@NotNull CypherFilterFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitForeach(@NotNull CypherForeach o) {
    visitPsiElement(o);
  }

  public void visitFunctionArguments(@NotNull CypherFunctionArguments o) {
    visitArgumentList(o);
  }

  public void visitFunctionInvocation(@NotNull CypherFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitFunctionInvocationBody(@NotNull CypherFunctionInvocationBody o) {
    visitPsiElement(o);
  }

  public void visitFunctionName(@NotNull CypherFunctionName o) {
    visitPsiElement(o);
  }

  public void visitHint(@NotNull CypherHint o) {
    visitPsiElement(o);
  }

  public void visitIdInColl(@NotNull CypherIdInColl o) {
    visitPsiElement(o);
  }

  public void visitIdLookup(@NotNull CypherIdLookup o) {
    visitPsiElement(o);
  }

  public void visitIdentifiedIndexLookup(@NotNull CypherIdentifiedIndexLookup o) {
    visitPsiElement(o);
  }

  public void visitIndexQuery(@NotNull CypherIndexQuery o) {
    visitPsiElement(o);
  }

  public void visitIndexSyntax(@NotNull CypherIndexSyntax o) {
    visitPsiElement(o);
  }

  public void visitIntegerLiteral(@NotNull CypherIntegerLiteral o) {
    visitIntegerYielding(o);
  }

  public void visitLabelName(@NotNull CypherLabelName o) {
    visitNamedElement(o);
  }

  public void visitLeftArrowHead(@NotNull CypherLeftArrowHead o) {
    visitPsiElement(o);
  }

  public void visitLimit(@NotNull CypherLimit o) {
    visitPsiElement(o);
  }

  public void visitListComprehension(@NotNull CypherListComprehension o) {
    visitListYielding(o);
  }

  public void visitLiteralEntry(@NotNull CypherLiteralEntry o) {
    visitPsiElement(o);
  }

  public void visitLiteralIds(@NotNull CypherLiteralIds o) {
    visitPsiElement(o);
  }

  public void visitLoadCSV(@NotNull CypherLoadCSV o) {
    visitPsiElement(o);
  }

  public void visitLoadCSVQuery(@NotNull CypherLoadCSVQuery o) {
    visitPsiElement(o);
  }

  public void visitLookup(@NotNull CypherLookup o) {
    visitPsiElement(o);
  }

  public void visitMapLiteral(@NotNull CypherMapLiteral o) {
    visitMapYielding(o);
  }

  public void visitMapProjection(@NotNull CypherMapProjection o) {
    visitMapYielding(o);
  }

  public void visitMapProjectionVariants(@NotNull CypherMapProjectionVariants o) {
    visitPsiElement(o);
  }

  public void visitMatch(@NotNull CypherMatch o) {
    visitPsiElement(o);
  }

  public void visitMaybeVariableLength(@NotNull CypherMaybeVariableLength o) {
    visitPsiElement(o);
  }

  public void visitMerge(@NotNull CypherMerge o) {
    visitPsiElement(o);
  }

  public void visitMergeAction(@NotNull CypherMergeAction o) {
    visitPsiElement(o);
  }

  public void visitMultiPartQuery(@NotNull CypherMultiPartQuery o) {
    visitPsiElement(o);
  }

  public void visitNamespace(@NotNull CypherNamespace o) {
    visitPsiElement(o);
  }

  public void visitNewParameter(@NotNull CypherNewParameter o) {
    visitPsiElement(o);
  }

  public void visitNodeLabel(@NotNull CypherNodeLabel o) {
    visitPsiElement(o);
  }

  public void visitNodeLabels(@NotNull CypherNodeLabels o) {
    visitPsiElement(o);
  }

  public void visitNodeLookup(@NotNull CypherNodeLookup o) {
    visitPsiElement(o);
  }

  public void visitNodePattern(@NotNull CypherNodePattern o) {
    visitPsiElement(o);
  }

  public void visitNodePropertyExistenceConstraintSyntax(@NotNull CypherNodePropertyExistenceConstraintSyntax o) {
    visitPsiElement(o);
  }

  public void visitNoneFunctionInvocation(@NotNull CypherNoneFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitNullLiteral(@NotNull CypherNullLiteral o) {
    visitNullYielding(o);
  }

  public void visitNumberLiteral(@NotNull CypherNumberLiteral o) {
    visitTypePropagator(o);
  }

  public void visitOldParameter(@NotNull CypherOldParameter o) {
    visitPsiElement(o);
  }

  public void visitOrder(@NotNull CypherOrder o) {
    visitPsiElement(o);
  }

  public void visitParameter(@NotNull CypherParameter o) {
    visitAnyYielding(o);
  }

  public void visitParenthesizedExpression(@NotNull CypherParenthesizedExpression o) {
    visitParenthesized(o);
  }

  public void visitPattern(@NotNull CypherPattern o) {
    visitPsiElement(o);
  }

  public void visitPatternComprehension(@NotNull CypherPatternComprehension o) {
    visitListYielding(o);
  }

  public void visitPatternElement(@NotNull CypherPatternElement o) {
    visitPsiElement(o);
  }

  public void visitPatternElementChain(@NotNull CypherPatternElementChain o) {
    visitPsiElement(o);
  }

  public void visitPatternPart(@NotNull CypherPatternPart o) {
    visitPsiElement(o);
  }

  public void visitPeriodicCommitHint(@NotNull CypherPeriodicCommitHint o) {
    visitPsiElement(o);
  }

  public void visitProcedureArguments(@NotNull CypherProcedureArguments o) {
    visitArgumentList(o);
  }

  public void visitProcedureInvocation(@NotNull CypherProcedureInvocation o) {
    visitInvocation(o);
  }

  public void visitProcedureInvocationBody(@NotNull CypherProcedureInvocationBody o) {
    visitPsiElement(o);
  }

  public void visitProcedureName(@NotNull CypherProcedureName o) {
    visitPsiElement(o);
  }

  public void visitProcedureOutput(@NotNull CypherProcedureOutput o) {
    visitPsiElement(o);
  }

  public void visitProcedureResult(@NotNull CypherProcedureResult o) {
    visitPsiElement(o);
  }

  public void visitProcedureResults(@NotNull CypherProcedureResults o) {
    visitPsiElement(o);
  }

  public void visitProfile(@NotNull CypherProfile o) {
    visitPsiElement(o);
  }

  public void visitProperties(@NotNull CypherProperties o) {
    visitPsiElement(o);
  }

  public void visitPropertyExpression(@NotNull CypherPropertyExpression o) {
    visitPsiElement(o);
  }

  public void visitPropertyKeyName(@NotNull CypherPropertyKeyName o) {
    visitNamedElement(o);
  }

  public void visitPropertyKeyNames(@NotNull CypherPropertyKeyNames o) {
    visitPsiElement(o);
  }

  public void visitPropertyLookup(@NotNull CypherPropertyLookup o) {
    visitPsiElement(o);
  }

  public void visitPropertySelector(@NotNull CypherPropertySelector o) {
    visitPsiElement(o);
  }

  public void visitQuery(@NotNull CypherQuery o) {
    visitPsiElement(o);
  }

  public void visitQueryOptions(@NotNull CypherQueryOptions o) {
    visitPsiElement(o);
  }

  public void visitRangeLiteral(@NotNull CypherRangeLiteral o) {
    visitPsiElement(o);
  }

  public void visitReadingClause(@NotNull CypherReadingClause o) {
    visitPsiElement(o);
  }

  public void visitReadingWithReturn(@NotNull CypherReadingWithReturn o) {
    visitPsiElement(o);
  }

  public void visitReduceFunctionInvocation(@NotNull CypherReduceFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitRegularQuery(@NotNull CypherRegularQuery o) {
    visitPsiElement(o);
  }

  public void visitRelType(@NotNull CypherRelType o) {
    visitPsiElement(o);
  }

  public void visitRelTypeName(@NotNull CypherRelTypeName o) {
    visitNamedElement(o);
  }

  public void visitRelationshipDetail(@NotNull CypherRelationshipDetail o) {
    visitPsiElement(o);
  }

  public void visitRelationshipLookup(@NotNull CypherRelationshipLookup o) {
    visitPsiElement(o);
  }

  public void visitRelationshipPattern(@NotNull CypherRelationshipPattern o) {
    visitPsiElement(o);
  }

  public void visitRelationshipPatternSyntax(@NotNull CypherRelationshipPatternSyntax o) {
    visitPsiElement(o);
  }

  public void visitRelationshipPropertyExistenceConstraintSyntax(@NotNull CypherRelationshipPropertyExistenceConstraintSyntax o) {
    visitPsiElement(o);
  }

  public void visitRelationshipTypes(@NotNull CypherRelationshipTypes o) {
    visitPsiElement(o);
  }

  public void visitRelationshipsPattern(@NotNull CypherRelationshipsPattern o) {
    visitPathYielding(o);
  }

  public void visitRemove(@NotNull CypherRemove o) {
    visitPsiElement(o);
  }

  public void visitRemoveItem(@NotNull CypherRemoveItem o) {
    visitPsiElement(o);
  }

  public void visitReservedWord(@NotNull CypherReservedWord o) {
    visitPsiElement(o);
  }

  public void visitReturn(@NotNull CypherReturn o) {
    visitPsiElement(o);
  }

  public void visitReturnBody(@NotNull CypherReturnBody o) {
    visitPsiElement(o);
  }

  public void visitReturnItem(@NotNull CypherReturnItem o) {
    visitPsiElement(o);
  }

  public void visitReturnItems(@NotNull CypherReturnItems o) {
    visitPsiElement(o);
  }

  public void visitRightArrowHead(@NotNull CypherRightArrowHead o) {
    visitPsiElement(o);
  }

  public void visitSetClause(@NotNull CypherSetClause o) {
    visitPsiElement(o);
  }

  public void visitSetItem(@NotNull CypherSetItem o) {
    visitPsiElement(o);
  }

  public void visitShortestPathFunctionInvocation(@NotNull CypherShortestPathFunctionInvocation o) {
    visitPathYielding(o);
  }

  public void visitShortestPathPattern(@NotNull CypherShortestPathPattern o) {
    visitTypePropagator(o);
  }

  public void visitSimpleProcedureResult(@NotNull CypherSimpleProcedureResult o) {
    visitPsiElement(o);
  }

  public void visitSingleFunctionInvocation(@NotNull CypherSingleFunctionInvocation o) {
    visitInvocation(o);
  }

  public void visitSinglePartQuery(@NotNull CypherSinglePartQuery o) {
    visitPsiElement(o);
  }

  public void visitSingleQuery(@NotNull CypherSingleQuery o) {
    visitPsiElement(o);
  }

  public void visitSkip(@NotNull CypherSkip o) {
    visitPsiElement(o);
  }

  public void visitSortItem(@NotNull CypherSortItem o) {
    visitPsiElement(o);
  }

  public void visitStandaloneCall(@NotNull CypherStandaloneCall o) {
    visitPsiElement(o);
  }

  public void visitStart(@NotNull CypherStart o) {
    visitPsiElement(o);
  }

  public void visitStartPoint(@NotNull CypherStartPoint o) {
    visitPsiElement(o);
  }

  public void visitStatement(@NotNull CypherStatement o) {
    visitPsiElement(o);
  }

  public void visitStatementItem(@NotNull CypherStatementItem o) {
    visitPsiElement(o);
  }

  public void visitStringLiteral(@NotNull CypherStringLiteral o) {
    visitStringYielding(o);
  }

  public void visitSymbolicNameString(@NotNull CypherSymbolicNameString o) {
    visitPsiElement(o);
  }

  public void visitUnaryOperator(@NotNull CypherUnaryOperator o) {
    visitTypePropagator(o);
  }

  public void visitUnescapedSymbolicNameString(@NotNull CypherUnescapedSymbolicNameString o) {
    visitPsiElement(o);
  }

  public void visitUnion(@NotNull CypherUnion o) {
    visitPsiElement(o);
  }

  public void visitUniqueConstraintSyntax(@NotNull CypherUniqueConstraintSyntax o) {
    visitPsiElement(o);
  }

  public void visitUnsignedDouble(@NotNull CypherUnsignedDouble o) {
    visitPsiElement(o);
  }

  public void visitUnsignedInteger(@NotNull CypherUnsignedInteger o) {
    visitPsiElement(o);
  }

  public void visitUnwind(@NotNull CypherUnwind o) {
    visitPsiElement(o);
  }

  public void visitUpdatingClause(@NotNull CypherUpdatingClause o) {
    visitPsiElement(o);
  }

  public void visitVariable(@NotNull CypherVariable o) {
    visitVariableElement(o);
  }

  public void visitVariableSelector(@NotNull CypherVariableSelector o) {
    visitPsiElement(o);
  }

  public void visitVersionNumber(@NotNull CypherVersionNumber o) {
    visitPsiElement(o);
  }

  public void visitWhere(@NotNull CypherWhere o) {
    visitPsiElement(o);
  }

  public void visitWith(@NotNull CypherWith o) {
    visitPsiElement(o);
  }

  public void visitAnyYielding(@NotNull CypherAnyYielding o) {
    visitPsiElement(o);
  }

  public void visitArgumentList(@NotNull CypherArgumentList o) {
    visitPsiElement(o);
  }

  public void visitBooleanYielding(@NotNull CypherBooleanYielding o) {
    visitPsiElement(o);
  }

  public void visitFloatYielding(@NotNull CypherFloatYielding o) {
    visitPsiElement(o);
  }

  public void visitIntegerYielding(@NotNull CypherIntegerYielding o) {
    visitPsiElement(o);
  }

  public void visitInvocation(@NotNull CypherInvocation o) {
    visitPsiElement(o);
  }

  public void visitListYielding(@NotNull CypherListYielding o) {
    visitPsiElement(o);
  }

  public void visitMapYielding(@NotNull CypherMapYielding o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull CypherNamedElement o) {
    visitPsiElement(o);
  }

  public void visitNullYielding(@NotNull CypherNullYielding o) {
    visitPsiElement(o);
  }

  public void visitParenthesized(@NotNull CypherParenthesized o) {
    visitPsiElement(o);
  }

  public void visitPathYielding(@NotNull CypherPathYielding o) {
    visitPsiElement(o);
  }

  public void visitStringYielding(@NotNull CypherStringYielding o) {
    visitPsiElement(o);
  }

  public void visitTypePropagator(@NotNull CypherTypePropagator o) {
    visitPsiElement(o);
  }

  public void visitVariableElement(@NotNull CypherVariableElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
