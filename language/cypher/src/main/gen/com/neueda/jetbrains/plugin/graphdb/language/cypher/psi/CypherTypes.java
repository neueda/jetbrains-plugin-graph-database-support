// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.impl.*;

public interface CypherTypes {

  IElementType ALIASED_PROCEDURE_RESULT = new CypherElementType("ALIASED_PROCEDURE_RESULT");
  IElementType ALL_FUNCTION_INVOCATION = new CypherElementType("ALL_FUNCTION_INVOCATION");
  IElementType ALL_PROPERTIES_SELECTOR = new CypherElementType("ALL_PROPERTIES_SELECTOR");
  IElementType ALL_SHORTEST_PATHS_FUNCTION_INVOCATION = new CypherElementType("ALL_SHORTEST_PATHS_FUNCTION_INVOCATION");
  IElementType ANONYMOUS_PATTERN_PART = new CypherElementType("ANONYMOUS_PATTERN_PART");
  IElementType ANY_CYPHER_OPTION = new CypherElementType("ANY_CYPHER_OPTION");
  IElementType ANY_FUNCTION_INVOCATION = new CypherElementType("ANY_FUNCTION_INVOCATION");
  IElementType ARRAY = new CypherElementType("ARRAY");
  IElementType BOOLEAN_LITERAL = new CypherElementType("BOOLEAN_LITERAL");
  IElementType BULK_IMPORT_QUERY = new CypherElementType("BULK_IMPORT_QUERY");
  IElementType CALL = new CypherElementType("CALL");
  IElementType CASE_ALTERNATIVES = new CypherElementType("CASE_ALTERNATIVES");
  IElementType CASE_EXPRESSION = new CypherElementType("CASE_EXPRESSION");
  IElementType COMMAND = new CypherElementType("COMMAND");
  IElementType CONFIGURATION_OPTION = new CypherElementType("CONFIGURATION_OPTION");
  IElementType COUNT_STAR = new CypherElementType("COUNT_STAR");
  IElementType CREATE = new CypherElementType("CREATE");
  IElementType CREATE_INDEX = new CypherElementType("CREATE_INDEX");
  IElementType CREATE_NODE_PROPERTY_EXISTENCE_CONSTRAINT = new CypherElementType("CREATE_NODE_PROPERTY_EXISTENCE_CONSTRAINT");
  IElementType CREATE_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT = new CypherElementType("CREATE_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT");
  IElementType CREATE_UNIQUE_CONSTRAINT = new CypherElementType("CREATE_UNIQUE_CONSTRAINT");
  IElementType CYPHER_OPTION = new CypherElementType("CYPHER_OPTION");
  IElementType DASH = new CypherElementType("DASH");
  IElementType DELETE = new CypherElementType("DELETE");
  IElementType DOUBLE_LITERAL = new CypherElementType("DOUBLE_LITERAL");
  IElementType DROP_INDEX = new CypherElementType("DROP_INDEX");
  IElementType DROP_NODE_PROPERTY_EXISTENCE_CONSTRAINT = new CypherElementType("DROP_NODE_PROPERTY_EXISTENCE_CONSTRAINT");
  IElementType DROP_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT = new CypherElementType("DROP_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT");
  IElementType DROP_UNIQUE_CONSTRAINT = new CypherElementType("DROP_UNIQUE_CONSTRAINT");
  IElementType ESCAPED_SYMBOLIC_NAME_STRING = new CypherElementType("ESCAPED_SYMBOLIC_NAME_STRING");
  IElementType EXISTS_FUNCTION_INVOCATION = new CypherElementType("EXISTS_FUNCTION_INVOCATION");
  IElementType EXPLAIN = new CypherElementType("EXPLAIN");
  IElementType EXPRESSION = new CypherElementType("EXPRESSION");
  IElementType EXTRACT_FUNCTION_INVOCATION = new CypherElementType("EXTRACT_FUNCTION_INVOCATION");
  IElementType FILTER_EXPRESSION = new CypherElementType("FILTER_EXPRESSION");
  IElementType FILTER_FUNCTION_INVOCATION = new CypherElementType("FILTER_FUNCTION_INVOCATION");
  IElementType FOREACH = new CypherElementType("FOREACH");
  IElementType FUNCTION_ARGUMENTS = new CypherElementType("FUNCTION_ARGUMENTS");
  IElementType FUNCTION_INVOCATION = new CypherElementType("FUNCTION_INVOCATION");
  IElementType FUNCTION_INVOCATION_BODY = new CypherElementType("FUNCTION_INVOCATION_BODY");
  IElementType FUNCTION_NAME = new CypherElementType("FUNCTION_NAME");
  IElementType HINT = new CypherElementType("HINT");
  IElementType IDENTIFIED_INDEX_LOOKUP = new CypherElementType("IDENTIFIED_INDEX_LOOKUP");
  IElementType ID_IN_COLL = new CypherElementType("ID_IN_COLL");
  IElementType ID_LOOKUP = new CypherElementType("ID_LOOKUP");
  IElementType INDEX_QUERY = new CypherElementType("INDEX_QUERY");
  IElementType INDEX_SYNTAX = new CypherElementType("INDEX_SYNTAX");
  IElementType INTEGER_LITERAL = new CypherElementType("INTEGER_LITERAL");
  IElementType LABEL_NAME = new CypherElementType("LABEL_NAME");
  IElementType LEFT_ARROW_HEAD = new CypherElementType("LEFT_ARROW_HEAD");
  IElementType LIMIT = new CypherElementType("LIMIT");
  IElementType LIST_COMPREHENSION = new CypherElementType("LIST_COMPREHENSION");
  IElementType LITERAL_ENTRY = new CypherElementType("LITERAL_ENTRY");
  IElementType LITERAL_IDS = new CypherElementType("LITERAL_IDS");
  IElementType LOAD_CSV = new CypherElementType("LOAD_CSV");
  IElementType LOAD_CSV_QUERY = new CypherElementType("LOAD_CSV_QUERY");
  IElementType LOOKUP = new CypherElementType("LOOKUP");
  IElementType MAP_LITERAL = new CypherElementType("MAP_LITERAL");
  IElementType MAP_PROJECTION = new CypherElementType("MAP_PROJECTION");
  IElementType MAP_PROJECTION_VARIANTS = new CypherElementType("MAP_PROJECTION_VARIANTS");
  IElementType MATCH = new CypherElementType("MATCH");
  IElementType MAYBE_VARIABLE_LENGTH = new CypherElementType("MAYBE_VARIABLE_LENGTH");
  IElementType MERGE = new CypherElementType("MERGE");
  IElementType MERGE_ACTION = new CypherElementType("MERGE_ACTION");
  IElementType MULTI_PART_QUERY = new CypherElementType("MULTI_PART_QUERY");
  IElementType NAMESPACE = new CypherElementType("NAMESPACE");
  IElementType NEW_PARAMETER = new CypherElementType("NEW_PARAMETER");
  IElementType NODE_LABEL = new CypherElementType("NODE_LABEL");
  IElementType NODE_LABELS = new CypherElementType("NODE_LABELS");
  IElementType NODE_LOOKUP = new CypherElementType("NODE_LOOKUP");
  IElementType NODE_PATTERN = new CypherElementType("NODE_PATTERN");
  IElementType NODE_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX = new CypherElementType("NODE_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX");
  IElementType NONE_FUNCTION_INVOCATION = new CypherElementType("NONE_FUNCTION_INVOCATION");
  IElementType NULL_LITERAL = new CypherElementType("NULL_LITERAL");
  IElementType NUMBER_LITERAL = new CypherElementType("NUMBER_LITERAL");
  IElementType OLD_PARAMETER = new CypherElementType("OLD_PARAMETER");
  IElementType ORDER = new CypherElementType("ORDER");
  IElementType PARAMETER = new CypherElementType("PARAMETER");
  IElementType PARENTHESIZED_EXPRESSION = new CypherElementType("PARENTHESIZED_EXPRESSION");
  IElementType PATTERN = new CypherElementType("PATTERN");
  IElementType PATTERN_COMPREHENSION = new CypherElementType("PATTERN_COMPREHENSION");
  IElementType PATTERN_ELEMENT = new CypherElementType("PATTERN_ELEMENT");
  IElementType PATTERN_ELEMENT_CHAIN = new CypherElementType("PATTERN_ELEMENT_CHAIN");
  IElementType PATTERN_PART = new CypherElementType("PATTERN_PART");
  IElementType PERIODIC_COMMIT_HINT = new CypherElementType("PERIODIC_COMMIT_HINT");
  IElementType PROCEDURE_ARGUMENTS = new CypherElementType("PROCEDURE_ARGUMENTS");
  IElementType PROCEDURE_INVOCATION = new CypherElementType("PROCEDURE_INVOCATION");
  IElementType PROCEDURE_INVOCATION_BODY = new CypherElementType("PROCEDURE_INVOCATION_BODY");
  IElementType PROCEDURE_NAME = new CypherElementType("PROCEDURE_NAME");
  IElementType PROCEDURE_OUTPUT = new CypherElementType("PROCEDURE_OUTPUT");
  IElementType PROCEDURE_RESULT = new CypherElementType("PROCEDURE_RESULT");
  IElementType PROCEDURE_RESULTS = new CypherElementType("PROCEDURE_RESULTS");
  IElementType PROFILE = new CypherElementType("PROFILE");
  IElementType PROPERTIES = new CypherElementType("PROPERTIES");
  IElementType PROPERTY_EXPRESSION = new CypherElementType("PROPERTY_EXPRESSION");
  IElementType PROPERTY_KEY_NAME = new CypherElementType("PROPERTY_KEY_NAME");
  IElementType PROPERTY_KEY_NAMES = new CypherElementType("PROPERTY_KEY_NAMES");
  IElementType PROPERTY_LOOKUP = new CypherElementType("PROPERTY_LOOKUP");
  IElementType PROPERTY_SELECTOR = new CypherElementType("PROPERTY_SELECTOR");
  IElementType QUERY = new CypherElementType("QUERY");
  IElementType QUERY_OPTIONS = new CypherElementType("QUERY_OPTIONS");
  IElementType RANGE_LITERAL = new CypherElementType("RANGE_LITERAL");
  IElementType READING_CLAUSE = new CypherElementType("READING_CLAUSE");
  IElementType READING_WITH_RETURN = new CypherElementType("READING_WITH_RETURN");
  IElementType REDUCE_FUNCTION_INVOCATION = new CypherElementType("REDUCE_FUNCTION_INVOCATION");
  IElementType REGULAR_QUERY = new CypherElementType("REGULAR_QUERY");
  IElementType RELATIONSHIPS_PATTERN = new CypherElementType("RELATIONSHIPS_PATTERN");
  IElementType RELATIONSHIP_DETAIL = new CypherElementType("RELATIONSHIP_DETAIL");
  IElementType RELATIONSHIP_LOOKUP = new CypherElementType("RELATIONSHIP_LOOKUP");
  IElementType RELATIONSHIP_PATTERN = new CypherElementType("RELATIONSHIP_PATTERN");
  IElementType RELATIONSHIP_PATTERN_SYNTAX = new CypherElementType("RELATIONSHIP_PATTERN_SYNTAX");
  IElementType RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX = new CypherElementType("RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX");
  IElementType RELATIONSHIP_TYPES = new CypherElementType("RELATIONSHIP_TYPES");
  IElementType REL_TYPE = new CypherElementType("REL_TYPE");
  IElementType REL_TYPE_NAME = new CypherElementType("REL_TYPE_NAME");
  IElementType REMOVE = new CypherElementType("REMOVE");
  IElementType REMOVE_ITEM = new CypherElementType("REMOVE_ITEM");
  IElementType RESERVED_WORD = new CypherElementType("RESERVED_WORD");
  IElementType RETURN = new CypherElementType("RETURN");
  IElementType RETURN_BODY = new CypherElementType("RETURN_BODY");
  IElementType RETURN_ITEM = new CypherElementType("RETURN_ITEM");
  IElementType RETURN_ITEMS = new CypherElementType("RETURN_ITEMS");
  IElementType RIGHT_ARROW_HEAD = new CypherElementType("RIGHT_ARROW_HEAD");
  IElementType SET_CLAUSE = new CypherElementType("SET_CLAUSE");
  IElementType SET_ITEM = new CypherElementType("SET_ITEM");
  IElementType SHORTEST_PATH_FUNCTION_INVOCATION = new CypherElementType("SHORTEST_PATH_FUNCTION_INVOCATION");
  IElementType SHORTEST_PATH_PATTERN = new CypherElementType("SHORTEST_PATH_PATTERN");
  IElementType SIMPLE_PROCEDURE_RESULT = new CypherElementType("SIMPLE_PROCEDURE_RESULT");
  IElementType SINGLE_FUNCTION_INVOCATION = new CypherElementType("SINGLE_FUNCTION_INVOCATION");
  IElementType SINGLE_PART_QUERY = new CypherElementType("SINGLE_PART_QUERY");
  IElementType SINGLE_QUERY = new CypherElementType("SINGLE_QUERY");
  IElementType SKIP = new CypherElementType("SKIP");
  IElementType SORT_ITEM = new CypherElementType("SORT_ITEM");
  IElementType STANDALONE_CALL = new CypherElementType("STANDALONE_CALL");
  IElementType START = new CypherElementType("START");
  IElementType START_POINT = new CypherElementType("START_POINT");
  IElementType STATEMENT = new CypherElementType("STATEMENT");
  IElementType STATEMENT_ITEM = new CypherElementType("STATEMENT_ITEM");
  IElementType STRING_LITERAL = new CypherElementType("STRING_LITERAL");
  IElementType SYMBOLIC_NAME_STRING = new CypherElementType("SYMBOLIC_NAME_STRING");
  IElementType UNARY_OPERATOR = new CypherElementType("UNARY_OPERATOR");
  IElementType UNESCAPED_SYMBOLIC_NAME_STRING = new CypherElementType("UNESCAPED_SYMBOLIC_NAME_STRING");
  IElementType UNION = new CypherElementType("UNION");
  IElementType UNIQUE_CONSTRAINT_SYNTAX = new CypherElementType("UNIQUE_CONSTRAINT_SYNTAX");
  IElementType UNSIGNED_DOUBLE = new CypherElementType("UNSIGNED_DOUBLE");
  IElementType UNSIGNED_INTEGER = new CypherElementType("UNSIGNED_INTEGER");
  IElementType UNWIND = new CypherElementType("UNWIND");
  IElementType UPDATING_CLAUSE = new CypherElementType("UPDATING_CLAUSE");
  IElementType VARIABLE = new CypherElementType("VARIABLE");
  IElementType VARIABLE_SELECTOR = new CypherElementType("VARIABLE_SELECTOR");
  IElementType VERSION_NUMBER = new CypherElementType("VERSION_NUMBER");
  IElementType WHERE = new CypherElementType("WHERE");
  IElementType WITH = new CypherElementType("WITH");

  IElementType BRACKET_CURLYCLOSE = new CypherTokenType("}");
  IElementType BRACKET_CURLYOPEN = new CypherTokenType("{");
  IElementType BRACKET_SQUARECLOSE = new CypherTokenType("]");
  IElementType BRACKET_SQUAREOPEN = new CypherTokenType("[");
  IElementType DOLLAR = new CypherTokenType("$");
  IElementType K_ADD = new CypherTokenType("K_ADD");
  IElementType K_ALL = new CypherTokenType("K_ALL");
  IElementType K_ALLSHORTESTPATHS = new CypherTokenType("K_ALLSHORTESTPATHS");
  IElementType K_AND = new CypherTokenType("K_AND");
  IElementType K_ANY = new CypherTokenType("K_ANY");
  IElementType K_AS = new CypherTokenType("K_AS");
  IElementType K_ASC = new CypherTokenType("K_ASC");
  IElementType K_ASCENDING = new CypherTokenType("K_ASCENDING");
  IElementType K_ASSERT = new CypherTokenType("K_ASSERT");
  IElementType K_BEGIN = new CypherTokenType("K_BEGIN");
  IElementType K_BY = new CypherTokenType("K_BY");
  IElementType K_CALL = new CypherTokenType("K_CALL");
  IElementType K_CASE = new CypherTokenType("K_CASE");
  IElementType K_COMMIT = new CypherTokenType("K_COMMIT");
  IElementType K_CONSTRAINT = new CypherTokenType("K_CONSTRAINT");
  IElementType K_CONTAINS = new CypherTokenType("K_CONTAINS");
  IElementType K_COUNT = new CypherTokenType("K_COUNT");
  IElementType K_CREATE = new CypherTokenType("K_CREATE");
  IElementType K_CSV = new CypherTokenType("K_CSV");
  IElementType K_CYPHER = new CypherTokenType("K_CYPHER");
  IElementType K_DELETE = new CypherTokenType("K_DELETE");
  IElementType K_DESC = new CypherTokenType("K_DESC");
  IElementType K_DESCENDING = new CypherTokenType("K_DESCENDING");
  IElementType K_DETACH = new CypherTokenType("K_DETACH");
  IElementType K_DISTINCT = new CypherTokenType("K_DISTINCT");
  IElementType K_DO = new CypherTokenType("K_DO");
  IElementType K_DROP = new CypherTokenType("K_DROP");
  IElementType K_ELSE = new CypherTokenType("K_ELSE");
  IElementType K_END = new CypherTokenType("K_END");
  IElementType K_ENDS = new CypherTokenType("K_ENDS");
  IElementType K_EXISTS = new CypherTokenType("K_EXISTS");
  IElementType K_EXPLAIN = new CypherTokenType("K_EXPLAIN");
  IElementType K_EXTRACT = new CypherTokenType("K_EXTRACT");
  IElementType K_FALSE = new CypherTokenType("K_FALSE");
  IElementType K_FIELDTERMINATOR = new CypherTokenType("K_FIELDTERMINATOR");
  IElementType K_FILTER = new CypherTokenType("K_FILTER");
  IElementType K_FOR = new CypherTokenType("K_FOR");
  IElementType K_FOREACH = new CypherTokenType("K_FOREACH");
  IElementType K_FROM = new CypherTokenType("K_FROM");
  IElementType K_HEADERS = new CypherTokenType("K_HEADERS");
  IElementType K_IN = new CypherTokenType("K_IN");
  IElementType K_INDEX = new CypherTokenType("K_INDEX");
  IElementType K_IS = new CypherTokenType("K_IS");
  IElementType K_JOIN = new CypherTokenType("K_JOIN");
  IElementType K_LIMIT = new CypherTokenType("K_LIMIT");
  IElementType K_LOAD = new CypherTokenType("K_LOAD");
  IElementType K_MANDATORY = new CypherTokenType("K_MANDATORY");
  IElementType K_MATCH = new CypherTokenType("K_MATCH");
  IElementType K_MERGE = new CypherTokenType("K_MERGE");
  IElementType K_NODE = new CypherTokenType("K_NODE");
  IElementType K_NONE = new CypherTokenType("K_NONE");
  IElementType K_NOT = new CypherTokenType("K_NOT");
  IElementType K_NULL = new CypherTokenType("K_NULL");
  IElementType K_OF = new CypherTokenType("K_OF");
  IElementType K_ON = new CypherTokenType("K_ON");
  IElementType K_OPTIONAL = new CypherTokenType("K_OPTIONAL");
  IElementType K_OR = new CypherTokenType("K_OR");
  IElementType K_ORDER = new CypherTokenType("K_ORDER");
  IElementType K_PERIODIC = new CypherTokenType("K_PERIODIC");
  IElementType K_PROFILE = new CypherTokenType("K_PROFILE");
  IElementType K_REDUCE = new CypherTokenType("K_REDUCE");
  IElementType K_REL = new CypherTokenType("K_REL");
  IElementType K_RELATIONSHIP = new CypherTokenType("K_RELATIONSHIP");
  IElementType K_REMOVE = new CypherTokenType("K_REMOVE");
  IElementType K_REQUIRE = new CypherTokenType("K_REQUIRE");
  IElementType K_RETURN = new CypherTokenType("K_RETURN");
  IElementType K_SCALAR = new CypherTokenType("K_SCALAR");
  IElementType K_SCAN = new CypherTokenType("K_SCAN");
  IElementType K_SET = new CypherTokenType("K_SET");
  IElementType K_SHORTESTPATH = new CypherTokenType("K_SHORTESTPATH");
  IElementType K_SINGLE = new CypherTokenType("K_SINGLE");
  IElementType K_SKIP = new CypherTokenType("K_SKIP");
  IElementType K_START = new CypherTokenType("K_START");
  IElementType K_STARTS = new CypherTokenType("K_STARTS");
  IElementType K_THEN = new CypherTokenType("K_THEN");
  IElementType K_TRUE = new CypherTokenType("K_TRUE");
  IElementType K_UNION = new CypherTokenType("K_UNION");
  IElementType K_UNIQUE = new CypherTokenType("K_UNIQUE");
  IElementType K_UNWIND = new CypherTokenType("K_UNWIND");
  IElementType K_USING = new CypherTokenType("K_USING");
  IElementType K_WHEN = new CypherTokenType("K_WHEN");
  IElementType K_WHERE = new CypherTokenType("K_WHERE");
  IElementType K_WITH = new CypherTokenType("K_WITH");
  IElementType K_XOR = new CypherTokenType("K_XOR");
  IElementType K_YIELD = new CypherTokenType("K_YIELD");
  IElementType L_DECIMAL = new CypherTokenType("l_decimal");
  IElementType L_IDENTIFIER = new CypherTokenType("l_identifier");
  IElementType L_IDENTIFIER_TEXT = new CypherTokenType("l_identifier_text");
  IElementType L_INTEGER = new CypherTokenType("l_integer");
  IElementType L_STRING = new CypherTokenType("l_string");
  IElementType OP_BACTICK = new CypherTokenType("`");
  IElementType OP_COLON = new CypherTokenType(":");
  IElementType OP_COMMA = new CypherTokenType(",");
  IElementType OP_DIVIDE = new CypherTokenType("/");
  IElementType OP_DOT = new CypherTokenType(".");
  IElementType OP_EQUAL = new CypherTokenType("=");
  IElementType OP_GREATERTHANEQUALS = new CypherTokenType(">=");
  IElementType OP_GREATHERTHEN = new CypherTokenType(">");
  IElementType OP_INVALIDNOTEQUALS = new CypherTokenType("<>");
  IElementType OP_LESSTHANEQUALS = new CypherTokenType("<=");
  IElementType OP_LESSTHEN = new CypherTokenType("<");
  IElementType OP_MINUS = new CypherTokenType("-");
  IElementType OP_MODULO = new CypherTokenType("%");
  IElementType OP_MUL = new CypherTokenType("*");
  IElementType OP_NOTEQUALS = new CypherTokenType("!=");
  IElementType OP_PIPE = new CypherTokenType("|");
  IElementType OP_PLUS = new CypherTokenType("+");
  IElementType OP_PLUSEQUALS = new CypherTokenType("+=");
  IElementType OP_POW = new CypherTokenType("^");
  IElementType OP_QUESTIONSIGN = new CypherTokenType("?");
  IElementType OP_RANGE = new CypherTokenType("..");
  IElementType OP_REGEXMATCH = new CypherTokenType("=~");
  IElementType PARENTHESE_CLOSE = new CypherTokenType(")");
  IElementType PARENTHESE_OPEN = new CypherTokenType("(");
  IElementType SEMICOLON = new CypherTokenType(";");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ALIASED_PROCEDURE_RESULT) {
        return new CypherAliasedProcedureResultImpl(node);
      }
      else if (type == ALL_FUNCTION_INVOCATION) {
        return new CypherAllFunctionInvocationImpl(node);
      }
      else if (type == ALL_PROPERTIES_SELECTOR) {
        return new CypherAllPropertiesSelectorImpl(node);
      }
      else if (type == ALL_SHORTEST_PATHS_FUNCTION_INVOCATION) {
        return new CypherAllShortestPathsFunctionInvocationImpl(node);
      }
      else if (type == ANONYMOUS_PATTERN_PART) {
        return new CypherAnonymousPatternPartImpl(node);
      }
      else if (type == ANY_CYPHER_OPTION) {
        return new CypherAnyCypherOptionImpl(node);
      }
      else if (type == ANY_FUNCTION_INVOCATION) {
        return new CypherAnyFunctionInvocationImpl(node);
      }
      else if (type == ARRAY) {
        return new CypherArrayImpl(node);
      }
      else if (type == BOOLEAN_LITERAL) {
        return new CypherBooleanLiteralImpl(node);
      }
      else if (type == BULK_IMPORT_QUERY) {
        return new CypherBulkImportQueryImpl(node);
      }
      else if (type == CALL) {
        return new CypherCallImpl(node);
      }
      else if (type == CASE_ALTERNATIVES) {
        return new CypherCaseAlternativesImpl(node);
      }
      else if (type == CASE_EXPRESSION) {
        return new CypherCaseExpressionImpl(node);
      }
      else if (type == COMMAND) {
        return new CypherCommandImpl(node);
      }
      else if (type == CONFIGURATION_OPTION) {
        return new CypherConfigurationOptionImpl(node);
      }
      else if (type == COUNT_STAR) {
        return new CypherCountStarImpl(node);
      }
      else if (type == CREATE) {
        return new CypherCreateImpl(node);
      }
      else if (type == CREATE_INDEX) {
        return new CypherCreateIndexImpl(node);
      }
      else if (type == CREATE_NODE_PROPERTY_EXISTENCE_CONSTRAINT) {
        return new CypherCreateNodePropertyExistenceConstraintImpl(node);
      }
      else if (type == CREATE_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT) {
        return new CypherCreateRelationshipPropertyExistenceConstraintImpl(node);
      }
      else if (type == CREATE_UNIQUE_CONSTRAINT) {
        return new CypherCreateUniqueConstraintImpl(node);
      }
      else if (type == CYPHER_OPTION) {
        return new CypherCypherOptionImpl(node);
      }
      else if (type == DASH) {
        return new CypherDashImpl(node);
      }
      else if (type == DELETE) {
        return new CypherDeleteImpl(node);
      }
      else if (type == DOUBLE_LITERAL) {
        return new CypherDoubleLiteralImpl(node);
      }
      else if (type == DROP_INDEX) {
        return new CypherDropIndexImpl(node);
      }
      else if (type == DROP_NODE_PROPERTY_EXISTENCE_CONSTRAINT) {
        return new CypherDropNodePropertyExistenceConstraintImpl(node);
      }
      else if (type == DROP_RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT) {
        return new CypherDropRelationshipPropertyExistenceConstraintImpl(node);
      }
      else if (type == DROP_UNIQUE_CONSTRAINT) {
        return new CypherDropUniqueConstraintImpl(node);
      }
      else if (type == ESCAPED_SYMBOLIC_NAME_STRING) {
        return new CypherEscapedSymbolicNameStringImpl(node);
      }
      else if (type == EXISTS_FUNCTION_INVOCATION) {
        return new CypherExistsFunctionInvocationImpl(node);
      }
      else if (type == EXPLAIN) {
        return new CypherExplainImpl(node);
      }
      else if (type == EXPRESSION) {
        return new CypherExpressionImpl(node);
      }
      else if (type == EXTRACT_FUNCTION_INVOCATION) {
        return new CypherExtractFunctionInvocationImpl(node);
      }
      else if (type == FILTER_EXPRESSION) {
        return new CypherFilterExpressionImpl(node);
      }
      else if (type == FILTER_FUNCTION_INVOCATION) {
        return new CypherFilterFunctionInvocationImpl(node);
      }
      else if (type == FOREACH) {
        return new CypherForeachImpl(node);
      }
      else if (type == FUNCTION_ARGUMENTS) {
        return new CypherFunctionArgumentsImpl(node);
      }
      else if (type == FUNCTION_INVOCATION) {
        return new CypherFunctionInvocationImpl(node);
      }
      else if (type == FUNCTION_INVOCATION_BODY) {
        return new CypherFunctionInvocationBodyImpl(node);
      }
      else if (type == FUNCTION_NAME) {
        return new CypherFunctionNameImpl(node);
      }
      else if (type == HINT) {
        return new CypherHintImpl(node);
      }
      else if (type == IDENTIFIED_INDEX_LOOKUP) {
        return new CypherIdentifiedIndexLookupImpl(node);
      }
      else if (type == ID_IN_COLL) {
        return new CypherIdInCollImpl(node);
      }
      else if (type == ID_LOOKUP) {
        return new CypherIdLookupImpl(node);
      }
      else if (type == INDEX_QUERY) {
        return new CypherIndexQueryImpl(node);
      }
      else if (type == INDEX_SYNTAX) {
        return new CypherIndexSyntaxImpl(node);
      }
      else if (type == INTEGER_LITERAL) {
        return new CypherIntegerLiteralImpl(node);
      }
      else if (type == LABEL_NAME) {
        return new CypherLabelNameImpl(node);
      }
      else if (type == LEFT_ARROW_HEAD) {
        return new CypherLeftArrowHeadImpl(node);
      }
      else if (type == LIMIT) {
        return new CypherLimitImpl(node);
      }
      else if (type == LIST_COMPREHENSION) {
        return new CypherListComprehensionImpl(node);
      }
      else if (type == LITERAL_ENTRY) {
        return new CypherLiteralEntryImpl(node);
      }
      else if (type == LITERAL_IDS) {
        return new CypherLiteralIdsImpl(node);
      }
      else if (type == LOAD_CSV) {
        return new CypherLoadCSVImpl(node);
      }
      else if (type == LOAD_CSV_QUERY) {
        return new CypherLoadCSVQueryImpl(node);
      }
      else if (type == LOOKUP) {
        return new CypherLookupImpl(node);
      }
      else if (type == MAP_LITERAL) {
        return new CypherMapLiteralImpl(node);
      }
      else if (type == MAP_PROJECTION) {
        return new CypherMapProjectionImpl(node);
      }
      else if (type == MAP_PROJECTION_VARIANTS) {
        return new CypherMapProjectionVariantsImpl(node);
      }
      else if (type == MATCH) {
        return new CypherMatchImpl(node);
      }
      else if (type == MAYBE_VARIABLE_LENGTH) {
        return new CypherMaybeVariableLengthImpl(node);
      }
      else if (type == MERGE) {
        return new CypherMergeImpl(node);
      }
      else if (type == MERGE_ACTION) {
        return new CypherMergeActionImpl(node);
      }
      else if (type == MULTI_PART_QUERY) {
        return new CypherMultiPartQueryImpl(node);
      }
      else if (type == NAMESPACE) {
        return new CypherNamespaceImpl(node);
      }
      else if (type == NEW_PARAMETER) {
        return new CypherNewParameterImpl(node);
      }
      else if (type == NODE_LABEL) {
        return new CypherNodeLabelImpl(node);
      }
      else if (type == NODE_LABELS) {
        return new CypherNodeLabelsImpl(node);
      }
      else if (type == NODE_LOOKUP) {
        return new CypherNodeLookupImpl(node);
      }
      else if (type == NODE_PATTERN) {
        return new CypherNodePatternImpl(node);
      }
      else if (type == NODE_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX) {
        return new CypherNodePropertyExistenceConstraintSyntaxImpl(node);
      }
      else if (type == NONE_FUNCTION_INVOCATION) {
        return new CypherNoneFunctionInvocationImpl(node);
      }
      else if (type == NULL_LITERAL) {
        return new CypherNullLiteralImpl(node);
      }
      else if (type == NUMBER_LITERAL) {
        return new CypherNumberLiteralImpl(node);
      }
      else if (type == OLD_PARAMETER) {
        return new CypherOldParameterImpl(node);
      }
      else if (type == ORDER) {
        return new CypherOrderImpl(node);
      }
      else if (type == PARAMETER) {
        return new CypherParameterImpl(node);
      }
      else if (type == PARENTHESIZED_EXPRESSION) {
        return new CypherParenthesizedExpressionImpl(node);
      }
      else if (type == PATTERN) {
        return new CypherPatternImpl(node);
      }
      else if (type == PATTERN_COMPREHENSION) {
        return new CypherPatternComprehensionImpl(node);
      }
      else if (type == PATTERN_ELEMENT) {
        return new CypherPatternElementImpl(node);
      }
      else if (type == PATTERN_ELEMENT_CHAIN) {
        return new CypherPatternElementChainImpl(node);
      }
      else if (type == PATTERN_PART) {
        return new CypherPatternPartImpl(node);
      }
      else if (type == PERIODIC_COMMIT_HINT) {
        return new CypherPeriodicCommitHintImpl(node);
      }
      else if (type == PROCEDURE_ARGUMENTS) {
        return new CypherProcedureArgumentsImpl(node);
      }
      else if (type == PROCEDURE_INVOCATION) {
        return new CypherProcedureInvocationImpl(node);
      }
      else if (type == PROCEDURE_INVOCATION_BODY) {
        return new CypherProcedureInvocationBodyImpl(node);
      }
      else if (type == PROCEDURE_NAME) {
        return new CypherProcedureNameImpl(node);
      }
      else if (type == PROCEDURE_OUTPUT) {
        return new CypherProcedureOutputImpl(node);
      }
      else if (type == PROCEDURE_RESULT) {
        return new CypherProcedureResultImpl(node);
      }
      else if (type == PROCEDURE_RESULTS) {
        return new CypherProcedureResultsImpl(node);
      }
      else if (type == PROFILE) {
        return new CypherProfileImpl(node);
      }
      else if (type == PROPERTIES) {
        return new CypherPropertiesImpl(node);
      }
      else if (type == PROPERTY_EXPRESSION) {
        return new CypherPropertyExpressionImpl(node);
      }
      else if (type == PROPERTY_KEY_NAME) {
        return new CypherPropertyKeyNameImpl(node);
      }
      else if (type == PROPERTY_KEY_NAMES) {
        return new CypherPropertyKeyNamesImpl(node);
      }
      else if (type == PROPERTY_LOOKUP) {
        return new CypherPropertyLookupImpl(node);
      }
      else if (type == PROPERTY_SELECTOR) {
        return new CypherPropertySelectorImpl(node);
      }
      else if (type == QUERY) {
        return new CypherQueryImpl(node);
      }
      else if (type == QUERY_OPTIONS) {
        return new CypherQueryOptionsImpl(node);
      }
      else if (type == RANGE_LITERAL) {
        return new CypherRangeLiteralImpl(node);
      }
      else if (type == READING_CLAUSE) {
        return new CypherReadingClauseImpl(node);
      }
      else if (type == READING_WITH_RETURN) {
        return new CypherReadingWithReturnImpl(node);
      }
      else if (type == REDUCE_FUNCTION_INVOCATION) {
        return new CypherReduceFunctionInvocationImpl(node);
      }
      else if (type == REGULAR_QUERY) {
        return new CypherRegularQueryImpl(node);
      }
      else if (type == RELATIONSHIPS_PATTERN) {
        return new CypherRelationshipsPatternImpl(node);
      }
      else if (type == RELATIONSHIP_DETAIL) {
        return new CypherRelationshipDetailImpl(node);
      }
      else if (type == RELATIONSHIP_LOOKUP) {
        return new CypherRelationshipLookupImpl(node);
      }
      else if (type == RELATIONSHIP_PATTERN) {
        return new CypherRelationshipPatternImpl(node);
      }
      else if (type == RELATIONSHIP_PATTERN_SYNTAX) {
        return new CypherRelationshipPatternSyntaxImpl(node);
      }
      else if (type == RELATIONSHIP_PROPERTY_EXISTENCE_CONSTRAINT_SYNTAX) {
        return new CypherRelationshipPropertyExistenceConstraintSyntaxImpl(node);
      }
      else if (type == RELATIONSHIP_TYPES) {
        return new CypherRelationshipTypesImpl(node);
      }
      else if (type == REL_TYPE) {
        return new CypherRelTypeImpl(node);
      }
      else if (type == REL_TYPE_NAME) {
        return new CypherRelTypeNameImpl(node);
      }
      else if (type == REMOVE) {
        return new CypherRemoveImpl(node);
      }
      else if (type == REMOVE_ITEM) {
        return new CypherRemoveItemImpl(node);
      }
      else if (type == RESERVED_WORD) {
        return new CypherReservedWordImpl(node);
      }
      else if (type == RETURN) {
        return new CypherReturnImpl(node);
      }
      else if (type == RETURN_BODY) {
        return new CypherReturnBodyImpl(node);
      }
      else if (type == RETURN_ITEM) {
        return new CypherReturnItemImpl(node);
      }
      else if (type == RETURN_ITEMS) {
        return new CypherReturnItemsImpl(node);
      }
      else if (type == RIGHT_ARROW_HEAD) {
        return new CypherRightArrowHeadImpl(node);
      }
      else if (type == SET_CLAUSE) {
        return new CypherSetClauseImpl(node);
      }
      else if (type == SET_ITEM) {
        return new CypherSetItemImpl(node);
      }
      else if (type == SHORTEST_PATH_FUNCTION_INVOCATION) {
        return new CypherShortestPathFunctionInvocationImpl(node);
      }
      else if (type == SHORTEST_PATH_PATTERN) {
        return new CypherShortestPathPatternImpl(node);
      }
      else if (type == SIMPLE_PROCEDURE_RESULT) {
        return new CypherSimpleProcedureResultImpl(node);
      }
      else if (type == SINGLE_FUNCTION_INVOCATION) {
        return new CypherSingleFunctionInvocationImpl(node);
      }
      else if (type == SINGLE_PART_QUERY) {
        return new CypherSinglePartQueryImpl(node);
      }
      else if (type == SINGLE_QUERY) {
        return new CypherSingleQueryImpl(node);
      }
      else if (type == SKIP) {
        return new CypherSkipImpl(node);
      }
      else if (type == SORT_ITEM) {
        return new CypherSortItemImpl(node);
      }
      else if (type == STANDALONE_CALL) {
        return new CypherStandaloneCallImpl(node);
      }
      else if (type == START) {
        return new CypherStartImpl(node);
      }
      else if (type == START_POINT) {
        return new CypherStartPointImpl(node);
      }
      else if (type == STATEMENT) {
        return new CypherStatementImpl(node);
      }
      else if (type == STATEMENT_ITEM) {
        return new CypherStatementItemImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new CypherStringLiteralImpl(node);
      }
      else if (type == SYMBOLIC_NAME_STRING) {
        return new CypherSymbolicNameStringImpl(node);
      }
      else if (type == UNARY_OPERATOR) {
        return new CypherUnaryOperatorImpl(node);
      }
      else if (type == UNESCAPED_SYMBOLIC_NAME_STRING) {
        return new CypherUnescapedSymbolicNameStringImpl(node);
      }
      else if (type == UNION) {
        return new CypherUnionImpl(node);
      }
      else if (type == UNIQUE_CONSTRAINT_SYNTAX) {
        return new CypherUniqueConstraintSyntaxImpl(node);
      }
      else if (type == UNSIGNED_DOUBLE) {
        return new CypherUnsignedDoubleImpl(node);
      }
      else if (type == UNSIGNED_INTEGER) {
        return new CypherUnsignedIntegerImpl(node);
      }
      else if (type == UNWIND) {
        return new CypherUnwindImpl(node);
      }
      else if (type == UPDATING_CLAUSE) {
        return new CypherUpdatingClauseImpl(node);
      }
      else if (type == VARIABLE) {
        return new CypherVariableImpl(node);
      }
      else if (type == VARIABLE_SELECTOR) {
        return new CypherVariableSelectorImpl(node);
      }
      else if (type == VERSION_NUMBER) {
        return new CypherVersionNumberImpl(node);
      }
      else if (type == WHERE) {
        return new CypherWhereImpl(node);
      }
      else if (type == WITH) {
        return new CypherWithImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
