package com.neueda.jetbrains.plugin.graphdb.language.cypher.lexer;

import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
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
WHITE_SPACE=\s

K_MATCH=(M|m)(A|a)(T|t)(C|c)(H|h)
K_RETURN=(R|r)(E|e)(T|t)(U|u)(R|r)(N|n)
K_DISTINCT=(D|d)(I|i)(S|s)(T|t)(I|i)(N|n)(C|c)(T|t)
K_UNION=(U|u)(N|n)(I|i)(O|o)(N|n)
K_ALL=(A|a)(L|l)(L|l)
K_LOAD=(L|l)(O|o)(A|a)(D|d)
K_CSV=(C|c)(S|s)(V|v)
K_WITH=(W|w)(I|i)(T|t)(H|h)
K_HEADERS=(H|h)(E|e)(A|a)(D|d)(E|e)(R|r)(S|s)
K_FROM=(F|f)(R|r)(O|o)(M|m)
K_AS=(A|a)(S|s)
K_FIELDTERMINATOR=(F|f)(I|i)(E|e)(L|l)(D|d)(T|t)(E|e)(R|r)(M|m)(I|i)(N|n)(A|a)(T|t)(O|o)(R|r)
K_CREATE=(C|c)(R|r)(E|e)(A|a)(T|t)(E|e)
K_CONSTRAINT=(C|c)(O|o)(N|n)(S|s)(T|t)(R|r)(A|a)(I|i)(N|n)(T|t)
K_ON=(O|o)(N|n)
K_ASSERT=(A|a)(S|s)(S|s)(E|e)(R|r)(T|t)
K_IS=(I|i)(S|s)
K_UNIQUE=(U|u)(N|n)(I|i)(Q|q)(U|u)(E|e)
K_EXISTS=(E|e)(X|x)(I|i)(S|s)(T|t)(S|s)
K_INDEX=(I|i)(N|n)(D|d)(E|e)(X|x)
K_DROP=(D|d)(R|r)(O|o)(P|p)
K_START=(S|s)(T|t)(A|a)(R|r)(T|t)
K_WHERE=(W|w)(H|h)(E|e)(R|r)(E|e)
K_NODE=(N|n)(O|o)(D|d)(E|e)
K_RELATIONSHIP=(R|r)(E|e)(L|l)(A|a)(T|t)(I|i)(O|o)(N|n)(S|s)(H|h)(I|i)(P|p)
K_REL=(R|r)(E|e)(L|l)
K_OPTIONAL=(O|o)(P|p)(T|t)(I|i)(O|o)(N|n)(A|a)(L|l)
K_USING=(U|u)(S|s)(I|i)(N|n)(G|g)
K_JOIN=(J|j)(O|o)(I|i)(N|n)
K_SCAN=(S|s)(C|c)(A|a)(N|n)
K_SHORTESTPATH=(S|s)(H|h)(O|o)(R|r)(T|t)(E|e)(S|s)(T|t)(P|p)(A|a)(T|t)(H|h)
K_ALLSHORTESTPATHS=(A|a)(L|l)(L|l)(S|s)(H|h)(O|o)(R|r)(T|t)(E|e)(S|s)(T|t)(P|p)(A|a)(T|t)(H|h)(S|s)
K_UNWIND=(U|u)(N|n)(W|w)(I|i)(N|n)(D|d)
K_MERGE=(M|m)(E|e)(R|r)(G|g)(E|e)
K_SET=(S|s)(E|e)(T|t)
K_DELETE=(D|d)(E|e)(L|l)(E|e)(T|t)(E|e)
K_DETACH=(D|d)(E|e)(T|t)(A|a)(C|c)(H|h)
K_REMOVE=(R|r)(E|e)(M|m)(O|o)(V|v)(E|e)
K_FOREACH=(F|f)(O|o)(R|r)(E|e)(A|a)(C|c)(H|h)
K_IN=(I|i)(N|n)
K_ORDER=(O|o)(R|r)(D|d)(E|e)(R|r)
K_BY=(B|b)(Y|y)
K_DESCENDING=(D|d)(E|e)(S|s)(C|c)(E|e)(N|n)(D|d)(I|i)(N|n)(G|g)
K_DESC=(D|d)(E|e)(S|s)(C|c)
K_ASCENDING=(A|a)(S|s)(C|c)(E|e)(N|n)(D|d)(I|i)(N|n)(G|g)
K_ASC=(A|a)(S|s)(C|c)
K_SKIP=(S|s)(K|k)(I|i)(P|p)
K_LIMIT=(L|l)(I|i)(M|m)(I|i)(T|t)
K_PERIODIC=(P|p)(E|e)(R|r)(I|i)(O|o)(D|d)(I|i)(C|c)
K_BEGIN=(B|b)(E|e)(G|g)(I|i)(N|n)
K_COMMIT=(C|c)(O|o)(M|m)(M|m)(I|i)(T|t)
K_XOR=(X|x)(O|o)(R|r)
K_OR=(O|o)(R|r)
K_AND=(A|a)(N|n)(D|d)
K_NOT=(N|n)(O|o)(T|t)
K_STARTS=(S|s)(T|t)(A|a)(R|r)(T|t)(S|s)
K_ENDS=(E|e)(N|n)(D|d)(S|s)
K_CONTAINS=(C|c)(O|o)(N|n)(T|t)(A|a)(I|i)(N|n)(S|s)
K_NULL=(N|n)(U|u)(L|l)(L|l)
K_TRUE=(T|t)(R|r)(U|u)(E|e)
K_FALSE=(F|f)(A|a)(L|l)(S|s)(E|e)
K_FILTER=(F|f)(I|i)(L|l)(T|t)(E|e)(R|r)
K_EXTRACT=(E|e)(X|x)(T|t)(R|r)(A|a)(C|c)(T|t)
K_REDUCE=(R|r)(E|e)(D|d)(U|u)(C|c)(E|e)
K_ANY=(A|a)(N|n)(Y|y)
K_NONE=(N|n)(O|o)(N|n)(E|e)
K_SINGLE=(S|s)(I|i)(N|n)(G|g)(L|l)(E|e)
K_CASE=(C|c)(A|a)(S|s)(E|e)
K_ELSE=(E|e)(L|l)(S|s)(E|e)
K_END=(E|e)(N|n)(D|d)
K_WHEN=(W|w)(H|h)(E|e)(N|n)
K_THEN=(T|t)(H|h)(E|e)(N|n)
K_PROFILE=(P|p)(R|r)(O|o)(F|f)(I|i)(L|l)(E|e)
K_EXPLAIN=(E|e)(X|x)(P|p)(L|l)(A|a)(I|i)(N|n)
K_CYPHER=(C|c)(Y|y)(P|p)(H|h)(E|e)(R|r)
K_CALL=(C|c)(A|a)(L|l)(L|l)
K_YIELD=(Y|y)(I|i)(E|e)(L|l)(D|d)
L_IDENTIFIER=[a-zA-Z_$][a-zA-Z_$0-9]*
L_IDENTIFIER_TEXT=\`[^`]+\`
L_DECIMAL=[+-]?(([1-9][0-9]+)|([0-9]))\.[0-9]+
L_INTEGER=[+-]?(([1-9][0-9]+)|([0-9]))
L_STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
LINECOMMENT="//".*
BLOCKCOMMENT="/"\*(.|\n)*\*"/"

%%
<YYINITIAL> {
  {WHITE_SPACE}             { return com.intellij.psi.TokenType.WHITE_SPACE; }

  ";"                       { return SEMICOLON; }
  "("                       { return PARENTHESE_OPEN; }
  ")"                       { return PARENTHESE_CLOSE; }
  "{"                       { return BRACKET_CURLYOPEN; }
  "}"                       { return BRACKET_CURLYCLOSE; }
  "["                       { return BRACKET_SQUAREOPEN; }
  "]"                       { return BRACKET_SQUARECLOSE; }
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
  {L_IDENTIFIER}            { return L_IDENTIFIER; }
  {L_IDENTIFIER_TEXT}       { return L_IDENTIFIER_TEXT; }
  {L_DECIMAL}               { return L_DECIMAL; }
  {L_INTEGER}               { return L_INTEGER; }
  {L_STRING}                { return L_STRING; }
  {LINECOMMENT}             { return LINECOMMENT; }
  {BLOCKCOMMENT}            { return BLOCKCOMMENT; }

}

[^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
