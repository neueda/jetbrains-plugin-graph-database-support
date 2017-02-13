// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CypherParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ALIASED_PROCEDURE_RESULT) {
      r = AliasedProcedureResult(b, 0);
    }
    else if (t == ALL_FUNCTION_INVOCATION) {
      r = AllFunctionInvocation(b, 0);
    }
    else if (t == ALL_PROPERTIES_SELECTOR) {
      r = AllPropertiesSelector(b, 0);
    }
    else if (t == ALL_SHORTEST_PATHS_FUNCTION_INVOCATION) {
      r = AllShortestPathsFunctionInvocation(b, 0);
    }
    else if (t == ANONYMOUS_PATTERN_PART) {
      r = AnonymousPatternPart(b, 0);
    }
    else if (t == ANY_CYPHER_OPTION) {
      r = AnyCypherOption(b, 0);
    }
    else if (t == ANY_FUNCTION_INVOCATION) {
      r = AnyFunctionInvocation(b, 0);
    }
    else if (t == BULK_IMPORT_QUERY) {
      r = BulkImportQuery(b, 0);
    }
    else if (t == CALL) {
      r = Call(b, 0);
    }
    else if (t == CASE_ALTERNATIVES) {
      r = CaseAlternatives(b, 0);
    }
    else if (t == CASE_EXPRESSION) {
      r = CaseExpression(b, 0);
    }
    else if (t == CLAUSE) {
      r = Clause(b, 0);
    }
    else if (t == COMMAND) {
      r = Command(b, 0);
    }
    else if (t == CONFIGURATION_OPTION) {
      r = ConfigurationOption(b, 0);
    }
    else if (t == COUNT_STAR) {
      r = CountStar(b, 0);
    }
    else if (t == CREATE) {
      r = Create(b, 0);
    }
    else if (t == CREATE_INDEX) {
      r = CreateIndex(b, 0);
    }
    else if (t == CREATE_NODE_PROPERTY_EXISTENCE_CONSTRAINT) {
      r = CreateNodePropertyExistenceConstraint(b, 0);
    }
    else if (t == CREATE_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT) {
      r = CreateRelationshipPropertyExistenceConstraint(b, 0);
    }
    else if (t == CREATE_UNIQUE_CONSTRAINT) {
      r = CreateUniqueConstraint(b, 0);
    }
    else if (t == CYPHER_OPTION) {
      r = CypherOption(b, 0);
    }
    else if (t == DASH) {
      r = Dash(b, 0);
    }
    else if (t == DELETE) {
      r = Delete(b, 0);
    }
    else if (t == DIGIT_STRING) {
      r = DigitString(b, 0);
    }
    else if (t == DOUBLE_LITERAL) {
      r = DoubleLiteral(b, 0);
    }
    else if (t == DROP_INDEX) {
      r = DropIndex(b, 0);
    }
    else if (t == DROP_NODE_PROPERTY_EXISTENCE_CONSTRAINT) {
      r = DropNodePropertyExistenceConstraint(b, 0);
    }
    else if (t == DROP_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT) {
      r = DropRelationshipPropertyExistenceConstraint(b, 0);
    }
    else if (t == DROP_UNIQUE_CONSTRAINT) {
      r = DropUniqueConstraint(b, 0);
    }
    else if (t == ESCAPED_SYMBOLIC_NAME_STRING) {
      r = EscapedSymbolicNameString(b, 0);
    }
    else if (t == EXISTS_FUNCTION_INVOCATION) {
      r = ExistsFunctionInvocation(b, 0);
    }
    else if (t == EXPLAIN) {
      r = Explain(b, 0);
    }
    else if (t == EXPRESSION) {
      r = Expression(b, 0);
    }
    else if (t == EXTRACT_FUNCTION_INVOCATION) {
      r = ExtractFunctionInvocation(b, 0);
    }
    else if (t == FILTER_EXPRESSION) {
      r = FilterExpression(b, 0);
    }
    else if (t == FILTER_FUNCTION_INVOCATION) {
      r = FilterFunctionInvocation(b, 0);
    }
    else if (t == FOREACH) {
      r = Foreach(b, 0);
    }
    else if (t == FUNCTION_ARGUMENTS) {
      r = FunctionArguments(b, 0);
    }
    else if (t == FUNCTION_INVOCATION) {
      r = FunctionInvocation(b, 0);
    }
    else if (t == FUNCTION_INVOCATION_BODY) {
      r = FunctionInvocationBody(b, 0);
    }
    else if (t == FUNCTION_NAME) {
      r = FunctionName(b, 0);
    }
    else if (t == HINT) {
      r = Hint(b, 0);
    }
    else if (t == ID_IN_COLL) {
      r = IdInColl(b, 0);
    }
    else if (t == ID_LOOKUP) {
      r = IdLookup(b, 0);
    }
    else if (t == IDENTIFIED_INDEX_LOOKUP) {
      r = IdentifiedIndexLookup(b, 0);
    }
    else if (t == INDEX_QUERY) {
      r = IndexQuery(b, 0);
    }
    else if (t == LABEL_NAME) {
      r = LabelName(b, 0);
    }
    else if (t == LEFT_ARROW_HEAD) {
      r = LeftArrowHead(b, 0);
    }
    else if (t == LIMIT) {
      r = Limit(b, 0);
    }
    else if (t == LIST_COMPREHENSION) {
      r = ListComprehension(b, 0);
    }
    else if (t == LITERAL_ENTRY) {
      r = LiteralEntry(b, 0);
    }
    else if (t == LITERAL_IDS) {
      r = LiteralIds(b, 0);
    }
    else if (t == LOAD_CSV) {
      r = LoadCSV(b, 0);
    }
    else if (t == LOAD_CSV_QUERY) {
      r = LoadCSVQuery(b, 0);
    }
    else if (t == LOOKUP) {
      r = Lookup(b, 0);
    }
    else if (t == MAP_LITERAL) {
      r = MapLiteral(b, 0);
    }
    else if (t == MAP_PROJECTION) {
      r = MapProjection(b, 0);
    }
    else if (t == MAP_PROJECTION_VARIANTS) {
      r = MapProjectionVariants(b, 0);
    }
    else if (t == MATCH) {
      r = Match(b, 0);
    }
    else if (t == MAYBE_VARIABLE_LENGTH) {
      r = MaybeVariableLength(b, 0);
    }
    else if (t == MERGE) {
      r = Merge(b, 0);
    }
    else if (t == MERGE_ACTION) {
      r = MergeAction(b, 0);
    }
    else if (t == NAMESPACE) {
      r = Namespace(b, 0);
    }
    else if (t == NEW_PARAMETER) {
      r = NewParameter(b, 0);
    }
    else if (t == NODE_LABEL) {
      r = NodeLabel(b, 0);
    }
    else if (t == NODE_LABELS) {
      r = NodeLabels(b, 0);
    }
    else if (t == NODE_LOOKUP) {
      r = NodeLookup(b, 0);
    }
    else if (t == NODE_PATTERN) {
      r = NodePattern(b, 0);
    }
    else if (t == NODE_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX) {
      r = NodePropertyExistenceConstraintSyntax(b, 0);
    }
    else if (t == NONE_FUNCTION_INVOCATION) {
      r = NoneFunctionInvocation(b, 0);
    }
    else if (t == NUMBER_LITERAL) {
      r = NumberLiteral(b, 0);
    }
    else if (t == OLD_PARAMETER) {
      r = OldParameter(b, 0);
    }
    else if (t == ORDER) {
      r = Order(b, 0);
    }
    else if (t == PARAMETER) {
      r = Parameter(b, 0);
    }
    else if (t == PATTERN) {
      r = Pattern(b, 0);
    }
    else if (t == PATTERN_COMPREHENSION) {
      r = PatternComprehension(b, 0);
    }
    else if (t == PATTERN_ELEMENT) {
      r = PatternElement(b, 0);
    }
    else if (t == PATTERN_ELEMENT_CHAIN) {
      r = PatternElementChain(b, 0);
    }
    else if (t == PATTERN_PART) {
      r = PatternPart(b, 0);
    }
    else if (t == PERIODIC_COMMIT_HINT) {
      r = PeriodicCommitHint(b, 0);
    }
    else if (t == PROCEDURE_ARGUMENTS) {
      r = ProcedureArguments(b, 0);
    }
    else if (t == PROCEDURE_INVOCATION) {
      r = ProcedureInvocation(b, 0);
    }
    else if (t == PROCEDURE_INVOCATION_BODY) {
      r = ProcedureInvocationBody(b, 0);
    }
    else if (t == PROCEDURE_NAME) {
      r = ProcedureName(b, 0);
    }
    else if (t == PROCEDURE_OUTPUT) {
      r = ProcedureOutput(b, 0);
    }
    else if (t == PROCEDURE_RESULT) {
      r = ProcedureResult(b, 0);
    }
    else if (t == PROCEDURE_RESULTS) {
      r = ProcedureResults(b, 0);
    }
    else if (t == PROFILE) {
      r = Profile(b, 0);
    }
    else if (t == PROPERTIES) {
      r = Properties(b, 0);
    }
    else if (t == PROPERTY_EXPRESSION) {
      r = PropertyExpression(b, 0);
    }
    else if (t == PROPERTY_KEY_NAME) {
      r = PropertyKeyName(b, 0);
    }
    else if (t == PROPERTY_LOOKUP) {
      r = PropertyLookup(b, 0);
    }
    else if (t == PROPERTY_SELECTOR) {
      r = PropertySelector(b, 0);
    }
    else if (t == QUERY) {
      r = Query(b, 0);
    }
    else if (t == QUERY_OPTIONS) {
      r = QueryOptions(b, 0);
    }
    else if (t == RANGE_LITERAL) {
      r = RangeLiteral(b, 0);
    }
    else if (t == REDUCE_FUNCTION_INVOCATION) {
      r = ReduceFunctionInvocation(b, 0);
    }
    else if (t == REGULAR_QUERY) {
      r = RegularQuery(b, 0);
    }
    else if (t == REL_TYPE) {
      r = RelType(b, 0);
    }
    else if (t == REL_TYPE_NAME) {
      r = RelTypeName(b, 0);
    }
    else if (t == RELATIONSHIP_DETAIL) {
      r = RelationshipDetail(b, 0);
    }
    else if (t == RELATIONSHIP_LOOKUP) {
      r = RelationshipLookup(b, 0);
    }
    else if (t == RELATIONSHIP_PATTERN) {
      r = RelationshipPattern(b, 0);
    }
    else if (t == RELATIONSHIP_PATTERN_SYNTAX) {
      r = RelationshipPatternSyntax(b, 0);
    }
    else if (t == RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX) {
      r = RelationshipPropertyExistenceConstraintSyntax(b, 0);
    }
    else if (t == RELATIONSHIP_TYPES) {
      r = RelationshipTypes(b, 0);
    }
    else if (t == RELATIONSHIPS_PATTERN) {
      r = RelationshipsPattern(b, 0);
    }
    else if (t == REMOVE) {
      r = Remove(b, 0);
    }
    else if (t == REMOVE_ITEM) {
      r = RemoveItem(b, 0);
    }
    else if (t == RETURN) {
      r = Return(b, 0);
    }
    else if (t == RETURN_BODY) {
      r = ReturnBody(b, 0);
    }
    else if (t == RETURN_ITEM) {
      r = ReturnItem(b, 0);
    }
    else if (t == RETURN_ITEMS) {
      r = ReturnItems(b, 0);
    }
    else if (t == RIGHT_ARROW_HEAD) {
      r = RightArrowHead(b, 0);
    }
    else if (t == SET_CLAUSE) {
      r = SetClause(b, 0);
    }
    else if (t == SET_ITEM) {
      r = SetItem(b, 0);
    }
    else if (t == SHORTEST_PATH_FUNCTION_INVOCATION) {
      r = ShortestPathFunctionInvocation(b, 0);
    }
    else if (t == SHORTEST_PATH_PATTERN) {
      r = ShortestPathPattern(b, 0);
    }
    else if (t == SIGNED_DECIMAL_INTEGER) {
      r = SignedDecimalInteger(b, 0);
    }
    else if (t == SIGNED_INTEGER_LITERAL) {
      r = SignedIntegerLiteral(b, 0);
    }
    else if (t == SIMPLE_PROCEDURE_RESULT) {
      r = SimpleProcedureResult(b, 0);
    }
    else if (t == SINGLE_FUNCTION_INVOCATION) {
      r = SingleFunctionInvocation(b, 0);
    }
    else if (t == SINGLE_QUERY) {
      r = SingleQuery(b, 0);
    }
    else if (t == SKIP) {
      r = Skip(b, 0);
    }
    else if (t == SORT_ITEM) {
      r = SortItem(b, 0);
    }
    else if (t == START) {
      r = Start(b, 0);
    }
    else if (t == START_POINT) {
      r = StartPoint(b, 0);
    }
    else if (t == STATEMENT) {
      r = Statement(b, 0);
    }
    else if (t == STATEMENT_ITEM) {
      r = StatementItem(b, 0);
    }
    else if (t == STRING_LITERAL) {
      r = StringLiteral(b, 0);
    }
    else if (t == SYMBOLIC_NAME_STRING) {
      r = SymbolicNameString(b, 0);
    }
    else if (t == UNESCAPED_SYMBOLIC_NAME_STRING) {
      r = UnescapedSymbolicNameString(b, 0);
    }
    else if (t == UNION) {
      r = Union(b, 0);
    }
    else if (t == UNIQUE_CONSTRAINT_SYNTAX) {
      r = UniqueConstraintSyntax(b, 0);
    }
    else if (t == UNSIGNED_DECIMAL_INTEGER) {
      r = UnsignedDecimalInteger(b, 0);
    }
    else if (t == UNSIGNED_INTEGER_LITERAL) {
      r = UnsignedIntegerLiteral(b, 0);
    }
    else if (t == UNWIND) {
      r = Unwind(b, 0);
    }
    else if (t == VARIABLE) {
      r = Variable(b, 0);
    }
    else if (t == VARIABLE_SELECTOR) {
      r = VariableSelector(b, 0);
    }
    else if (t == VERSION_NUMBER) {
      r = VersionNumber(b, 0);
    }
    else if (t == WHERE) {
      r = Where(b, 0);
    }
    else if (t == WITH) {
      r = With(b, 0);
    }
    else if (t == PARENTHESIZED_EXPRESSION) {
      r = parenthesizedExpression(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return cypher(b, l + 1);
  }

  /* ********************************************************** */
  // ProcedureOutput K_AS Variable
  public static boolean AliasedProcedureResult(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AliasedProcedureResult")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ALIASED_PROCEDURE_RESULT, "<aliased procedure result>");
    r = ProcedureOutput(b, l + 1);
    r = r && consumeToken(b, K_AS);
    r = r && Variable(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // K_ALL "(" FilterExpression ")"
  public static boolean AllFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AllFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_ALL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ALL, PARENTHESE_OPEN);
    r = r && FilterExpression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, ALL_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // "." "*"
  public static boolean AllPropertiesSelector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AllPropertiesSelector")) return false;
    if (!nextTokenIs(b, OP_DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OP_DOT, OP_MUL);
    exit_section_(b, m, ALL_PROPERTIES_SELECTOR, r);
    return r;
  }

  /* ********************************************************** */
  // K_ALLSHORTESTPATHS "(" PatternElement ")"
  public static boolean AllShortestPathsFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AllShortestPathsFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_ALLSHORTESTPATHS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ALLSHORTESTPATHS, PARENTHESE_OPEN);
    r = r && PatternElement(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, ALL_SHORTEST_PATHS_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // ShortestPathPattern | PatternElement
  public static boolean AnonymousPatternPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnonymousPatternPart")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANONYMOUS_PATTERN_PART, "<anonymous pattern part>");
    r = ShortestPathPattern(b, l + 1);
    if (!r) r = PatternElement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CypherOption | Explain | Profile
  public static boolean AnyCypherOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnyCypherOption")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANY_CYPHER_OPTION, "<any cypher option>");
    r = CypherOption(b, l + 1);
    if (!r) r = Explain(b, l + 1);
    if (!r) r = Profile(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // K_ANY "(" FilterExpression ")"
  public static boolean AnyFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnyFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_ANY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ANY, PARENTHESE_OPEN);
    r = r && FilterExpression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, ANY_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // PeriodicCommitHint LoadCSVQuery
  public static boolean BulkImportQuery(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BulkImportQuery")) return false;
    if (!nextTokenIs(b, K_USING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PeriodicCommitHint(b, l + 1);
    r = r && LoadCSVQuery(b, l + 1);
    exit_section_(b, m, BULK_IMPORT_QUERY, r);
    return r;
  }

  /* ********************************************************** */
  // K_CALL ProcedureInvocation ProcedureResults?
  public static boolean Call(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Call")) return false;
    if (!nextTokenIs(b, K_CALL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CALL);
    r = r && ProcedureInvocation(b, l + 1);
    r = r && Call_2(b, l + 1);
    exit_section_(b, m, CALL, r);
    return r;
  }

  // ProcedureResults?
  private static boolean Call_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Call_2")) return false;
    ProcedureResults(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // K_WHEN Expression K_THEN Expression
  public static boolean CaseAlternatives(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseAlternatives")) return false;
    if (!nextTokenIs(b, K_WHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_WHEN);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, K_THEN);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, CASE_ALTERNATIVES, r);
    return r;
  }

  /* ********************************************************** */
  // ((K_CASE CaseAlternatives+) | (K_CASE Expression CaseAlternatives+))
  //                    (K_ELSE Expression)?
  //                    K_END
  public static boolean CaseExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseExpression")) return false;
    if (!nextTokenIs(b, K_CASE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CaseExpression_0(b, l + 1);
    r = r && CaseExpression_1(b, l + 1);
    r = r && consumeToken(b, K_END);
    exit_section_(b, m, CASE_EXPRESSION, r);
    return r;
  }

  // (K_CASE CaseAlternatives+) | (K_CASE Expression CaseAlternatives+)
  private static boolean CaseExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CaseExpression_0_0(b, l + 1);
    if (!r) r = CaseExpression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_CASE CaseAlternatives+
  private static boolean CaseExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CASE);
    r = r && CaseExpression_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CaseAlternatives+
  private static boolean CaseExpression_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseExpression_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CaseAlternatives(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!CaseAlternatives(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CaseExpression_0_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // K_CASE Expression CaseAlternatives+
  private static boolean CaseExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseExpression_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CASE);
    r = r && Expression(b, l + 1);
    r = r && CaseExpression_0_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CaseAlternatives+
  private static boolean CaseExpression_0_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseExpression_0_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CaseAlternatives(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!CaseAlternatives(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CaseExpression_0_1_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (K_ELSE Expression)?
  private static boolean CaseExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseExpression_1")) return false;
    CaseExpression_1_0(b, l + 1);
    return true;
  }

  // K_ELSE Expression
  private static boolean CaseExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_ELSE);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LoadCSV
  //     | Start
  //     | Match
  //     | Unwind
  //     | Merge
  //     | Create
  //     | SetClause
  //     | Delete
  //     | Remove
  //     | Foreach
  //     | With
  //     | Return
  //     | Call
  public static boolean Clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Clause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLAUSE, "<clause>");
    r = LoadCSV(b, l + 1);
    if (!r) r = Start(b, l + 1);
    if (!r) r = Match(b, l + 1);
    if (!r) r = Unwind(b, l + 1);
    if (!r) r = Merge(b, l + 1);
    if (!r) r = Create(b, l + 1);
    if (!r) r = SetClause(b, l + 1);
    if (!r) r = Delete(b, l + 1);
    if (!r) r = Remove(b, l + 1);
    if (!r) r = Foreach(b, l + 1);
    if (!r) r = With(b, l + 1);
    if (!r) r = Return(b, l + 1);
    if (!r) r = Call(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CreateIndex
  //           | DropIndex
  //           | CreateUniqueConstraint
  //           | DropUniqueConstraint
  //           | CreateNodePropertyExistenceConstraint
  //           | DropNodePropertyExistenceConstraint
  //           | CreateRelationshipPropertyExistenceConstraint
  //           | DropRelationshipPropertyExistenceConstraint
  public static boolean Command(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Command")) return false;
    if (!nextTokenIs(b, "<command>", K_CREATE, K_DROP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMAND, "<command>");
    r = CreateIndex(b, l + 1);
    if (!r) r = DropIndex(b, l + 1);
    if (!r) r = CreateUniqueConstraint(b, l + 1);
    if (!r) r = DropUniqueConstraint(b, l + 1);
    if (!r) r = CreateNodePropertyExistenceConstraint(b, l + 1);
    if (!r) r = DropNodePropertyExistenceConstraint(b, l + 1);
    if (!r) r = CreateRelationshipPropertyExistenceConstraint(b, l + 1);
    if (!r) r = DropRelationshipPropertyExistenceConstraint(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SymbolicNameString '=' SymbolicNameString
  public static boolean ConfigurationOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConfigurationOption")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONFIGURATION_OPTION, "<configuration option>");
    r = SymbolicNameString(b, l + 1);
    r = r && consumeToken(b, OP_EQUAL);
    r = r && SymbolicNameString(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // K_COUNT "(" "*" ")"
  public static boolean CountStar(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CountStar")) return false;
    if (!nextTokenIs(b, K_COUNT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_COUNT, PARENTHESE_OPEN, OP_MUL, PARENTHESE_CLOSE);
    exit_section_(b, m, COUNT_STAR, r);
    return r;
  }

  /* ********************************************************** */
  // (K_CREATE K_UNIQUE Pattern)
  //          | (K_CREATE Pattern)
  public static boolean Create(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Create")) return false;
    if (!nextTokenIs(b, K_CREATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Create_0(b, l + 1);
    if (!r) r = Create_1(b, l + 1);
    exit_section_(b, m, CREATE, r);
    return r;
  }

  // K_CREATE K_UNIQUE Pattern
  private static boolean Create_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Create_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_CREATE, K_UNIQUE);
    r = r && Pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_CREATE Pattern
  private static boolean Create_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Create_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CREATE);
    r = r && Pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_CREATE K_INDEX K_ON NodeLabel "(" PropertyKeyName ")"
  public static boolean CreateIndex(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CreateIndex")) return false;
    if (!nextTokenIs(b, K_CREATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_CREATE, K_INDEX, K_ON);
    r = r && NodeLabel(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_OPEN);
    r = r && PropertyKeyName(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, CREATE_INDEX, r);
    return r;
  }

  /* ********************************************************** */
  // K_CREATE NodePropertyExistenceConstraintSyntax
  public static boolean CreateNodePropertyExistenceConstraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CreateNodePropertyExistenceConstraint")) return false;
    if (!nextTokenIs(b, K_CREATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CREATE);
    r = r && NodePropertyExistenceConstraintSyntax(b, l + 1);
    exit_section_(b, m, CREATE_NODE_PROPERTY_EXISTENCE_CONSTRAINT, r);
    return r;
  }

  /* ********************************************************** */
  // K_CREATE RelationshipPropertyExistenceConstraintSyntax
  public static boolean CreateRelationshipPropertyExistenceConstraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CreateRelationshipPropertyExistenceConstraint")) return false;
    if (!nextTokenIs(b, K_CREATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CREATE);
    r = r && RelationshipPropertyExistenceConstraintSyntax(b, l + 1);
    exit_section_(b, m, CREATE_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT, r);
    return r;
  }

  /* ********************************************************** */
  // K_CREATE UniqueConstraintSyntax
  public static boolean CreateUniqueConstraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CreateUniqueConstraint")) return false;
    if (!nextTokenIs(b, K_CREATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CREATE);
    r = r && UniqueConstraintSyntax(b, l + 1);
    exit_section_(b, m, CREATE_UNIQUE_CONSTRAINT, r);
    return r;
  }

  /* ********************************************************** */
  // K_CYPHER VersionNumber? ConfigurationOption*
  public static boolean CypherOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CypherOption")) return false;
    if (!nextTokenIs(b, K_CYPHER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CYPHER);
    r = r && CypherOption_1(b, l + 1);
    r = r && CypherOption_2(b, l + 1);
    exit_section_(b, m, CYPHER_OPTION, r);
    return r;
  }

  // VersionNumber?
  private static boolean CypherOption_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CypherOption_1")) return false;
    VersionNumber(b, l + 1);
    return true;
  }

  // ConfigurationOption*
  private static boolean CypherOption_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CypherOption_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ConfigurationOption(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CypherOption_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "-"
  public static boolean Dash(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Dash")) return false;
    if (!nextTokenIs(b, OP_MINUS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_MINUS);
    exit_section_(b, m, DASH, r);
    return r;
  }

  /* ********************************************************** */
  // (K_DELETE Expression ("," Expression)*)
  //          | (K_DETACH K_DELETE Expression ("," Expression)*)
  public static boolean Delete(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Delete")) return false;
    if (!nextTokenIs(b, "<delete>", K_DELETE, K_DETACH)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DELETE, "<delete>");
    r = Delete_0(b, l + 1);
    if (!r) r = Delete_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // K_DELETE Expression ("," Expression)*
  private static boolean Delete_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Delete_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DELETE);
    r = r && Expression(b, l + 1);
    r = r && Delete_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," Expression)*
  private static boolean Delete_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Delete_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Delete_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Delete_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Expression
  private static boolean Delete_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Delete_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_DETACH K_DELETE Expression ("," Expression)*
  private static boolean Delete_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Delete_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_DETACH, K_DELETE);
    r = r && Expression(b, l + 1);
    r = r && Delete_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," Expression)*
  private static boolean Delete_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Delete_1_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Delete_1_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Delete_1_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Expression
  private static boolean Delete_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Delete_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // l_integer
  public static boolean DigitString(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DigitString")) return false;
    if (!nextTokenIs(b, L_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_INTEGER);
    exit_section_(b, m, DIGIT_STRING, r);
    return r;
  }

  /* ********************************************************** */
  // l_decimal
  public static boolean DoubleLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DoubleLiteral")) return false;
    if (!nextTokenIs(b, L_DECIMAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_DECIMAL);
    exit_section_(b, m, DOUBLE_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // K_DROP K_INDEX K_ON NodeLabel "(" PropertyKeyName ")"
  public static boolean DropIndex(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DropIndex")) return false;
    if (!nextTokenIs(b, K_DROP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_DROP, K_INDEX, K_ON);
    r = r && NodeLabel(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_OPEN);
    r = r && PropertyKeyName(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, DROP_INDEX, r);
    return r;
  }

  /* ********************************************************** */
  // K_DROP NodePropertyExistenceConstraintSyntax
  public static boolean DropNodePropertyExistenceConstraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DropNodePropertyExistenceConstraint")) return false;
    if (!nextTokenIs(b, K_DROP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DROP);
    r = r && NodePropertyExistenceConstraintSyntax(b, l + 1);
    exit_section_(b, m, DROP_NODE_PROPERTY_EXISTENCE_CONSTRAINT, r);
    return r;
  }

  /* ********************************************************** */
  // K_DROP RelationshipPropertyExistenceConstraintSyntax
  public static boolean DropRelationshipPropertyExistenceConstraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DropRelationshipPropertyExistenceConstraint")) return false;
    if (!nextTokenIs(b, K_DROP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DROP);
    r = r && RelationshipPropertyExistenceConstraintSyntax(b, l + 1);
    exit_section_(b, m, DROP_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT, r);
    return r;
  }

  /* ********************************************************** */
  // K_DROP UniqueConstraintSyntax
  public static boolean DropUniqueConstraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DropUniqueConstraint")) return false;
    if (!nextTokenIs(b, K_DROP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DROP);
    r = r && UniqueConstraintSyntax(b, l + 1);
    exit_section_(b, m, DROP_UNIQUE_CONSTRAINT, r);
    return r;
  }

  /* ********************************************************** */
  // l_identifier_text
  public static boolean EscapedSymbolicNameString(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EscapedSymbolicNameString")) return false;
    if (!nextTokenIs(b, L_IDENTIFIER_TEXT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_IDENTIFIER_TEXT);
    exit_section_(b, m, ESCAPED_SYMBOLIC_NAME_STRING, r);
    return r;
  }

  /* ********************************************************** */
  // K_EXISTS "(" Expression ")"
  public static boolean ExistsFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExistsFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_EXISTS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_EXISTS, PARENTHESE_OPEN);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, EXISTS_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // K_EXPLAIN
  public static boolean Explain(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Explain")) return false;
    if (!nextTokenIs(b, K_EXPLAIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_EXPLAIN);
    exit_section_(b, m, EXPLAIN, r);
    return r;
  }

  /* ********************************************************** */
  // Expression12
  public static boolean Expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, EXPRESSION, "<expression>");
    r = Expression12(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NumberLiteral
  //               | StringLiteral
  //               | Parameter
  //               | (K_TRUE)
  //               | (K_FALSE)
  //               | (K_NULL)
  //               | CaseExpression
  //               | MapLiteral
  //               | MapProjection
  //               | CountStar
  //               | ListComprehension
  //               | PatternComprehension
  //               | ("[" Expression? ("," Expression)* "]")
  //               | FilterFunctionInvocation
  //               | ExtractFunctionInvocation
  //               | ReduceFunctionInvocation
  //               | AllFunctionInvocation
  //               | AnyFunctionInvocation
  //               | NoneFunctionInvocation
  //               | SingleFunctionInvocation
  //               | ExistsFunctionInvocation
  //               | ShortestPathPattern
  //               | RelationshipsPattern
  //               | parenthesizedExpression
  //               | FunctionInvocation
  //               | Variable
  static boolean Expression1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumberLiteral(b, l + 1);
    if (!r) r = StringLiteral(b, l + 1);
    if (!r) r = Parameter(b, l + 1);
    if (!r) r = consumeToken(b, K_TRUE);
    if (!r) r = consumeToken(b, K_FALSE);
    if (!r) r = consumeToken(b, K_NULL);
    if (!r) r = CaseExpression(b, l + 1);
    if (!r) r = MapLiteral(b, l + 1);
    if (!r) r = MapProjection(b, l + 1);
    if (!r) r = CountStar(b, l + 1);
    if (!r) r = ListComprehension(b, l + 1);
    if (!r) r = PatternComprehension(b, l + 1);
    if (!r) r = Expression1_12(b, l + 1);
    if (!r) r = FilterFunctionInvocation(b, l + 1);
    if (!r) r = ExtractFunctionInvocation(b, l + 1);
    if (!r) r = ReduceFunctionInvocation(b, l + 1);
    if (!r) r = AllFunctionInvocation(b, l + 1);
    if (!r) r = AnyFunctionInvocation(b, l + 1);
    if (!r) r = NoneFunctionInvocation(b, l + 1);
    if (!r) r = SingleFunctionInvocation(b, l + 1);
    if (!r) r = ExistsFunctionInvocation(b, l + 1);
    if (!r) r = ShortestPathPattern(b, l + 1);
    if (!r) r = RelationshipsPattern(b, l + 1);
    if (!r) r = parenthesizedExpression(b, l + 1);
    if (!r) r = FunctionInvocation(b, l + 1);
    if (!r) r = Variable(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "[" Expression? ("," Expression)* "]"
  private static boolean Expression1_12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression1_12")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_SQUAREOPEN);
    r = r && Expression1_12_1(b, l + 1);
    r = r && Expression1_12_2(b, l + 1);
    r = r && consumeToken(b, BRACKET_SQUARECLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expression?
  private static boolean Expression1_12_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression1_12_1")) return false;
    Expression(b, l + 1);
    return true;
  }

  // ("," Expression)*
  private static boolean Expression1_12_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression1_12_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression1_12_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression1_12_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Expression
  private static boolean Expression1_12_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression1_12_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression9 (K_AND Expression9)*
  static boolean Expression10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression10")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression9(b, l + 1);
    r = r && Expression10_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (K_AND Expression9)*
  private static boolean Expression10_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression10_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression10_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression10_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // K_AND Expression9
  private static boolean Expression10_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression10_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AND);
    r = r && Expression9(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression10 (K_XOR Expression10)*
  static boolean Expression11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression11")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression10(b, l + 1);
    r = r && Expression11_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (K_XOR Expression10)*
  private static boolean Expression11_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression11_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression11_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression11_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // K_XOR Expression10
  private static boolean Expression11_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression11_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_XOR);
    r = r && Expression10(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression11 (K_OR Expression11)*
  static boolean Expression12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression12")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression11(b, l + 1);
    r = r && Expression12_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (K_OR Expression11)*
  private static boolean Expression12_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression12_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression12_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression12_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // K_OR Expression11
  private static boolean Expression12_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression12_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_OR);
    r = r && Expression11(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression1 (PropertyLookup | NodeLabels)*
  static boolean Expression2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression1(b, l + 1);
    r = r && Expression2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PropertyLookup | NodeLabels)*
  private static boolean Expression2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression2_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression2_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression2_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // PropertyLookup | NodeLabels
  private static boolean Expression2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression2_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropertyLookup(b, l + 1);
    if (!r) r = NodeLabels(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression2 (
  //     ("[" Expression "]")
  //   | ("[" Expression? ".." Expression? "]")
  //   | ("=~" Expression2)
  //   | (K_IN Expression2)
  //   | (K_STARTS K_WITH  Expression2)
  //   | (K_ENDS K_WITH  Expression2)
  //   | (K_CONTAINS  Expression2)
  //   | (K_IS K_NULL)
  //   | (K_IS K_NOT K_NULL)
  // )*
  static boolean Expression3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression2(b, l + 1);
    r = r && Expression3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (
  //     ("[" Expression "]")
  //   | ("[" Expression? ".." Expression? "]")
  //   | ("=~" Expression2)
  //   | (K_IN Expression2)
  //   | (K_STARTS K_WITH  Expression2)
  //   | (K_ENDS K_WITH  Expression2)
  //   | (K_CONTAINS  Expression2)
  //   | (K_IS K_NULL)
  //   | (K_IS K_NOT K_NULL)
  // )*
  private static boolean Expression3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression3_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression3_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ("[" Expression "]")
  //   | ("[" Expression? ".." Expression? "]")
  //   | ("=~" Expression2)
  //   | (K_IN Expression2)
  //   | (K_STARTS K_WITH  Expression2)
  //   | (K_ENDS K_WITH  Expression2)
  //   | (K_CONTAINS  Expression2)
  //   | (K_IS K_NULL)
  //   | (K_IS K_NOT K_NULL)
  private static boolean Expression3_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression3_1_0_0(b, l + 1);
    if (!r) r = Expression3_1_0_1(b, l + 1);
    if (!r) r = Expression3_1_0_2(b, l + 1);
    if (!r) r = Expression3_1_0_3(b, l + 1);
    if (!r) r = Expression3_1_0_4(b, l + 1);
    if (!r) r = Expression3_1_0_5(b, l + 1);
    if (!r) r = Expression3_1_0_6(b, l + 1);
    if (!r) r = Expression3_1_0_7(b, l + 1);
    if (!r) r = Expression3_1_0_8(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "[" Expression "]"
  private static boolean Expression3_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_SQUAREOPEN);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, BRACKET_SQUARECLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // "[" Expression? ".." Expression? "]"
  private static boolean Expression3_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_SQUAREOPEN);
    r = r && Expression3_1_0_1_1(b, l + 1);
    r = r && consumeToken(b, OP_RANGE);
    r = r && Expression3_1_0_1_3(b, l + 1);
    r = r && consumeToken(b, BRACKET_SQUARECLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expression?
  private static boolean Expression3_1_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_1_1")) return false;
    Expression(b, l + 1);
    return true;
  }

  // Expression?
  private static boolean Expression3_1_0_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_1_3")) return false;
    Expression(b, l + 1);
    return true;
  }

  // "=~" Expression2
  private static boolean Expression3_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_REGEXMATCH);
    r = r && Expression2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_IN Expression2
  private static boolean Expression3_1_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_IN);
    r = r && Expression2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_STARTS K_WITH  Expression2
  private static boolean Expression3_1_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_STARTS, K_WITH);
    r = r && Expression2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_ENDS K_WITH  Expression2
  private static boolean Expression3_1_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ENDS, K_WITH);
    r = r && Expression2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_CONTAINS  Expression2
  private static boolean Expression3_1_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CONTAINS);
    r = r && Expression2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_IS K_NULL
  private static boolean Expression3_1_0_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_IS, K_NULL);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_IS K_NOT K_NULL
  private static boolean Expression3_1_0_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression3_1_0_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_IS, K_NOT, K_NULL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression3 | ("-" Expression4) | ("+" Expression4)
  static boolean Expression4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression3(b, l + 1);
    if (!r) r = Expression4_1(b, l + 1);
    if (!r) r = Expression4_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "-" Expression4
  private static boolean Expression4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression4_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_MINUS);
    r = r && Expression4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "+" Expression4
  private static boolean Expression4_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression4_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_PLUS);
    r = r && Expression4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression4 ("^" Expression4)*
  static boolean Expression5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression4(b, l + 1);
    r = r && Expression5_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("^" Expression4)*
  private static boolean Expression5_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression5_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression5_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression5_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "^" Expression4
  private static boolean Expression5_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression5_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_POW);
    r = r && Expression4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression5 (("*" Expression5) | ("/" Expression5) | ("%" Expression5))*
  static boolean Expression6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression5(b, l + 1);
    r = r && Expression6_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (("*" Expression5) | ("/" Expression5) | ("%" Expression5))*
  private static boolean Expression6_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression6_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression6_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression6_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ("*" Expression5) | ("/" Expression5) | ("%" Expression5)
  private static boolean Expression6_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression6_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression6_1_0_0(b, l + 1);
    if (!r) r = Expression6_1_0_1(b, l + 1);
    if (!r) r = Expression6_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "*" Expression5
  private static boolean Expression6_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression6_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_MUL);
    r = r && Expression5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "/" Expression5
  private static boolean Expression6_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression6_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_DIVIDE);
    r = r && Expression5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "%" Expression5
  private static boolean Expression6_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression6_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_MODULO);
    r = r && Expression5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression6 (("-" Expression6) | ("+" Expression6))*
  static boolean Expression7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression6(b, l + 1);
    r = r && Expression7_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (("-" Expression6) | ("+" Expression6))*
  private static boolean Expression7_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression7_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression7_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression7_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ("-" Expression6) | ("+" Expression6)
  private static boolean Expression7_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression7_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression7_1_0_0(b, l + 1);
    if (!r) r = Expression7_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "-" Expression6
  private static boolean Expression7_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression7_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_MINUS);
    r = r && Expression6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "+" Expression6
  private static boolean Expression7_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression7_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_PLUS);
    r = r && Expression6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression7 PartialComparisonExpression*
  static boolean Expression8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression7(b, l + 1);
    r = r && Expression8_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PartialComparisonExpression*
  private static boolean Expression8_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression8_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!PartialComparisonExpression(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression8_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (K_NOT Expression9) | Expression8
  static boolean Expression9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression9")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression9_0(b, l + 1);
    if (!r) r = Expression8(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_NOT Expression9
  private static boolean Expression9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_NOT);
    r = r && Expression9(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_EXTRACT "(" FilterExpression ("|" Expression)? ")"
  public static boolean ExtractFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtractFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_EXTRACT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_EXTRACT, PARENTHESE_OPEN);
    r = r && FilterExpression(b, l + 1);
    r = r && ExtractFunctionInvocation_3(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, EXTRACT_FUNCTION_INVOCATION, r);
    return r;
  }

  // ("|" Expression)?
  private static boolean ExtractFunctionInvocation_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtractFunctionInvocation_3")) return false;
    ExtractFunctionInvocation_3_0(b, l + 1);
    return true;
  }

  // "|" Expression
  private static boolean ExtractFunctionInvocation_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtractFunctionInvocation_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_PIPE);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IdInColl Where?
  public static boolean FilterExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FilterExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FILTER_EXPRESSION, "<filter expression>");
    r = IdInColl(b, l + 1);
    r = r && FilterExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Where?
  private static boolean FilterExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FilterExpression_1")) return false;
    Where(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // K_FILTER "(" FilterExpression ")"
  public static boolean FilterFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FilterFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_FILTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_FILTER, PARENTHESE_OPEN);
    r = r && FilterExpression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, FILTER_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // K_FOREACH "(" Variable K_IN Expression "|" Clause+ ")"
  public static boolean Foreach(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Foreach")) return false;
    if (!nextTokenIs(b, K_FOREACH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_FOREACH, PARENTHESE_OPEN);
    r = r && Variable(b, l + 1);
    r = r && consumeToken(b, K_IN);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, OP_PIPE);
    r = r && Foreach_6(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, FOREACH, r);
    return r;
  }

  // Clause+
  private static boolean Foreach_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Foreach_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Clause(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!Clause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Foreach_6", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "(" K_DISTINCT? Expression? ("," Expression)* ")"
  public static boolean FunctionArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArguments")) return false;
    if (!nextTokenIs(b, PARENTHESE_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARENTHESE_OPEN);
    r = r && FunctionArguments_1(b, l + 1);
    r = r && FunctionArguments_2(b, l + 1);
    r = r && FunctionArguments_3(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, FUNCTION_ARGUMENTS, r);
    return r;
  }

  // K_DISTINCT?
  private static boolean FunctionArguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArguments_1")) return false;
    consumeToken(b, K_DISTINCT);
    return true;
  }

  // Expression?
  private static boolean FunctionArguments_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArguments_2")) return false;
    Expression(b, l + 1);
    return true;
  }

  // ("," Expression)*
  private static boolean FunctionArguments_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArguments_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!FunctionArguments_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionArguments_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Expression
  private static boolean FunctionArguments_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArguments_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (FunctionInvocationBody "(" "*" ")")
  //                      | (FunctionInvocationBody FunctionArguments)
  public static boolean FunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionInvocation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_INVOCATION, "<function invocation>");
    r = FunctionInvocation_0(b, l + 1);
    if (!r) r = FunctionInvocation_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FunctionInvocationBody "(" "*" ")"
  private static boolean FunctionInvocation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionInvocation_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionInvocationBody(b, l + 1);
    r = r && consumeTokens(b, 0, PARENTHESE_OPEN, OP_MUL, PARENTHESE_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // FunctionInvocationBody FunctionArguments
  private static boolean FunctionInvocation_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionInvocation_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionInvocationBody(b, l + 1);
    r = r && FunctionArguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Namespace FunctionName
  public static boolean FunctionInvocationBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionInvocationBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_INVOCATION_BODY, "<function invocation body>");
    r = Namespace(b, l + 1);
    r = r && FunctionName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // UnescapedSymbolicNameString | EscapedSymbolicNameString | K_COUNT
  public static boolean FunctionName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_NAME, "<function name>");
    r = UnescapedSymbolicNameString(b, l + 1);
    if (!r) r = EscapedSymbolicNameString(b, l + 1);
    if (!r) r = consumeToken(b, K_COUNT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (K_USING K_INDEX Variable NodeLabel "(" PropertyKeyName ")")
  //        | (K_USING K_JOIN K_ON Variable ("," Variable)*)
  //        | (K_USING K_SCAN Variable NodeLabel)
  public static boolean Hint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Hint")) return false;
    if (!nextTokenIs(b, K_USING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Hint_0(b, l + 1);
    if (!r) r = Hint_1(b, l + 1);
    if (!r) r = Hint_2(b, l + 1);
    exit_section_(b, m, HINT, r);
    return r;
  }

  // K_USING K_INDEX Variable NodeLabel "(" PropertyKeyName ")"
  private static boolean Hint_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Hint_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_USING, K_INDEX);
    r = r && Variable(b, l + 1);
    r = r && NodeLabel(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_OPEN);
    r = r && PropertyKeyName(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_USING K_JOIN K_ON Variable ("," Variable)*
  private static boolean Hint_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Hint_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_USING, K_JOIN, K_ON);
    r = r && Variable(b, l + 1);
    r = r && Hint_1_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," Variable)*
  private static boolean Hint_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Hint_1_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Hint_1_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Hint_1_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Variable
  private static boolean Hint_1_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Hint_1_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && Variable(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_USING K_SCAN Variable NodeLabel
  private static boolean Hint_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Hint_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_USING, K_SCAN);
    r = r && Variable(b, l + 1);
    r = r && NodeLabel(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Variable K_IN Expression
  public static boolean IdInColl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdInColl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ID_IN_COLL, "<id in coll>");
    r = Variable(b, l + 1);
    r = r && consumeToken(b, K_IN);
    r = r && Expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "(" (LiteralIds | Parameter | "*") ")"
  public static boolean IdLookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdLookup")) return false;
    if (!nextTokenIs(b, PARENTHESE_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARENTHESE_OPEN);
    r = r && IdLookup_1(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, ID_LOOKUP, r);
    return r;
  }

  // LiteralIds | Parameter | "*"
  private static boolean IdLookup_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdLookup_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LiteralIds(b, l + 1);
    if (!r) r = Parameter(b, l + 1);
    if (!r) r = consumeToken(b, OP_MUL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ":" SymbolicNameString "(" SymbolicNameString "=" (StringLiteral | Parameter) ")"
  public static boolean IdentifiedIndexLookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifiedIndexLookup")) return false;
    if (!nextTokenIs(b, OP_COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COLON);
    r = r && SymbolicNameString(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_OPEN);
    r = r && SymbolicNameString(b, l + 1);
    r = r && consumeToken(b, OP_EQUAL);
    r = r && IdentifiedIndexLookup_5(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, IDENTIFIED_INDEX_LOOKUP, r);
    return r;
  }

  // StringLiteral | Parameter
  private static boolean IdentifiedIndexLookup_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifiedIndexLookup_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteral(b, l + 1);
    if (!r) r = Parameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ":" SymbolicNameString "(" (StringLiteral | Parameter) ")"
  public static boolean IndexQuery(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexQuery")) return false;
    if (!nextTokenIs(b, OP_COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COLON);
    r = r && SymbolicNameString(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_OPEN);
    r = r && IndexQuery_3(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, INDEX_QUERY, r);
    return r;
  }

  // StringLiteral | Parameter
  private static boolean IndexQuery_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexQuery_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteral(b, l + 1);
    if (!r) r = Parameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SymbolicNameString
  public static boolean LabelName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL_NAME, "<label name>");
    r = SymbolicNameString(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "<"
  public static boolean LeftArrowHead(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LeftArrowHead")) return false;
    if (!nextTokenIs(b, OP_LESSTHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_LESSTHEN);
    exit_section_(b, m, LEFT_ARROW_HEAD, r);
    return r;
  }

  /* ********************************************************** */
  // K_LIMIT Expression
  public static boolean Limit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Limit")) return false;
    if (!nextTokenIs(b, K_LIMIT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_LIMIT);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, LIMIT, r);
    return r;
  }

  /* ********************************************************** */
  // "[" FilterExpression ("|"  Expression)? "]"
  public static boolean ListComprehension(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListComprehension")) return false;
    if (!nextTokenIs(b, BRACKET_SQUAREOPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_SQUAREOPEN);
    r = r && FilterExpression(b, l + 1);
    r = r && ListComprehension_2(b, l + 1);
    r = r && consumeToken(b, BRACKET_SQUARECLOSE);
    exit_section_(b, m, LIST_COMPREHENSION, r);
    return r;
  }

  // ("|"  Expression)?
  private static boolean ListComprehension_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListComprehension_2")) return false;
    ListComprehension_2_0(b, l + 1);
    return true;
  }

  // "|"  Expression
  private static boolean ListComprehension_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListComprehension_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_PIPE);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PropertyKeyName ":" Expression
  public static boolean LiteralEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralEntry")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_ENTRY, "<literal entry>");
    r = PropertyKeyName(b, l + 1);
    r = r && consumeToken(b, OP_COLON);
    r = r && Expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // UnsignedIntegerLiteral ("," UnsignedIntegerLiteral)*
  public static boolean LiteralIds(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralIds")) return false;
    if (!nextTokenIs(b, L_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnsignedIntegerLiteral(b, l + 1);
    r = r && LiteralIds_1(b, l + 1);
    exit_section_(b, m, LITERAL_IDS, r);
    return r;
  }

  // ("," UnsignedIntegerLiteral)*
  private static boolean LiteralIds_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralIds_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!LiteralIds_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "LiteralIds_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," UnsignedIntegerLiteral
  private static boolean LiteralIds_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralIds_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && UnsignedIntegerLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_LOAD K_CSV (K_WITH K_HEADERS)? K_FROM Expression K_AS Variable (K_FIELDTERMINATOR StringLiteral)?
  public static boolean LoadCSV(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoadCSV")) return false;
    if (!nextTokenIs(b, K_LOAD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_LOAD, K_CSV);
    r = r && LoadCSV_2(b, l + 1);
    r = r && consumeToken(b, K_FROM);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, K_AS);
    r = r && Variable(b, l + 1);
    r = r && LoadCSV_7(b, l + 1);
    exit_section_(b, m, LOAD_CSV, r);
    return r;
  }

  // (K_WITH K_HEADERS)?
  private static boolean LoadCSV_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoadCSV_2")) return false;
    LoadCSV_2_0(b, l + 1);
    return true;
  }

  // K_WITH K_HEADERS
  private static boolean LoadCSV_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoadCSV_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_WITH, K_HEADERS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (K_FIELDTERMINATOR StringLiteral)?
  private static boolean LoadCSV_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoadCSV_7")) return false;
    LoadCSV_7_0(b, l + 1);
    return true;
  }

  // K_FIELDTERMINATOR StringLiteral
  private static boolean LoadCSV_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoadCSV_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_FIELDTERMINATOR);
    r = r && StringLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LoadCSV Clause*
  public static boolean LoadCSVQuery(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoadCSVQuery")) return false;
    if (!nextTokenIs(b, K_LOAD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LoadCSV(b, l + 1);
    r = r && LoadCSVQuery_1(b, l + 1);
    exit_section_(b, m, LOAD_CSV_QUERY, r);
    return r;
  }

  // Clause*
  private static boolean LoadCSVQuery_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoadCSVQuery_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Clause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "LoadCSVQuery_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // NodeLookup | RelationshipLookup
  public static boolean Lookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Lookup")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LOOKUP, "<lookup>");
    r = NodeLookup(b, l + 1);
    if (!r) r = RelationshipLookup(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "{" (PropertyKeyName ":" Expression)? ("," PropertyKeyName ":" Expression)* "}"
  public static boolean MapLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapLiteral")) return false;
    if (!nextTokenIs(b, BRACKET_CURLYOPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_CURLYOPEN);
    r = r && MapLiteral_1(b, l + 1);
    r = r && MapLiteral_2(b, l + 1);
    r = r && consumeToken(b, BRACKET_CURLYCLOSE);
    exit_section_(b, m, MAP_LITERAL, r);
    return r;
  }

  // (PropertyKeyName ":" Expression)?
  private static boolean MapLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapLiteral_1")) return false;
    MapLiteral_1_0(b, l + 1);
    return true;
  }

  // PropertyKeyName ":" Expression
  private static boolean MapLiteral_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapLiteral_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropertyKeyName(b, l + 1);
    r = r && consumeToken(b, OP_COLON);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," PropertyKeyName ":" Expression)*
  private static boolean MapLiteral_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapLiteral_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MapLiteral_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MapLiteral_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," PropertyKeyName ":" Expression
  private static boolean MapLiteral_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapLiteral_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && PropertyKeyName(b, l + 1);
    r = r && consumeToken(b, OP_COLON);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Variable "{" MapProjectionVariants? ("," MapProjectionVariants)* "}"
  public static boolean MapProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapProjection")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAP_PROJECTION, "<map projection>");
    r = Variable(b, l + 1);
    r = r && consumeToken(b, BRACKET_CURLYOPEN);
    r = r && MapProjection_2(b, l + 1);
    r = r && MapProjection_3(b, l + 1);
    r = r && consumeToken(b, BRACKET_CURLYCLOSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // MapProjectionVariants?
  private static boolean MapProjection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapProjection_2")) return false;
    MapProjectionVariants(b, l + 1);
    return true;
  }

  // ("," MapProjectionVariants)*
  private static boolean MapProjection_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapProjection_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MapProjection_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MapProjection_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," MapProjectionVariants
  private static boolean MapProjection_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapProjection_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && MapProjectionVariants(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LiteralEntry | PropertySelector | VariableSelector | AllPropertiesSelector
  public static boolean MapProjectionVariants(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapProjectionVariants")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAP_PROJECTION_VARIANTS, "<map projection variants>");
    r = LiteralEntry(b, l + 1);
    if (!r) r = PropertySelector(b, l + 1);
    if (!r) r = VariableSelector(b, l + 1);
    if (!r) r = AllPropertiesSelector(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ((K_OPTIONAL K_MATCH) | K_MATCH) Pattern Hint* Where?
  public static boolean Match(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Match")) return false;
    if (!nextTokenIs(b, "<match>", K_MATCH, K_OPTIONAL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MATCH, "<match>");
    r = Match_0(b, l + 1);
    r = r && Pattern(b, l + 1);
    r = r && Match_2(b, l + 1);
    r = r && Match_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (K_OPTIONAL K_MATCH) | K_MATCH
  private static boolean Match_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Match_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Match_0_0(b, l + 1);
    if (!r) r = consumeToken(b, K_MATCH);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_OPTIONAL K_MATCH
  private static boolean Match_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Match_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_OPTIONAL, K_MATCH);
    exit_section_(b, m, null, r);
    return r;
  }

  // Hint*
  private static boolean Match_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Match_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Hint(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Match_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Where?
  private static boolean Match_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Match_3")) return false;
    Where(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ("*" RangeLiteral?)?
  public static boolean MaybeVariableLength(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MaybeVariableLength")) return false;
    Marker m = enter_section_(b, l, _NONE_, MAYBE_VARIABLE_LENGTH, "<maybe variable length>");
    MaybeVariableLength_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // "*" RangeLiteral?
  private static boolean MaybeVariableLength_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MaybeVariableLength_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_MUL);
    r = r && MaybeVariableLength_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // RangeLiteral?
  private static boolean MaybeVariableLength_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MaybeVariableLength_0_1")) return false;
    RangeLiteral(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // K_MERGE PatternPart MergeAction*
  public static boolean Merge(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Merge")) return false;
    if (!nextTokenIs(b, K_MERGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_MERGE);
    r = r && PatternPart(b, l + 1);
    r = r && Merge_2(b, l + 1);
    exit_section_(b, m, MERGE, r);
    return r;
  }

  // MergeAction*
  private static boolean Merge_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Merge_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MergeAction(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Merge_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (K_ON K_MATCH SetClause)
  //               | (K_ON K_CREATE SetClause)
  public static boolean MergeAction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MergeAction")) return false;
    if (!nextTokenIs(b, K_ON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MergeAction_0(b, l + 1);
    if (!r) r = MergeAction_1(b, l + 1);
    exit_section_(b, m, MERGE_ACTION, r);
    return r;
  }

  // K_ON K_MATCH SetClause
  private static boolean MergeAction_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MergeAction_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ON, K_MATCH);
    r = r && SetClause(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_ON K_CREATE SetClause
  private static boolean MergeAction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MergeAction_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ON, K_CREATE);
    r = r && SetClause(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (SymbolicNameString ".")*
  public static boolean Namespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Namespace")) return false;
    Marker m = enter_section_(b, l, _NONE_, NAMESPACE, "<namespace>");
    int c = current_position_(b);
    while (true) {
      if (!Namespace_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Namespace", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // SymbolicNameString "."
  private static boolean Namespace_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Namespace_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SymbolicNameString(b, l + 1);
    r = r && consumeToken(b, OP_DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "$" (SymbolicNameString | UnsignedDecimalInteger)
  public static boolean NewParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewParameter")) return false;
    if (!nextTokenIs(b, DOLLAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR);
    r = r && NewParameter_1(b, l + 1);
    exit_section_(b, m, NEW_PARAMETER, r);
    return r;
  }

  // SymbolicNameString | UnsignedDecimalInteger
  private static boolean NewParameter_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewParameter_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SymbolicNameString(b, l + 1);
    if (!r) r = UnsignedDecimalInteger(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ":" LabelName
  public static boolean NodeLabel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodeLabel")) return false;
    if (!nextTokenIs(b, OP_COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COLON);
    r = r && LabelName(b, l + 1);
    exit_section_(b, m, NODE_LABEL, r);
    return r;
  }

  /* ********************************************************** */
  // NodeLabel+
  public static boolean NodeLabels(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodeLabels")) return false;
    if (!nextTokenIs(b, OP_COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NodeLabel(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!NodeLabel(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NodeLabels", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, NODE_LABELS, r);
    return r;
  }

  /* ********************************************************** */
  // K_NODE (IdentifiedIndexLookup | IndexQuery | IdLookup)
  public static boolean NodeLookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodeLookup")) return false;
    if (!nextTokenIs(b, K_NODE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_NODE);
    r = r && NodeLookup_1(b, l + 1);
    exit_section_(b, m, NODE_LOOKUP, r);
    return r;
  }

  // IdentifiedIndexLookup | IndexQuery | IdLookup
  private static boolean NodeLookup_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodeLookup_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IdentifiedIndexLookup(b, l + 1);
    if (!r) r = IndexQuery(b, l + 1);
    if (!r) r = IdLookup(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "(" Variable? NodeLabels? Properties? ")"
  public static boolean NodePattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodePattern")) return false;
    if (!nextTokenIs(b, PARENTHESE_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NODE_PATTERN, null);
    r = consumeToken(b, PARENTHESE_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, NodePattern_1(b, l + 1));
    r = p && report_error_(b, NodePattern_2(b, l + 1)) && r;
    r = p && report_error_(b, NodePattern_3(b, l + 1)) && r;
    r = p && consumeToken(b, PARENTHESE_CLOSE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Variable?
  private static boolean NodePattern_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodePattern_1")) return false;
    Variable(b, l + 1);
    return true;
  }

  // NodeLabels?
  private static boolean NodePattern_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodePattern_2")) return false;
    NodeLabels(b, l + 1);
    return true;
  }

  // Properties?
  private static boolean NodePattern_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodePattern_3")) return false;
    Properties(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // K_CONSTRAINT K_ON "(" Variable NodeLabel ")" K_ASSERT K_EXISTS "(" PropertyExpression ")"
  public static boolean NodePropertyExistenceConstraintSyntax(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodePropertyExistenceConstraintSyntax")) return false;
    if (!nextTokenIs(b, K_CONSTRAINT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_CONSTRAINT, K_ON, PARENTHESE_OPEN);
    r = r && Variable(b, l + 1);
    r = r && NodeLabel(b, l + 1);
    r = r && consumeTokens(b, 0, PARENTHESE_CLOSE, K_ASSERT, K_EXISTS, PARENTHESE_OPEN);
    r = r && PropertyExpression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, NODE_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX, r);
    return r;
  }

  /* ********************************************************** */
  // K_NONE "(" FilterExpression ")"
  public static boolean NoneFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NoneFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_NONE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_NONE, PARENTHESE_OPEN);
    r = r && FilterExpression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, NONE_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // DoubleLiteral | SignedIntegerLiteral
  public static boolean NumberLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral")) return false;
    if (!nextTokenIs(b, "<number literal>", L_DECIMAL, L_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMBER_LITERAL, "<number literal>");
    r = DoubleLiteral(b, l + 1);
    if (!r) r = SignedIntegerLiteral(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "{" (SymbolicNameString | UnsignedDecimalInteger) "}"
  public static boolean OldParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OldParameter")) return false;
    if (!nextTokenIs(b, BRACKET_CURLYOPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_CURLYOPEN);
    r = r && OldParameter_1(b, l + 1);
    r = r && consumeToken(b, BRACKET_CURLYCLOSE);
    exit_section_(b, m, OLD_PARAMETER, r);
    return r;
  }

  // SymbolicNameString | UnsignedDecimalInteger
  private static boolean OldParameter_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OldParameter_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SymbolicNameString(b, l + 1);
    if (!r) r = UnsignedDecimalInteger(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_ORDER K_BY SortItem ("," SortItem)*
  public static boolean Order(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Order")) return false;
    if (!nextTokenIs(b, K_ORDER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ORDER, K_BY);
    r = r && SortItem(b, l + 1);
    r = r && Order_3(b, l + 1);
    exit_section_(b, m, ORDER, r);
    return r;
  }

  // ("," SortItem)*
  private static boolean Order_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Order_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Order_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Order_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," SortItem
  private static boolean Order_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Order_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && SortItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NewParameter | OldParameter
  public static boolean Parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parameter")) return false;
    if (!nextTokenIs(b, "<parameter>", DOLLAR, BRACKET_CURLYOPEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER, "<parameter>");
    r = NewParameter(b, l + 1);
    if (!r) r = OldParameter(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ("=" Expression7)
  //                                       | ("<>" Expression7)
  //                                       | ("!=" Expression7)
  //                                       | ("<" Expression7)
  //                                       | (">" Expression7)
  //                                       | ("<=" Expression7)
  //                                       | (">=" Expression7)
  static boolean PartialComparisonExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialComparisonExpression")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PartialComparisonExpression_0(b, l + 1);
    if (!r) r = PartialComparisonExpression_1(b, l + 1);
    if (!r) r = PartialComparisonExpression_2(b, l + 1);
    if (!r) r = PartialComparisonExpression_3(b, l + 1);
    if (!r) r = PartialComparisonExpression_4(b, l + 1);
    if (!r) r = PartialComparisonExpression_5(b, l + 1);
    if (!r) r = PartialComparisonExpression_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "=" Expression7
  private static boolean PartialComparisonExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialComparisonExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_EQUAL);
    r = r && Expression7(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "<>" Expression7
  private static boolean PartialComparisonExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialComparisonExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_INVALIDNOTEQUALS);
    r = r && Expression7(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "!=" Expression7
  private static boolean PartialComparisonExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialComparisonExpression_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_NOTEQUALS);
    r = r && Expression7(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "<" Expression7
  private static boolean PartialComparisonExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialComparisonExpression_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_LESSTHEN);
    r = r && Expression7(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ">" Expression7
  private static boolean PartialComparisonExpression_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialComparisonExpression_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_GREATHERTHEN);
    r = r && Expression7(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "<=" Expression7
  private static boolean PartialComparisonExpression_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialComparisonExpression_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_LESSTHANEQUALS);
    r = r && Expression7(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ">=" Expression7
  private static boolean PartialComparisonExpression_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialComparisonExpression_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_GREATERTHANEQUALS);
    r = r && Expression7(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PatternPart ("," PatternPart)*
  public static boolean Pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PATTERN, "<pattern>");
    r = PatternPart(b, l + 1);
    r = r && Pattern_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("," PatternPart)*
  private static boolean Pattern_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pattern_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Pattern_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Pattern_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," PatternPart
  private static boolean Pattern_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pattern_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && PatternPart(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "[" RelationshipsPattern Where? "|" Expression "]"
  public static boolean PatternComprehension(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternComprehension")) return false;
    if (!nextTokenIs(b, BRACKET_SQUAREOPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_SQUAREOPEN);
    r = r && RelationshipsPattern(b, l + 1);
    r = r && PatternComprehension_2(b, l + 1);
    r = r && consumeToken(b, OP_PIPE);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, BRACKET_SQUARECLOSE);
    exit_section_(b, m, PATTERN_COMPREHENSION, r);
    return r;
  }

  // Where?
  private static boolean PatternComprehension_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternComprehension_2")) return false;
    Where(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (NodePattern PatternElementChain*) | ("(" PatternElement ")")
  public static boolean PatternElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternElement")) return false;
    if (!nextTokenIs(b, PARENTHESE_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PatternElement_0(b, l + 1);
    if (!r) r = PatternElement_1(b, l + 1);
    exit_section_(b, m, PATTERN_ELEMENT, r);
    return r;
  }

  // NodePattern PatternElementChain*
  private static boolean PatternElement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternElement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NodePattern(b, l + 1);
    r = r && PatternElement_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PatternElementChain*
  private static boolean PatternElement_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternElement_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!PatternElementChain(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PatternElement_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "(" PatternElement ")"
  private static boolean PatternElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternElement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARENTHESE_OPEN);
    r = r && PatternElement(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // RelationshipPattern NodePattern
  public static boolean PatternElementChain(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternElementChain")) return false;
    if (!nextTokenIs(b, "<pattern element chain>", OP_MINUS, OP_LESSTHEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PATTERN_ELEMENT_CHAIN, "<pattern element chain>");
    r = RelationshipPattern(b, l + 1);
    p = r; // pin = 1
    r = r && NodePattern(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (Variable "=" AnonymousPatternPart) | AnonymousPatternPart
  public static boolean PatternPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternPart")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PATTERN_PART, "<pattern part>");
    r = PatternPart_0(b, l + 1);
    if (!r) r = AnonymousPatternPart(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Variable "=" AnonymousPatternPart
  private static boolean PatternPart_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PatternPart_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Variable(b, l + 1);
    r = r && consumeToken(b, OP_EQUAL);
    r = r && AnonymousPatternPart(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_USING K_PERIODIC K_COMMIT SignedIntegerLiteral?
  public static boolean PeriodicCommitHint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PeriodicCommitHint")) return false;
    if (!nextTokenIs(b, K_USING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_USING, K_PERIODIC, K_COMMIT);
    r = r && PeriodicCommitHint_3(b, l + 1);
    exit_section_(b, m, PERIODIC_COMMIT_HINT, r);
    return r;
  }

  // SignedIntegerLiteral?
  private static boolean PeriodicCommitHint_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PeriodicCommitHint_3")) return false;
    SignedIntegerLiteral(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "(" Expression? ("," Expression)* ")"
  public static boolean ProcedureArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureArguments")) return false;
    if (!nextTokenIs(b, PARENTHESE_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARENTHESE_OPEN);
    r = r && ProcedureArguments_1(b, l + 1);
    r = r && ProcedureArguments_2(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, PROCEDURE_ARGUMENTS, r);
    return r;
  }

  // Expression?
  private static boolean ProcedureArguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureArguments_1")) return false;
    Expression(b, l + 1);
    return true;
  }

  // ("," Expression)*
  private static boolean ProcedureArguments_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureArguments_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ProcedureArguments_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ProcedureArguments_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Expression
  private static boolean ProcedureArguments_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureArguments_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ProcedureInvocationBody ProcedureArguments
  public static boolean ProcedureInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureInvocation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROCEDURE_INVOCATION, "<procedure invocation>");
    r = ProcedureInvocationBody(b, l + 1);
    r = r && ProcedureArguments(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Namespace ProcedureName
  public static boolean ProcedureInvocationBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureInvocationBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROCEDURE_INVOCATION_BODY, "<procedure invocation body>");
    r = Namespace(b, l + 1);
    r = r && ProcedureName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SymbolicNameString
  public static boolean ProcedureName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROCEDURE_NAME, "<procedure name>");
    r = SymbolicNameString(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SymbolicNameString
  public static boolean ProcedureOutput(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureOutput")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROCEDURE_OUTPUT, "<procedure output>");
    r = SymbolicNameString(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AliasedProcedureResult | SimpleProcedureResult
  public static boolean ProcedureResult(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureResult")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROCEDURE_RESULT, "<procedure result>");
    r = AliasedProcedureResult(b, l + 1);
    if (!r) r = SimpleProcedureResult(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // K_YIELD ProcedureResult ("," ProcedureResult)*
  public static boolean ProcedureResults(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureResults")) return false;
    if (!nextTokenIs(b, K_YIELD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_YIELD);
    r = r && ProcedureResult(b, l + 1);
    r = r && ProcedureResults_2(b, l + 1);
    exit_section_(b, m, PROCEDURE_RESULTS, r);
    return r;
  }

  // ("," ProcedureResult)*
  private static boolean ProcedureResults_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureResults_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ProcedureResults_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ProcedureResults_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," ProcedureResult
  private static boolean ProcedureResults_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProcedureResults_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && ProcedureResult(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_PROFILE
  public static boolean Profile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Profile")) return false;
    if (!nextTokenIs(b, K_PROFILE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_PROFILE);
    exit_section_(b, m, PROFILE, r);
    return r;
  }

  /* ********************************************************** */
  // MapLiteral | Parameter
  public static boolean Properties(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Properties")) return false;
    if (!nextTokenIs(b, "<properties>", DOLLAR, BRACKET_CURLYOPEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTIES, "<properties>");
    r = MapLiteral(b, l + 1);
    if (!r) r = Parameter(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Expression1 (PropertyLookup)+
  public static boolean PropertyExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropertyExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY_EXPRESSION, "<property expression>");
    r = Expression1(b, l + 1);
    r = r && PropertyExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (PropertyLookup)+
  private static boolean PropertyExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropertyExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropertyExpression_1_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!PropertyExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PropertyExpression_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (PropertyLookup)
  private static boolean PropertyExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropertyExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropertyLookup(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SymbolicNameString
  public static boolean PropertyKeyName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropertyKeyName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY_KEY_NAME, "<property key name>");
    r = SymbolicNameString(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "." PropertyKeyName
  public static boolean PropertyLookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropertyLookup")) return false;
    if (!nextTokenIs(b, OP_DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_DOT);
    r = r && PropertyKeyName(b, l + 1);
    exit_section_(b, m, PROPERTY_LOOKUP, r);
    return r;
  }

  /* ********************************************************** */
  // "." Variable
  public static boolean PropertySelector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropertySelector")) return false;
    if (!nextTokenIs(b, OP_DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_DOT);
    r = r && Variable(b, l + 1);
    exit_section_(b, m, PROPERTY_SELECTOR, r);
    return r;
  }

  /* ********************************************************** */
  // RegularQuery | BulkImportQuery
  public static boolean Query(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Query")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QUERY, "<query>");
    r = RegularQuery(b, l + 1);
    if (!r) r = BulkImportQuery(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AnyCypherOption*
  public static boolean QueryOptions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QueryOptions")) return false;
    Marker m = enter_section_(b, l, _NONE_, QUERY_OPTIONS, "<query options>");
    int c = current_position_(b);
    while (true) {
      if (!AnyCypherOption(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "QueryOptions", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // (UnsignedIntegerLiteral? ".." UnsignedIntegerLiteral?) | (UnsignedIntegerLiteral)
  public static boolean RangeLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeLiteral")) return false;
    if (!nextTokenIs(b, "<range literal>", OP_RANGE, L_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RANGE_LITERAL, "<range literal>");
    r = RangeLiteral_0(b, l + 1);
    if (!r) r = RangeLiteral_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // UnsignedIntegerLiteral? ".." UnsignedIntegerLiteral?
  private static boolean RangeLiteral_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeLiteral_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = RangeLiteral_0_0(b, l + 1);
    r = r && consumeToken(b, OP_RANGE);
    r = r && RangeLiteral_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // UnsignedIntegerLiteral?
  private static boolean RangeLiteral_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeLiteral_0_0")) return false;
    UnsignedIntegerLiteral(b, l + 1);
    return true;
  }

  // UnsignedIntegerLiteral?
  private static boolean RangeLiteral_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeLiteral_0_2")) return false;
    UnsignedIntegerLiteral(b, l + 1);
    return true;
  }

  // (UnsignedIntegerLiteral)
  private static boolean RangeLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeLiteral_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnsignedIntegerLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_REDUCE "(" Variable "=" Expression "," IdInColl "|" Expression ")"
  public static boolean ReduceFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReduceFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_REDUCE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_REDUCE, PARENTHESE_OPEN);
    r = r && Variable(b, l + 1);
    r = r && consumeToken(b, OP_EQUAL);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, OP_COMMA);
    r = r && IdInColl(b, l + 1);
    r = r && consumeToken(b, OP_PIPE);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, REDUCE_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // SingleQuery Union*
  public static boolean RegularQuery(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegularQuery")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REGULAR_QUERY, "<regular query>");
    r = SingleQuery(b, l + 1);
    r = r && RegularQuery_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Union*
  private static boolean RegularQuery_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegularQuery_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Union(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "RegularQuery_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // ":" RelTypeName
  public static boolean RelType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelType")) return false;
    if (!nextTokenIs(b, OP_COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COLON);
    r = r && RelTypeName(b, l + 1);
    exit_section_(b, m, REL_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // SymbolicNameString
  public static boolean RelTypeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelTypeName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REL_TYPE_NAME, "<rel type name>");
    r = SymbolicNameString(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "[" Variable? "?"? RelationshipTypes? MaybeVariableLength Properties? "]"
  public static boolean RelationshipDetail(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipDetail")) return false;
    if (!nextTokenIs(b, BRACKET_SQUAREOPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RELATIONSHIP_DETAIL, null);
    r = consumeToken(b, BRACKET_SQUAREOPEN);
    p = r; // pin = 1
    r = r && report_error_(b, RelationshipDetail_1(b, l + 1));
    r = p && report_error_(b, RelationshipDetail_2(b, l + 1)) && r;
    r = p && report_error_(b, RelationshipDetail_3(b, l + 1)) && r;
    r = p && report_error_(b, MaybeVariableLength(b, l + 1)) && r;
    r = p && report_error_(b, RelationshipDetail_5(b, l + 1)) && r;
    r = p && consumeToken(b, BRACKET_SQUARECLOSE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Variable?
  private static boolean RelationshipDetail_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipDetail_1")) return false;
    Variable(b, l + 1);
    return true;
  }

  // "?"?
  private static boolean RelationshipDetail_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipDetail_2")) return false;
    consumeToken(b, OP_QUESTIONSIGN);
    return true;
  }

  // RelationshipTypes?
  private static boolean RelationshipDetail_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipDetail_3")) return false;
    RelationshipTypes(b, l + 1);
    return true;
  }

  // Properties?
  private static boolean RelationshipDetail_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipDetail_5")) return false;
    Properties(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (K_RELATIONSHIP | K_REL) (IdentifiedIndexLookup | IndexQuery | IdLookup)
  public static boolean RelationshipLookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipLookup")) return false;
    if (!nextTokenIs(b, "<relationship lookup>", K_REL, K_RELATIONSHIP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RELATIONSHIP_LOOKUP, "<relationship lookup>");
    r = RelationshipLookup_0(b, l + 1);
    r = r && RelationshipLookup_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // K_RELATIONSHIP | K_REL
  private static boolean RelationshipLookup_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipLookup_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_RELATIONSHIP);
    if (!r) r = consumeToken(b, K_REL);
    exit_section_(b, m, null, r);
    return r;
  }

  // IdentifiedIndexLookup | IndexQuery | IdLookup
  private static boolean RelationshipLookup_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipLookup_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IdentifiedIndexLookup(b, l + 1);
    if (!r) r = IndexQuery(b, l + 1);
    if (!r) r = IdLookup(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LeftArrowHead? Dash RelationshipDetail? Dash RightArrowHead?
  public static boolean RelationshipPattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPattern")) return false;
    if (!nextTokenIs(b, "<relationship pattern>", OP_MINUS, OP_LESSTHEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RELATIONSHIP_PATTERN, "<relationship pattern>");
    r = RelationshipPattern_0(b, l + 1);
    r = r && Dash(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, RelationshipPattern_2(b, l + 1));
    r = p && report_error_(b, Dash(b, l + 1)) && r;
    r = p && RelationshipPattern_4(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // LeftArrowHead?
  private static boolean RelationshipPattern_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPattern_0")) return false;
    LeftArrowHead(b, l + 1);
    return true;
  }

  // RelationshipDetail?
  private static boolean RelationshipPattern_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPattern_2")) return false;
    RelationshipDetail(b, l + 1);
    return true;
  }

  // RightArrowHead?
  private static boolean RelationshipPattern_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPattern_4")) return false;
    RightArrowHead(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ("(" ")" "-" "[" Variable RelType "]" "-" "(" ")")
  //       | ("(" ")" "-" "[" Variable RelType "]" "-" ">" "(" ")")
  //       | ("(" ")" "<" "-" "[" Variable RelType "]" "-" "(" ")")
  public static boolean RelationshipPatternSyntax(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPatternSyntax")) return false;
    if (!nextTokenIs(b, PARENTHESE_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = RelationshipPatternSyntax_0(b, l + 1);
    if (!r) r = RelationshipPatternSyntax_1(b, l + 1);
    if (!r) r = RelationshipPatternSyntax_2(b, l + 1);
    exit_section_(b, m, RELATIONSHIP_PATTERN_SYNTAX, r);
    return r;
  }

  // "(" ")" "-" "[" Variable RelType "]" "-" "(" ")"
  private static boolean RelationshipPatternSyntax_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPatternSyntax_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PARENTHESE_OPEN, PARENTHESE_CLOSE, OP_MINUS, BRACKET_SQUAREOPEN);
    r = r && Variable(b, l + 1);
    r = r && RelType(b, l + 1);
    r = r && consumeTokens(b, 0, BRACKET_SQUARECLOSE, OP_MINUS, PARENTHESE_OPEN, PARENTHESE_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" ")" "-" "[" Variable RelType "]" "-" ">" "(" ")"
  private static boolean RelationshipPatternSyntax_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPatternSyntax_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PARENTHESE_OPEN, PARENTHESE_CLOSE, OP_MINUS, BRACKET_SQUAREOPEN);
    r = r && Variable(b, l + 1);
    r = r && RelType(b, l + 1);
    r = r && consumeTokens(b, 0, BRACKET_SQUARECLOSE, OP_MINUS, OP_GREATHERTHEN, PARENTHESE_OPEN, PARENTHESE_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" ")" "<" "-" "[" Variable RelType "]" "-" "(" ")"
  private static boolean RelationshipPatternSyntax_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPatternSyntax_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PARENTHESE_OPEN, PARENTHESE_CLOSE, OP_LESSTHEN, OP_MINUS, BRACKET_SQUAREOPEN);
    r = r && Variable(b, l + 1);
    r = r && RelType(b, l + 1);
    r = r && consumeTokens(b, 0, BRACKET_SQUARECLOSE, OP_MINUS, PARENTHESE_OPEN, PARENTHESE_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_CONSTRAINT K_ON RelationshipPatternSyntax K_ASSERT K_EXISTS "(" PropertyExpression ")"
  public static boolean RelationshipPropertyExistenceConstraintSyntax(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipPropertyExistenceConstraintSyntax")) return false;
    if (!nextTokenIs(b, K_CONSTRAINT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_CONSTRAINT, K_ON);
    r = r && RelationshipPatternSyntax(b, l + 1);
    r = r && consumeTokens(b, 0, K_ASSERT, K_EXISTS, PARENTHESE_OPEN);
    r = r && PropertyExpression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX, r);
    return r;
  }

  /* ********************************************************** */
  // ":" RelTypeName ("|" ":"? RelTypeName)*
  public static boolean RelationshipTypes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipTypes")) return false;
    if (!nextTokenIs(b, OP_COLON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RELATIONSHIP_TYPES, null);
    r = consumeToken(b, OP_COLON);
    p = r; // pin = 1
    r = r && report_error_(b, RelTypeName(b, l + 1));
    r = p && RelationshipTypes_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("|" ":"? RelTypeName)*
  private static boolean RelationshipTypes_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipTypes_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!RelationshipTypes_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "RelationshipTypes_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "|" ":"? RelTypeName
  private static boolean RelationshipTypes_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipTypes_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_PIPE);
    r = r && RelationshipTypes_2_0_1(b, l + 1);
    r = r && RelTypeName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ":"?
  private static boolean RelationshipTypes_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipTypes_2_0_1")) return false;
    consumeToken(b, OP_COLON);
    return true;
  }

  /* ********************************************************** */
  // NodePattern PatternElementChain+
  public static boolean RelationshipsPattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipsPattern")) return false;
    if (!nextTokenIs(b, PARENTHESE_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NodePattern(b, l + 1);
    r = r && RelationshipsPattern_1(b, l + 1);
    exit_section_(b, m, RELATIONSHIPS_PATTERN, r);
    return r;
  }

  // PatternElementChain+
  private static boolean RelationshipsPattern_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationshipsPattern_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PatternElementChain(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!PatternElementChain(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "RelationshipsPattern_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_REMOVE RemoveItem ("," RemoveItem)*
  public static boolean Remove(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Remove")) return false;
    if (!nextTokenIs(b, K_REMOVE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_REMOVE);
    r = r && RemoveItem(b, l + 1);
    r = r && Remove_2(b, l + 1);
    exit_section_(b, m, REMOVE, r);
    return r;
  }

  // ("," RemoveItem)*
  private static boolean Remove_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Remove_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Remove_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Remove_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," RemoveItem
  private static boolean Remove_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Remove_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && RemoveItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (Variable NodeLabels) | PropertyExpression
  public static boolean RemoveItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RemoveItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REMOVE_ITEM, "<remove item>");
    r = RemoveItem_0(b, l + 1);
    if (!r) r = PropertyExpression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Variable NodeLabels
  private static boolean RemoveItem_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RemoveItem_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Variable(b, l + 1);
    r = r && NodeLabels(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (K_RETURN K_DISTINCT ReturnBody)
  //          | (K_RETURN ReturnBody)
  public static boolean Return(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return")) return false;
    if (!nextTokenIs(b, K_RETURN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Return_0(b, l + 1);
    if (!r) r = Return_1(b, l + 1);
    exit_section_(b, m, RETURN, r);
    return r;
  }

  // K_RETURN K_DISTINCT ReturnBody
  private static boolean Return_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_RETURN, K_DISTINCT);
    r = r && ReturnBody(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_RETURN ReturnBody
  private static boolean Return_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Return_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_RETURN);
    r = r && ReturnBody(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ReturnItems Order? Skip? Limit?
  public static boolean ReturnBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RETURN_BODY, "<return body>");
    r = ReturnItems(b, l + 1);
    r = r && ReturnBody_1(b, l + 1);
    r = r && ReturnBody_2(b, l + 1);
    r = r && ReturnBody_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Order?
  private static boolean ReturnBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnBody_1")) return false;
    Order(b, l + 1);
    return true;
  }

  // Skip?
  private static boolean ReturnBody_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnBody_2")) return false;
    Skip(b, l + 1);
    return true;
  }

  // Limit?
  private static boolean ReturnBody_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnBody_3")) return false;
    Limit(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (Expression K_AS Variable) | (Expression)
  public static boolean ReturnItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RETURN_ITEM, "<return item>");
    r = ReturnItem_0(b, l + 1);
    if (!r) r = ReturnItem_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Expression K_AS Variable
  private static boolean ReturnItem_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItem_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    r = r && consumeToken(b, K_AS);
    r = r && Variable(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Expression)
  private static boolean ReturnItem_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItem_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ("*" ("," ReturnItem)*)
  //               | (ReturnItem ("," ReturnItem)*)
  public static boolean ReturnItems(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItems")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RETURN_ITEMS, "<return items>");
    r = ReturnItems_0(b, l + 1);
    if (!r) r = ReturnItems_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "*" ("," ReturnItem)*
  private static boolean ReturnItems_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItems_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_MUL);
    r = r && ReturnItems_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," ReturnItem)*
  private static boolean ReturnItems_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItems_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ReturnItems_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ReturnItems_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," ReturnItem
  private static boolean ReturnItems_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItems_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && ReturnItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ReturnItem ("," ReturnItem)*
  private static boolean ReturnItems_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItems_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ReturnItem(b, l + 1);
    r = r && ReturnItems_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," ReturnItem)*
  private static boolean ReturnItems_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItems_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ReturnItems_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ReturnItems_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," ReturnItem
  private static boolean ReturnItems_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnItems_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && ReturnItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ">"
  public static boolean RightArrowHead(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RightArrowHead")) return false;
    if (!nextTokenIs(b, OP_GREATHERTHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_GREATHERTHEN);
    exit_section_(b, m, RIGHT_ARROW_HEAD, r);
    return r;
  }

  /* ********************************************************** */
  // K_SET SetItem ("," SetItem)*
  public static boolean SetClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetClause")) return false;
    if (!nextTokenIs(b, K_SET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_SET);
    r = r && SetItem(b, l + 1);
    r = r && SetClause_2(b, l + 1);
    exit_section_(b, m, SET_CLAUSE, r);
    return r;
  }

  // ("," SetItem)*
  private static boolean SetClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetClause_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!SetClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SetClause_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," SetItem
  private static boolean SetClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && SetItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (PropertyExpression "=" Expression)
  //           | (Variable "=" Expression)
  //           | (Variable "+=" Expression)
  //           | (Variable NodeLabels)
  public static boolean SetItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SET_ITEM, "<set item>");
    r = SetItem_0(b, l + 1);
    if (!r) r = SetItem_1(b, l + 1);
    if (!r) r = SetItem_2(b, l + 1);
    if (!r) r = SetItem_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PropertyExpression "=" Expression
  private static boolean SetItem_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetItem_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropertyExpression(b, l + 1);
    r = r && consumeToken(b, OP_EQUAL);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Variable "=" Expression
  private static boolean SetItem_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetItem_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Variable(b, l + 1);
    r = r && consumeToken(b, OP_EQUAL);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Variable "+=" Expression
  private static boolean SetItem_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetItem_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Variable(b, l + 1);
    r = r && consumeToken(b, OP_PLUSEQUALS);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Variable NodeLabels
  private static boolean SetItem_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetItem_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Variable(b, l + 1);
    r = r && NodeLabels(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_SHORTESTPATH "(" PatternElement ")"
  public static boolean ShortestPathFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShortestPathFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_SHORTESTPATH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_SHORTESTPATH, PARENTHESE_OPEN);
    r = r && PatternElement(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, SHORTEST_PATH_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // ShortestPathFunctionInvocation
  //                       | AllShortestPathsFunctionInvocation
  public static boolean ShortestPathPattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShortestPathPattern")) return false;
    if (!nextTokenIs(b, "<shortest path pattern>", K_ALLSHORTESTPATHS, K_SHORTESTPATH)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SHORTEST_PATH_PATTERN, "<shortest path pattern>");
    r = ShortestPathFunctionInvocation(b, l + 1);
    if (!r) r = AllShortestPathsFunctionInvocation(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // l_integer
  public static boolean SignedDecimalInteger(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SignedDecimalInteger")) return false;
    if (!nextTokenIs(b, L_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_INTEGER);
    exit_section_(b, m, SIGNED_DECIMAL_INTEGER, r);
    return r;
  }

  /* ********************************************************** */
  // SignedDecimalInteger
  public static boolean SignedIntegerLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SignedIntegerLiteral")) return false;
    if (!nextTokenIs(b, L_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SignedDecimalInteger(b, l + 1);
    exit_section_(b, m, SIGNED_INTEGER_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // Variable
  public static boolean SimpleProcedureResult(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleProcedureResult")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_PROCEDURE_RESULT, "<simple procedure result>");
    r = Variable(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // K_SINGLE "(" FilterExpression ")"
  public static boolean SingleFunctionInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SingleFunctionInvocation")) return false;
    if (!nextTokenIs(b, K_SINGLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_SINGLE, PARENTHESE_OPEN);
    r = r && FilterExpression(b, l + 1);
    r = r && consumeToken(b, PARENTHESE_CLOSE);
    exit_section_(b, m, SINGLE_FUNCTION_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // Clause+
  public static boolean SingleQuery(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SingleQuery")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SINGLE_QUERY, "<single query>");
    r = Clause(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!Clause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SingleQuery", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // K_SKIP Expression
  public static boolean Skip(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Skip")) return false;
    if (!nextTokenIs(b, K_SKIP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_SKIP);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, SKIP, r);
    return r;
  }

  /* ********************************************************** */
  // (Expression (K_DESCENDING | K_DESC))
  //            | (Expression (K_ASCENDING | K_ASC)?)
  public static boolean SortItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SortItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SORT_ITEM, "<sort item>");
    r = SortItem_0(b, l + 1);
    if (!r) r = SortItem_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Expression (K_DESCENDING | K_DESC)
  private static boolean SortItem_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SortItem_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    r = r && SortItem_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_DESCENDING | K_DESC
  private static boolean SortItem_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SortItem_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DESCENDING);
    if (!r) r = consumeToken(b, K_DESC);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expression (K_ASCENDING | K_ASC)?
  private static boolean SortItem_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SortItem_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    r = r && SortItem_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (K_ASCENDING | K_ASC)?
  private static boolean SortItem_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SortItem_1_1")) return false;
    SortItem_1_1_0(b, l + 1);
    return true;
  }

  // K_ASCENDING | K_ASC
  private static boolean SortItem_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SortItem_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_ASCENDING);
    if (!r) r = consumeToken(b, K_ASC);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_START (StartPoint ("," StartPoint)*)? Where?
  public static boolean Start(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Start")) return false;
    if (!nextTokenIs(b, K_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_START);
    r = r && Start_1(b, l + 1);
    r = r && Start_2(b, l + 1);
    exit_section_(b, m, START, r);
    return r;
  }

  // (StartPoint ("," StartPoint)*)?
  private static boolean Start_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Start_1")) return false;
    Start_1_0(b, l + 1);
    return true;
  }

  // StartPoint ("," StartPoint)*
  private static boolean Start_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Start_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StartPoint(b, l + 1);
    r = r && Start_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," StartPoint)*
  private static boolean Start_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Start_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Start_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Start_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," StartPoint
  private static boolean Start_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Start_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_COMMA);
    r = r && StartPoint(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Where?
  private static boolean Start_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Start_2")) return false;
    Where(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Variable "=" Lookup
  public static boolean StartPoint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StartPoint")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, START_POINT, "<start point>");
    r = Variable(b, l + 1);
    r = r && consumeToken(b, OP_EQUAL);
    r = r && Lookup(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (K_BEGIN) | (K_COMMIT) | (QueryOptions? (Command | Query))
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = consumeToken(b, K_BEGIN);
    if (!r) r = consumeToken(b, K_COMMIT);
    if (!r) r = Statement_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // QueryOptions? (Command | Query)
  private static boolean Statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement_2_0(b, l + 1);
    r = r && Statement_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // QueryOptions?
  private static boolean Statement_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_2_0")) return false;
    QueryOptions(b, l + 1);
    return true;
  }

  // Command | Query
  private static boolean Statement_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Command(b, l + 1);
    if (!r) r = Query(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !<<eof>> Statement ';'?
  public static boolean StatementItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementItem")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT_ITEM, "<statement item>");
    r = StatementItem_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, Statement(b, l + 1));
    r = p && StatementItem_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, statement_recover_parser_);
    return r || p;
  }

  // !<<eof>>
  private static boolean StatementItem_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementItem_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !eof(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ';'?
  private static boolean StatementItem_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementItem_2")) return false;
    consumeToken(b, SEMICOLON);
    return true;
  }

  /* ********************************************************** */
  // l_string
  public static boolean StringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringLiteral")) return false;
    if (!nextTokenIs(b, L_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_STRING);
    exit_section_(b, m, STRING_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // UnescapedSymbolicNameString
  //     | EscapedSymbolicNameString
  //     | K_MATCH
  //     | K_RETURN
  //     | K_DISTINCT
  //     | K_UNION
  //     | K_ALL
  //     | K_LOAD
  //     | K_CSV
  //     | K_WITH
  //     | K_HEADERS
  //     | K_FROM
  //     | K_AS
  //     | K_FIELDTERMINATOR
  //     | K_CREATE
  //     | K_CONSTRAINT
  //     | K_ON
  //     | K_ASSERT
  //     | K_IS
  //     | K_UNIQUE
  //     | K_EXISTS
  //     | K_INDEX
  //     | K_DROP
  //     | K_START
  //     | K_WHERE
  //     | K_NODE
  //     | K_RELATIONSHIP
  //     | K_REL
  //     | K_OPTIONAL
  //     | K_USING
  //     | K_JOIN
  //     | K_SCAN
  //     | K_SHORTESTPATH
  //     | K_ALLSHORTESTPATHS
  //     | K_UNWIND
  //     | K_MERGE
  //     | K_SET
  //     | K_DELETE
  //     | K_DETACH
  //     | K_REMOVE
  //     | K_FOREACH
  //     | K_IN
  //     | K_ORDER
  //     | K_BY
  //     | K_DESCENDING
  //     | K_DESC
  //     | K_ASCENDING
  //     | K_ASC
  //     | K_SKIP
  //     | K_LIMIT
  //     | K_PERIODIC
  //     | K_BEGIN
  //     | K_COMMIT
  //     | K_XOR
  //     | K_OR
  //     | K_AND
  //     | K_NOT
  //     | K_STARTS
  //     | K_ENDS
  //     | K_CONTAINS
  //     | K_NULL
  //     | K_TRUE
  //     | K_FALSE
  //     | K_FILTER
  //     | K_EXTRACT
  //     | K_REDUCE
  //     | K_ANY
  //     | K_NONE
  //     | K_SINGLE
  //     | K_CASE
  //     | K_ELSE
  //     | K_END
  //     | K_WHEN
  //     | K_THEN
  //     | K_PROFILE
  //     | K_EXPLAIN
  //     | K_CYPHER
  //     | K_CALL
  //     | K_YIELD
  //     | K_COUNT
  public static boolean SymbolicNameString(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SymbolicNameString")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SYMBOLIC_NAME_STRING, "<symbolic name string>");
    r = UnescapedSymbolicNameString(b, l + 1);
    if (!r) r = EscapedSymbolicNameString(b, l + 1);
    if (!r) r = consumeToken(b, K_MATCH);
    if (!r) r = consumeToken(b, K_RETURN);
    if (!r) r = consumeToken(b, K_DISTINCT);
    if (!r) r = consumeToken(b, K_UNION);
    if (!r) r = consumeToken(b, K_ALL);
    if (!r) r = consumeToken(b, K_LOAD);
    if (!r) r = consumeToken(b, K_CSV);
    if (!r) r = consumeToken(b, K_WITH);
    if (!r) r = consumeToken(b, K_HEADERS);
    if (!r) r = consumeToken(b, K_FROM);
    if (!r) r = consumeToken(b, K_AS);
    if (!r) r = consumeToken(b, K_FIELDTERMINATOR);
    if (!r) r = consumeToken(b, K_CREATE);
    if (!r) r = consumeToken(b, K_CONSTRAINT);
    if (!r) r = consumeToken(b, K_ON);
    if (!r) r = consumeToken(b, K_ASSERT);
    if (!r) r = consumeToken(b, K_IS);
    if (!r) r = consumeToken(b, K_UNIQUE);
    if (!r) r = consumeToken(b, K_EXISTS);
    if (!r) r = consumeToken(b, K_INDEX);
    if (!r) r = consumeToken(b, K_DROP);
    if (!r) r = consumeToken(b, K_START);
    if (!r) r = consumeToken(b, K_WHERE);
    if (!r) r = consumeToken(b, K_NODE);
    if (!r) r = consumeToken(b, K_RELATIONSHIP);
    if (!r) r = consumeToken(b, K_REL);
    if (!r) r = consumeToken(b, K_OPTIONAL);
    if (!r) r = consumeToken(b, K_USING);
    if (!r) r = consumeToken(b, K_JOIN);
    if (!r) r = consumeToken(b, K_SCAN);
    if (!r) r = consumeToken(b, K_SHORTESTPATH);
    if (!r) r = consumeToken(b, K_ALLSHORTESTPATHS);
    if (!r) r = consumeToken(b, K_UNWIND);
    if (!r) r = consumeToken(b, K_MERGE);
    if (!r) r = consumeToken(b, K_SET);
    if (!r) r = consumeToken(b, K_DELETE);
    if (!r) r = consumeToken(b, K_DETACH);
    if (!r) r = consumeToken(b, K_REMOVE);
    if (!r) r = consumeToken(b, K_FOREACH);
    if (!r) r = consumeToken(b, K_IN);
    if (!r) r = consumeToken(b, K_ORDER);
    if (!r) r = consumeToken(b, K_BY);
    if (!r) r = consumeToken(b, K_DESCENDING);
    if (!r) r = consumeToken(b, K_DESC);
    if (!r) r = consumeToken(b, K_ASCENDING);
    if (!r) r = consumeToken(b, K_ASC);
    if (!r) r = consumeToken(b, K_SKIP);
    if (!r) r = consumeToken(b, K_LIMIT);
    if (!r) r = consumeToken(b, K_PERIODIC);
    if (!r) r = consumeToken(b, K_BEGIN);
    if (!r) r = consumeToken(b, K_COMMIT);
    if (!r) r = consumeToken(b, K_XOR);
    if (!r) r = consumeToken(b, K_OR);
    if (!r) r = consumeToken(b, K_AND);
    if (!r) r = consumeToken(b, K_NOT);
    if (!r) r = consumeToken(b, K_STARTS);
    if (!r) r = consumeToken(b, K_ENDS);
    if (!r) r = consumeToken(b, K_CONTAINS);
    if (!r) r = consumeToken(b, K_NULL);
    if (!r) r = consumeToken(b, K_TRUE);
    if (!r) r = consumeToken(b, K_FALSE);
    if (!r) r = consumeToken(b, K_FILTER);
    if (!r) r = consumeToken(b, K_EXTRACT);
    if (!r) r = consumeToken(b, K_REDUCE);
    if (!r) r = consumeToken(b, K_ANY);
    if (!r) r = consumeToken(b, K_NONE);
    if (!r) r = consumeToken(b, K_SINGLE);
    if (!r) r = consumeToken(b, K_CASE);
    if (!r) r = consumeToken(b, K_ELSE);
    if (!r) r = consumeToken(b, K_END);
    if (!r) r = consumeToken(b, K_WHEN);
    if (!r) r = consumeToken(b, K_THEN);
    if (!r) r = consumeToken(b, K_PROFILE);
    if (!r) r = consumeToken(b, K_EXPLAIN);
    if (!r) r = consumeToken(b, K_CYPHER);
    if (!r) r = consumeToken(b, K_CALL);
    if (!r) r = consumeToken(b, K_YIELD);
    if (!r) r = consumeToken(b, K_COUNT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // l_identifier
  public static boolean UnescapedSymbolicNameString(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnescapedSymbolicNameString")) return false;
    if (!nextTokenIs(b, L_IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_IDENTIFIER);
    exit_section_(b, m, UNESCAPED_SYMBOLIC_NAME_STRING, r);
    return r;
  }

  /* ********************************************************** */
  // ((K_UNION K_ALL) | K_UNION) SingleQuery
  public static boolean Union(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Union")) return false;
    if (!nextTokenIs(b, K_UNION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Union_0(b, l + 1);
    r = r && SingleQuery(b, l + 1);
    exit_section_(b, m, UNION, r);
    return r;
  }

  // (K_UNION K_ALL) | K_UNION
  private static boolean Union_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Union_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Union_0_0(b, l + 1);
    if (!r) r = consumeToken(b, K_UNION);
    exit_section_(b, m, null, r);
    return r;
  }

  // K_UNION K_ALL
  private static boolean Union_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Union_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_UNION, K_ALL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // K_CONSTRAINT K_ON "(" Variable NodeLabel ")" K_ASSERT PropertyExpression K_IS K_UNIQUE
  public static boolean UniqueConstraintSyntax(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UniqueConstraintSyntax")) return false;
    if (!nextTokenIs(b, K_CONSTRAINT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_CONSTRAINT, K_ON, PARENTHESE_OPEN);
    r = r && Variable(b, l + 1);
    r = r && NodeLabel(b, l + 1);
    r = r && consumeTokens(b, 0, PARENTHESE_CLOSE, K_ASSERT);
    r = r && PropertyExpression(b, l + 1);
    r = r && consumeTokens(b, 0, K_IS, K_UNIQUE);
    exit_section_(b, m, UNIQUE_CONSTRAINT_SYNTAX, r);
    return r;
  }

  /* ********************************************************** */
  // l_integer
  public static boolean UnsignedDecimalInteger(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnsignedDecimalInteger")) return false;
    if (!nextTokenIs(b, L_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_INTEGER);
    exit_section_(b, m, UNSIGNED_DECIMAL_INTEGER, r);
    return r;
  }

  /* ********************************************************** */
  // UnsignedDecimalInteger
  public static boolean UnsignedIntegerLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnsignedIntegerLiteral")) return false;
    if (!nextTokenIs(b, L_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnsignedDecimalInteger(b, l + 1);
    exit_section_(b, m, UNSIGNED_INTEGER_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // K_UNWIND Expression K_AS Variable
  public static boolean Unwind(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Unwind")) return false;
    if (!nextTokenIs(b, K_UNWIND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_UNWIND);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, K_AS);
    r = r && Variable(b, l + 1);
    exit_section_(b, m, UNWIND, r);
    return r;
  }

  /* ********************************************************** */
  // SymbolicNameString
  public static boolean Variable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Variable")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE, "<variable>");
    r = SymbolicNameString(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Variable
  public static boolean VariableSelector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableSelector")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_SELECTOR, "<variable selector>");
    r = Variable(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DoubleLiteral
  public static boolean VersionNumber(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionNumber")) return false;
    if (!nextTokenIs(b, L_DECIMAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DoubleLiteral(b, l + 1);
    exit_section_(b, m, VERSION_NUMBER, r);
    return r;
  }

  /* ********************************************************** */
  // K_WHERE Expression
  public static boolean Where(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Where")) return false;
    if (!nextTokenIs(b, K_WHERE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_WHERE);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, WHERE, r);
    return r;
  }

  /* ********************************************************** */
  // (K_WITH K_DISTINCT ReturnBody Where?)
  //        | (K_WITH ReturnBody Where?)
  public static boolean With(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "With")) return false;
    if (!nextTokenIs(b, K_WITH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = With_0(b, l + 1);
    if (!r) r = With_1(b, l + 1);
    exit_section_(b, m, WITH, r);
    return r;
  }

  // K_WITH K_DISTINCT ReturnBody Where?
  private static boolean With_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "With_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_WITH, K_DISTINCT);
    r = r && ReturnBody(b, l + 1);
    r = r && With_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Where?
  private static boolean With_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "With_0_3")) return false;
    Where(b, l + 1);
    return true;
  }

  // K_WITH ReturnBody Where?
  private static boolean With_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "With_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_WITH);
    r = r && ReturnBody(b, l + 1);
    r = r && With_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Where?
  private static boolean With_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "With_1_2")) return false;
    Where(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // StatementItem *
  static boolean cypher(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cypher")) return false;
    int c = current_position_(b);
    while (true) {
      if (!StatementItem(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "cypher", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "(" Expression ")"
  public static boolean parenthesizedExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesizedExpression")) return false;
    if (!nextTokenIs(b, PARENTHESE_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARENTHESIZED_EXPRESSION, null);
    r = consumeToken(b, PARENTHESE_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1));
    r = p && consumeToken(b, PARENTHESE_CLOSE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // !(';'
  //                         | K_USING
  //                         | K_UNION
  //                         | K_LOAD
  //                         | K_START
  //                         | K_MATCH
  //                         | K_UNWIND
  //                         | K_MERGE
  //                         | K_CREATE
  //                         | K_DROP
  //                         | K_SET
  //                         | K_DELETE
  //                         | K_REMOVE
  //                         | K_FOREACH
  //                         | K_WITH
  //                         | K_RETURN
  //                         | K_BEGIN
  //                         | K_COMMIT
  //                         | K_CYPHER
  //                         | K_PROFILE
  //                         | K_EXPLAIN
  //                         | K_CALL
  //                         | K_OPTIONAL
  //                         | K_DETACH)
  static boolean statement_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !statement_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ';'
  //                         | K_USING
  //                         | K_UNION
  //                         | K_LOAD
  //                         | K_START
  //                         | K_MATCH
  //                         | K_UNWIND
  //                         | K_MERGE
  //                         | K_CREATE
  //                         | K_DROP
  //                         | K_SET
  //                         | K_DELETE
  //                         | K_REMOVE
  //                         | K_FOREACH
  //                         | K_WITH
  //                         | K_RETURN
  //                         | K_BEGIN
  //                         | K_COMMIT
  //                         | K_CYPHER
  //                         | K_PROFILE
  //                         | K_EXPLAIN
  //                         | K_CALL
  //                         | K_OPTIONAL
  //                         | K_DETACH
  private static boolean statement_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_recover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, K_USING);
    if (!r) r = consumeToken(b, K_UNION);
    if (!r) r = consumeToken(b, K_LOAD);
    if (!r) r = consumeToken(b, K_START);
    if (!r) r = consumeToken(b, K_MATCH);
    if (!r) r = consumeToken(b, K_UNWIND);
    if (!r) r = consumeToken(b, K_MERGE);
    if (!r) r = consumeToken(b, K_CREATE);
    if (!r) r = consumeToken(b, K_DROP);
    if (!r) r = consumeToken(b, K_SET);
    if (!r) r = consumeToken(b, K_DELETE);
    if (!r) r = consumeToken(b, K_REMOVE);
    if (!r) r = consumeToken(b, K_FOREACH);
    if (!r) r = consumeToken(b, K_WITH);
    if (!r) r = consumeToken(b, K_RETURN);
    if (!r) r = consumeToken(b, K_BEGIN);
    if (!r) r = consumeToken(b, K_COMMIT);
    if (!r) r = consumeToken(b, K_CYPHER);
    if (!r) r = consumeToken(b, K_PROFILE);
    if (!r) r = consumeToken(b, K_EXPLAIN);
    if (!r) r = consumeToken(b, K_CALL);
    if (!r) r = consumeToken(b, K_OPTIONAL);
    if (!r) r = consumeToken(b, K_DETACH);
    exit_section_(b, m, null, r);
    return r;
  }

  final static Parser statement_recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return statement_recover(b, l + 1);
    }
  };
}
