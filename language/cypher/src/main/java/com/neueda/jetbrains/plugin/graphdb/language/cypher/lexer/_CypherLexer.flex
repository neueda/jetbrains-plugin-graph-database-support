package com.neueda.jetbrains.plugin.graphdb.language.cypher.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherParserDefinition.LINE_COMMENT;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherParserDefinition.BLOCK_COMMENT;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes.*;

%%

%{
  public _CypherLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _CypherLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

K_MATCH=[Mm][Aa][Tt][Cc][Hh]
K_RETURN=[Rr][Ee][Tt][Uu][Rr][Nn]
K_DISTINCT=[Dd][Ii][Ss][Tt][Ii][Nn][Cc][Tt]
K_UNION=[Uu][Nn][Ii][Oo][Nn]
K_ALL=[Aa][Ll][Ll]
K_LOAD=[Ll][Oo][Aa][Dd]
K_CSV=[Cc][Ss][Vv]
K_WITH=[Ww][Ii][Tt][Hh]
K_HEADERS=[Hh][Ee][Aa][Dd][Ee][Rr][Ss]
K_FROM=[Ff][Rr][Oo][Mm]
K_AS=[Aa][Ss]
K_FIELDTERMINATOR=[Ff][Ii][Ee][Ll][Dd][Tt][Ee][Rr][Mm][Ii][Nn][Aa][Tt][Oo][Rr]
K_CREATE=[Cc][Rr][Ee][Aa][Tt][Ee]
K_CONSTRAINT=[Cc][Oo][Nn][Ss][Tt][Rr][Aa][Ii][Nn][Tt]
K_ON=[Oo][Nn]
K_ASSERT=[Aa][Ss][Ss][Ee][Rr][Tt]
K_IS=[Ii][Ss]
K_UNIQUE=[Uu][Nn][Ii][Qq][Uu][Ee]
K_EXISTS=[Ee][Xx][Ii][Ss][Tt][Ss]
K_INDEX=[Ii][Nn][Dd][Ee][Xx]
K_DROP=[Dd][Rr][Oo][Pp]
K_START=[Ss][Tt][Aa][Rr][Tt]
K_WHERE=[Ww][Hh][Ee][Rr][Ee]
K_NODE=[Nn][Oo][Dd][Ee]
K_RELATIONSHIP=[Rr][Ee][Ll][Aa][Tt][Ii][Oo][Nn][Ss][Hh][Ii][Pp]
K_REL=[Rr][Ee][Ll]
K_OPTIONAL=[Oo][Pp][Tt][Ii][Oo][Nn][Aa][Ll]
K_USING=[Uu][Ss][Ii][Nn][Gg]
K_JOIN=[Jj][Oo][Ii][Nn]
K_SCAN=[Ss][Cc][Aa][Nn]
K_SHORTESTPATH=[Ss][Hh][Oo][Rr][Tt][Ee][Ss][Tt][Pp][Aa][Tt][Hh]
K_ALLSHORTESTPATHS=[Aa][Ll][Ll][Ss][Hh][Oo][Rr][Tt][Ee][Ss][Tt][Pp][Aa][Tt][Hh][Ss]
K_UNWIND=[Uu][Nn][Ww][Ii][Nn][Dd]
K_MERGE=[Mm][Ee][Rr][Gg][Ee]
K_SET=[Ss][Ee][Tt]
K_DELETE=[Dd][Ee][Ll][Ee][Tt][Ee]
K_DETACH=[Dd][Ee][Tt][Aa][Cc][Hh]
K_REMOVE=[Rr][Ee][Mm][Oo][Vv][Ee]
K_FOREACH=[Ff][Oo][Rr][Ee][Aa][Cc][Hh]
K_IN=[Ii][Nn]
K_ORDER=[Oo][Rr][Dd][Ee][Rr]
K_BY=[Bb][Yy]
K_DESCENDING=[Dd][Ee][Ss][Cc][Ee][Nn][Dd][Ii][Nn][Gg]
K_DESC=[Dd][Ee][Ss][Cc]
K_ASCENDING=[Aa][Ss][Cc][Ee][Nn][Dd][Ii][Nn][Gg]
K_ASC=[Aa][Ss][Cc]
K_SKIP=[Ss][Kk][Ii][Pp]
K_LIMIT=[Ll][Ii][Mm][Ii][Tt]
K_PERIODIC=[Pp][Ee][Rr][Ii][Oo][Dd][Ii][Cc]
K_BEGIN=[Bb][Ee][Gg][Ii][Nn]
K_COMMIT=[Cc][Oo][Mm][Mm][Ii][Tt]
K_XOR=[Xx][Oo][Rr]
K_OR=[Oo][Rr]
K_AND=[Aa][Nn][Dd]
K_NOT=[Nn][Oo][Tt]
K_STARTS=[Ss][Tt][Aa][Rr][Tt][Ss]
K_ENDS=[Ee][Nn][Dd][Ss]
K_CONTAINS=[Cc][Oo][Nn][Tt][Aa][Ii][Nn][Ss]
K_NULL=[Nn][Uu][Ll][Ll]
K_TRUE=[Tt][Rr][Uu][Ee]
K_FALSE=[Ff][Aa][Ll][Ss][Ee]
K_FILTER=[Ff][Ii][Ll][Tt][Ee][Rr]
K_EXTRACT=[Ee][Xx][Tt][Rr][Aa][Cc][Tt]
K_REDUCE=[Rr][Ee][Dd][Uu][Cc][Ee]
K_ANY=[Aa][Nn][Yy]
K_NONE=[Nn][Oo][Nn][Ee]
K_SINGLE=[Ss][Ii][Nn][Gg][Ll][Ee]
K_CASE=[Cc][Aa][Ss][Ee]
K_ELSE=[Ee][Ll][Ss][Ee]
K_END=[Ee][Nn][Dd]
K_WHEN=[Ww][Hh][Ee][Nn]
K_THEN=[Tt][Hh][Ee][Nn]
K_PROFILE=[Pp][Rr][Oo][Ff][Ii][Ll][Ee]
K_EXPLAIN=[Ee][Xx][Pp][Ll][Aa][Ii][Nn]
K_CYPHER=[Cc][Yy][Pp][Hh][Ee][Rr]
K_CALL=[Cc][Aa][Ll][Ll]
K_YIELD=[Yy][Ii][Ee][Ll][Dd]
K_COUNT=[Cc][Oo][Uu][Nn][Tt]
K_DO=[Dd][Oo]
K_FOR=[Ff][Oo][Rr]
K_REQUIRE=[Rr][Ee][Qq][Uu][Ii][Rr][Ee]
K_MANDATORY=[Mm][Aa][Nn][Dd][Aa][Tt][Oo][Rr][Yy]
K_SCALAR=[Ss][Cc][Aa][Ll][Aa][Rr]
K_OF=[Oo][Ff]
K_ADD=[Aa][Dd][Dd]
L_IDENTIFIER=[a-zA-Z_][a-zA-Z_$0-9]*
L_IDENTIFIER_TEXT=\`[^`]+\`
L_DECIMAL=(0|[1-9][0-9]*)\.[0-9]+
L_INTEGER=0|[1-9][0-9]*
L_STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")

LINE_COMMENT = "//" [^\r\n]*
BLOCK_COMMENT = "/*" ( ([^"*"]|[\r\n])* ("*"+ [^"*""/"] )? )* ("*" | "*"+"/")?

%%
<YYINITIAL> {
  {WHITE_SPACE}             { return WHITE_SPACE; }

  {LINE_COMMENT}            { return LINE_COMMENT; }
  {BLOCK_COMMENT}           { return BLOCK_COMMENT; }

  ";"                       { return SEMICOLON; }
  "("                       { return PARENTHESE_OPEN; }
  ")"                       { return PARENTHESE_CLOSE; }
  "{"                       { return BRACKET_CURLYOPEN; }
  "}"                       { return BRACKET_CURLYCLOSE; }
  "["                       { return BRACKET_SQUAREOPEN; }
  "]"                       { return BRACKET_SQUARECLOSE; }
  "$"                       { return DOLLAR; }
  ":"                       { return OP_COLON; }
  "."                       { return OP_DOT; }
  "="                       { return OP_EQUAL; }
  "<"                       { return OP_LESSTHEN; }
  ">"                       { return OP_GREATHERTHEN; }
  "+"                       { return OP_PLUS; }
  "-"                       { return OP_MINUS; }
  "*"                       { return OP_MUL; }
  "`"                       { return OP_BACTICK; }
  ","                       { return OP_COMMA; }
  "?"                       { return OP_QUESTIONSIGN; }
  "|"                       { return OP_PIPE; }
  ".."                      { return OP_RANGE; }
  "+="                      { return OP_PLUSEQUALS; }
  "<>"                      { return OP_INVALIDNOTEQUALS; }
  "!="                      { return OP_NOTEQUALS; }
  "<="                      { return OP_LESSTHANEQUALS; }
  ">="                      { return OP_GREATERTHANEQUALS; }
  "/"                       { return OP_DIVIDE; }
  "%"                       { return OP_MODULO; }
  "^"                       { return OP_POW; }
  "=~"                      { return OP_REGEXMATCH; }

  {K_MATCH}                 { return K_MATCH; }
  {K_RETURN}                { return K_RETURN; }
  {K_DISTINCT}              { return K_DISTINCT; }
  {K_UNION}                 { return K_UNION; }
  {K_ALL}                   { return K_ALL; }
  {K_LOAD}                  { return K_LOAD; }
  {K_CSV}                   { return K_CSV; }
  {K_WITH}                  { return K_WITH; }
  {K_HEADERS}               { return K_HEADERS; }
  {K_FROM}                  { return K_FROM; }
  {K_AS}                    { return K_AS; }
  {K_FIELDTERMINATOR}       { return K_FIELDTERMINATOR; }
  {K_CREATE}                { return K_CREATE; }
  {K_CONSTRAINT}            { return K_CONSTRAINT; }
  {K_ON}                    { return K_ON; }
  {K_ASSERT}                { return K_ASSERT; }
  {K_IS}                    { return K_IS; }
  {K_UNIQUE}                { return K_UNIQUE; }
  {K_EXISTS}                { return K_EXISTS; }
  {K_INDEX}                 { return K_INDEX; }
  {K_DROP}                  { return K_DROP; }
  {K_START}                 { return K_START; }
  {K_WHERE}                 { return K_WHERE; }
  {K_NODE}                  { return K_NODE; }
  {K_RELATIONSHIP}          { return K_RELATIONSHIP; }
  {K_REL}                   { return K_REL; }
  {K_OPTIONAL}              { return K_OPTIONAL; }
  {K_USING}                 { return K_USING; }
  {K_JOIN}                  { return K_JOIN; }
  {K_SCAN}                  { return K_SCAN; }
  {K_SHORTESTPATH}          { return K_SHORTESTPATH; }
  {K_ALLSHORTESTPATHS}      { return K_ALLSHORTESTPATHS; }
  {K_UNWIND}                { return K_UNWIND; }
  {K_MERGE}                 { return K_MERGE; }
  {K_SET}                   { return K_SET; }
  {K_DELETE}                { return K_DELETE; }
  {K_DETACH}                { return K_DETACH; }
  {K_REMOVE}                { return K_REMOVE; }
  {K_FOREACH}               { return K_FOREACH; }
  {K_IN}                    { return K_IN; }
  {K_ORDER}                 { return K_ORDER; }
  {K_BY}                    { return K_BY; }
  {K_DESCENDING}            { return K_DESCENDING; }
  {K_DESC}                  { return K_DESC; }
  {K_ASCENDING}             { return K_ASCENDING; }
  {K_ASC}                   { return K_ASC; }
  {K_SKIP}                  { return K_SKIP; }
  {K_LIMIT}                 { return K_LIMIT; }
  {K_PERIODIC}              { return K_PERIODIC; }
  {K_BEGIN}                 { return K_BEGIN; }
  {K_COMMIT}                { return K_COMMIT; }
  {K_XOR}                   { return K_XOR; }
  {K_OR}                    { return K_OR; }
  {K_AND}                   { return K_AND; }
  {K_NOT}                   { return K_NOT; }
  {K_STARTS}                { return K_STARTS; }
  {K_ENDS}                  { return K_ENDS; }
  {K_CONTAINS}              { return K_CONTAINS; }
  {K_NULL}                  { return K_NULL; }
  {K_TRUE}                  { return K_TRUE; }
  {K_FALSE}                 { return K_FALSE; }
  {K_FILTER}                { return K_FILTER; }
  {K_EXTRACT}               { return K_EXTRACT; }
  {K_REDUCE}                { return K_REDUCE; }
  {K_ANY}                   { return K_ANY; }
  {K_NONE}                  { return K_NONE; }
  {K_SINGLE}                { return K_SINGLE; }
  {K_CASE}                  { return K_CASE; }
  {K_ELSE}                  { return K_ELSE; }
  {K_END}                   { return K_END; }
  {K_WHEN}                  { return K_WHEN; }
  {K_THEN}                  { return K_THEN; }
  {K_PROFILE}               { return K_PROFILE; }
  {K_EXPLAIN}               { return K_EXPLAIN; }
  {K_CYPHER}                { return K_CYPHER; }
  {K_CALL}                  { return K_CALL; }
  {K_YIELD}                 { return K_YIELD; }
  {K_COUNT}                 { return K_COUNT; }
  {K_DO}                    { return K_DO; }
  {K_FOR}                   { return K_FOR; }
  {K_REQUIRE}               { return K_REQUIRE; }
  {K_MANDATORY}             { return K_MANDATORY; }
  {K_SCALAR}                { return K_SCALAR; }
  {K_OF}                    { return K_OF; }
  {K_ADD}                   { return K_ADD; }
  {L_IDENTIFIER}            { return L_IDENTIFIER; }
  {L_IDENTIFIER_TEXT}       { return L_IDENTIFIER_TEXT; }
  {L_DECIMAL}               { return L_DECIMAL; }
  {L_INTEGER}               { return L_INTEGER; }
  {L_STRING}                { return L_STRING; }

}

[^] { return BAD_CHARACTER; }
