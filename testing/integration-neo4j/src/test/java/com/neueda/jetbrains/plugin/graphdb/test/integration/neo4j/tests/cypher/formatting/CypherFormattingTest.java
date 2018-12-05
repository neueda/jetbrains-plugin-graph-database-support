package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.formatting;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseFormattingTest;

public class CypherFormattingTest extends BaseFormattingTest {

    public void testFormatter() {
        doTest("match (a:Person{name:'Dmitry'})-[]-(b) where b.title='Neueda' return a,b;",
                "MATCH (a:Person {name: 'Dmitry'})-[]-(b)\n" +
                        "  WHERE b.title = 'Neueda'\n" +
                        "RETURN a, b;");
    }

    public void testMatchParenthesis() {
        doTest("match (a)-->(b) return b", "MATCH (a)-->(b)\nRETURN b");
    }

    public void testLoadCSV() {
        doTest("LOAD CSV FROM 'f' AS line", "LOAD CSV FROM 'f' AS line");
    }

    public void testLoadCSVWithHeaders() {
        doTest("LOAD CSV wiTh HEADERS FROM 'f' AS line", "LOAD CSV WITH HEADERS FROM 'f' AS line");
    }

    public void testPrettifyQuotes() {
        doTest("mATCH (a) WhERE a.name='A' RETURN a.age > 30, \"I'm a literal\", (a)-->()", "MATCH (a)\n  WHERE a.name = 'A'\nRETURN a.age > 30, \"I'm a literal\", (a)-->()");
    }

    public void testExpressionPrecedence() {
        doTest("return (1 + 2) * 3", "RETURN (1 + 2) * 3");
    }

    //Casing tests
    public void testUpperCaseKeywords() {
        doTest("create (n)", "CREATE (n)");
    }

    public void testUpperCaseMultipleKeywords() {
        doTest("match (n) where n.name='B' return n", "MATCH (n)\n  WHERE n.name = 'B'\nRETURN n");
    }

    public void testUpperCaseExtraKeywords() {
        doTest("match (david)--(otherPerson)-->() where david.name='David' with otherPerson, count(*) as foaf where foaf > 1 return otherPerson", "MATCH (david)--(otherPerson)-->()\n  WHERE david.name = 'David'\nWITH otherPerson, COUNT(*) AS foaf\n  WHERE foaf > 1\nRETURN otherPerson");
    }

    public void testLowerCaseValueNull() {
        doTest("WITH NULL AS n1, Null AS n2 RETURN n1, n2", "WITH null AS n1, null AS n2\nRETURN n1, n2");
    }

    public void testUpperCaseIsNull() {
        doTest("WITH null AS n1, null AS n2 RETURN n1 is Null, n2 is not null", "WITH null AS n1, null AS n2\nRETURN n1 IS NULL, n2 IS NOT NULL");
    }

    public void testLowerCaseBooleanLiterals() {
        doTest("WITH TRUE AS n1, False AS n2 RETURN n1 && n2", "WITH true AS n1, false AS n2\nRETURN n1 && n2");
    }

    public void testLowerCaseFunction() {
        doTest("return Rand()", "RETURN rand()");
    }

    public void testStartParamNameLowerCase() {
        doTest("match (n {prop: $Param}) return count(n)", "MATCH (n {prop: $Param})\nRETURN count(n)");
    }

    //Spacing tests
    public void testNoSpaceBetweenKeyAndColon() {
        doTest("MATCH (n:Person {age : 45}) RETURN n.name", "MATCH (n:Person {age: 45})\nRETURN n.name");
    }

    public void testNoSpaceBetweenOpenBraceAndFirstKey() {
        doTest("MATCH (n:Person { age: 45}) RETURN n.name", "MATCH (n:Person {age: 45})\nRETURN n.name");
    }

    public void testOneSpaceBetweenColonAndValue() {
        doTest("MATCH (n:Person {age:45, name:  'Anne'}) RETURN n.name", "MATCH (n:Person {age: 45, name: 'Anne'})\nRETURN n.name");
    }

    public void testOneSpaceBetweenValueAndComma() {
        doTest("MATCH (n:Person {age: 45 , name: 'Anne'}) RETURN n.name", "MATCH (n:Person {age: 45, name: 'Anne'})\nRETURN n.name");
    }

    public void testOneSpaceBetweenCommaAndNextKey() {
        doTest("MATCH (n:Person {age: 45,name: 'Anne',  eyeColour: 'grey'}) RETURN n.name", "MATCH (n:Person {age: 45, name: 'Anne', eyeColour: 'grey'})\nRETURN n.name");
    }

    public void testNoSpaceBetweenLastValAndClosingBrace() {
        doTest("MATCH (n:Person {age: 45 }) RETURN n.name", "MATCH (n:Person {age: 45})\nRETURN n.name");
    }

    public void testOneSpaceBetweenLabelPredicateAndPropPredicate() {
        doTest("MATCH (n:Person{age: 45})-->(m:Person  {age: 30}) RETURN n.name", "MATCH (n:Person {age: 45})-->(m:Person {age: 30})\nRETURN n.name");
    }

    public void testOneSpaceBetweenRelTypePredicateAndPropPredicate() {
        doTest("MATCH (n)-[:KNOWS{since: 2015}]->(m)-[:KNOWS  {since: 2010}]->(o) RETURN n.name", "MATCH (n)-[:KNOWS {since: 2015}]->(m)-[:KNOWS {since: 2010}]->(o)\nRETURN n.name");
    }

    public void testNoSpaceInPatterns() {
        doTest("MATCH (n:Person) - [:DRIVES] -> (:Vehicle) RETURN n.name", "MATCH (n:Person)-[:DRIVES]->(:Vehicle)\nRETURN n.name");
    }

    public void testWrappingSpaceAroundOperators() {
        doTest("MATCH p=(m)-->(n) WHERE m.age<>n.age RETURN n.name", "MATCH p = (m)-->(n)\n  WHERE m.age <> n.age\nRETURN n.name");
    }

    public void testNoSpaceInLabelPredicates() {
        doTest("MATCH (p:  Person  : Owner ) RETURN p.name", "MATCH (p:Person:Owner)\nRETURN p.name");
    }

    public void testOneSpaceAfterCommaInLists() {
        doTest("WITH ['a','b',3.14] AS list RETURN list,2,3,4", "WITH ['a', 'b', 3.14] AS list\nRETURN list, 2, 3, 4");
    }

    public void testNoSpaceWithinFunctionCallParenthesis() {
        doTest("RETURN split( 'original', 'i' )", "RETURN split('original', 'i')");
    }

    //Backtick tests
    public void testHandleBackticksInLabel() {
        doTest("MATCH (n:`Person Employee`) RETURN n.name", "MATCH (n:`Person Employee`)\nRETURN n.name");
    }

    public void testBackticksInAliasesColumn() {
        doTest("MATCH (n) RETURN n.name AS `Column With Space`", "MATCH (n)\nRETURN n.name AS `Column With Space`");
    }

    public void testBackticksInRelType() {
        doTest("MATCH (n)-[r:`Has connection`]->(:Person) RETURN n.name", "MATCH (n)-[r:`Has connection`]->(:Person)\nRETURN n.name");
    }

    public void testBackticksInMultipleRelTypes() {
        doTest("MATCH (n)-[r:`Has connection`|`Has address`]->(:Person) RETURN n.name", "MATCH (n)-[r:`Has connection`|`Has address`]->(:Person)\nRETURN n.name");
    }

    public void testBackticksInPropKey() {
        doTest("MATCH (n {`first name`: 'John'}) RETURN n.name", "MATCH (n {`first name`: 'John'})\nRETURN n.name");
    }

    public void testBackticksInPropName() {
        doTest("MATCH (n) WHERE n.`first name` = 'John' RETURN n.name", "MATCH (n)\n  WHERE n.`first name` = 'John'\nRETURN n.name");
    }

    //indenting tests for subclauses
    public void testWhereOnNewLineAndIndent() {
        doTest("MATCH (n) WHERE n.name = 'John' RETURN n.name", "MATCH (n)\n  WHERE n.name = 'John'\nRETURN n.name");
    }

    public void testOnCreateOnNewLineAndIndented() {
        doTest("merge (n) on create set n.age=32", "MERGE (n)\n  ON CREATE SET n.age = 32");
    }

    public void testOnMatchOnNewLineAndIndented() {
        doTest("merge (n) on match set n.age=32", "MERGE (n)\n  ON MATCH SET n.age = 32");
    }

    public void testSkipOnNewLineAndIndented() {
        doTest("match (n) return n.name skip 3", "MATCH (n)\nRETURN n.name\n  SKIP 3");
    }

    public void testLimitOnNewLineAndIndented() {
        doTest("match (n) return n.name limit 3", "MATCH (n)\nRETURN n.name\n  LIMIT 3");
    }

    public void testOrderByOnNewLineAndIndented() {
        doTest("match (n) return n.name, n.age order by n.age", "MATCH (n)\nRETURN n.name, n.age\n  ORDER BY n.age");
    }

    //positive line-breaking tests
    public void testClauseOnNewLine() {
        doTest("match (n) with n as m return m.name", "MATCH (n)\nWITH n AS m\nRETURN m.name");
    }

    public void testLoadCSVOnNewLine() {
        doTest("MATCH (n) LOAD CSV FROM \"f\" AS line return (n)", "MATCH (n)\nLOAD CSV FROM 'f' AS line\nRETURN (n)");
    }

    public void testJoinHintsOnNewLine() {
        doTest("match (a:A)-->(b:B) USING join ON b return a.prop", "MATCH (a:A)-->(b:B)\nUSING JOIN ON b\nRETURN a.prop");
    }

    public void testIndexHintsOnNewLine() {
        doTest("match (a:A)-->(b:B) USING index b:B ( prop ) return a.prop", "MATCH (a:A)-->(b:B)\nUSING INDEX b:B(prop)\nRETURN a.prop");
    }

    public void testScanHintsOnNewLine() {
        doTest("match (a:A)-->(b:B) USING scan b:B return a.prop", "MATCH (a:A)-->(b:B)\nUSING SCAN b:B\nRETURN a.prop");
    }

    public void testCallOnNewLineAndYieldIsNot() {
        doTest("match (n) call db.indexes() yield state RETURN *", "MATCH (n)\nCALL db.indexes() YIELD state\nRETURN *");
    }

    public void testMergeOnNewLine() {
        doTest("MERGE (a:A) MERGE (b:B) MERGE (a)-[:T]->(b) RETURN *", "MERGE (a:A)\nMERGE (b:B)\nMERGE (a)-[:T]->(b)\nRETURN *");
    }

    public void testUnwindOnNewLine() {
        doTest("WITH [1,2,2] AS coll UNWIND coll AS x RETURN collect(x)", "WITH [1, 2, 2] AS coll\nUNWIND coll AS x\nRETURN collect(x)");
    }

    //positive line-breaking tests with width
    public void ignoreNestedWhereOnNewLineAndIndented() {
        CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(getProject());

        settings.setDefaultRightMargin(26);

        doTest("return [x in range(0,10) where x + 2 = 0 | x^3] as result",
                "RETURN [x IN range(0, 10)\n" +
                "         WHERE x + 2 = 0 | x ^ 3]\n" +
                "       AS result");
    }

    //negative line-breaking tests
    public void testNoBreakOnIndexOn() {
        doTest("create index on :Person(name)", "CREATE INDEX ON :Person(name)");
    }

    public void testNoBreakOnConstraintOn() {
        doTest("create constraint on (person:Person) assert person.age is unique", "CREATE CONSTRAINT ON (person:Person) ASSERT person.age IS UNIQUE");
    }

    public void testNoBreakOnASC() {
        doTest("RETURN n order by n.name asc", "RETURN n\n  ORDER BY n.name ASC");
    }

    public void testNoBreakOnCreateInForeach() {
        doTest("match p=(n) foreach(x in p | create (x)--())",
                "MATCH p = (n)\n" +
                        "FOREACH (x IN p |\n" +
                        "  CREATE (x)--()\n" +
                        ")");
    }

    public void testNoBreakOnCreateInComplexForeach() {
        doTest("match p=(n) foreach(x in p | create (x)--() set x.foo = 'bar') return distinct p;",
                "MATCH p = (n)\n" +
                        "FOREACH (x IN p |\n" +
                        "  CREATE (x)--()\n" +
                        "  SET x.foo = 'bar'\n" +
                        ")\n" +
                        "RETURN DISTINCT p;");
    }

    public void testNoBreakOnStartsWith() {
        doTest("return 'apartment' starts with 'apa' as x", "RETURN 'apartment' STARTS WITH 'apa' AS x");
    }

    public void testNoBreakOnEndsWith() {
        doTest("return 'apartment' ends with 'apa' as x", "RETURN 'apartment' ENDS WITH 'apa' AS x");
    }

    public void testNoBreakOnContains() {
        doTest("return 'apartment' contains 'apa' as x", "RETURN 'apartment' CONTAINS 'apa' AS x");
    }

    public void testNoBreakOnCreateUnique() {
        doTest("start me=node(3) match p1=(me)-[*2]-(friendOfFriend) " +
                        "create p2=(me)-[:MARRIED_TO]-(wife { name: \"Gunhild\" }) " +
                        "create unique p3=(wife)-[:KNOWS]-(friendOfFriend) " +
                        "return p1,p2,p3",
                "START me = node(3)\n" +
                        "MATCH p1 = (me)-[*2]-(friendOfFriend)\n" +
                        "CREATE p2 = (me)-[:MARRIED_TO]-(wife {name: 'Gunhild'})\n" +
                        "CREATE UNIQUE p3 = (wife)-[:KNOWS]-(friendOfFriend)\n" +
                        "RETURN p1, p2, p3"
        );
    }

    public void testNoBreakOnWhereInComprehensions() {
        doTest("return [x in range(0,10) where x + 2 = 0 | x^3] as result", "RETURN [x IN range(0, 10) WHERE x + 2 = 0 | x ^ 3] AS result");
    }

    public void testNoBreakAfterOptional() {
        doTest("optional MATCH (n)-->(x) return n, x", "OPTIONAL MATCH (n)-->(x)\nRETURN n, x");
    }

    public void testNoBreakAfterDetachInDetachDelete() {
        doTest("MATCH (n) DETACH DELETE (n)", "MATCH (n)\nDETACH DELETE (n)");
    }

    // OpenCYPHER tests
    public void testClauseNewLine() {
        doTest("MATCH (n) WHERE n.name CONTAINS 's' RETURN n.name",
                "MATCH (n)\n" +
                        "  WHERE n.name CONTAINS 's'\n" +
                        "RETURN n.name");
    }

    public void testIndentSubClause() {
        doTest("MERGE (n) ON CREATE SET n.prop = 0\n" +
                        "MERGE (a:A)-[:T]-(b:B)\n" +
                        "ON CREATE SET a.name = 'me'\n" +
                        "ON MATCH SET b.name = 'you'\n" +
                        "RETURN a.prop ORDER BY b.prop ASC SKIP 10\n" +
                        "LIMIT 10",
                "MERGE (n)\n" +
                        "  ON CREATE SET n.prop = 0\n" +
                        "MERGE (a:A)-[:T]-(b:B)\n" +
                        "  ON CREATE SET a.name = 'me'\n" +
                        "  ON MATCH SET b.name = 'you'\n" +
                        "RETURN a.prop\n" +
                        "  ORDER BY b.prop ASC\n" +
                        "  SKIP 10\n" +
                        "  LIMIT 10");
    }

    public void testUseSingleQuotes() {
        doTest("RETURN \"Cypher\"", "RETURN 'Cypher'");
    }

    public void testUseDoubleQuotesIfSingleQuotesArePresent() {
        doTest("RETURN 'Cypher\\'s a nice language', \"Mats' quote: \\\"statement\\\"\"",
                "RETURN \"Cypher's a nice language\", 'Mats\\' quote: \"statement\"'");
    }

    public void testLiteralMapsSpacing() {
        doTest("WITH { key1 :'value' ,key2  :  42 } AS map\n" +
                        "RETURN map",
                "WITH {key1: 'value', key2: 42} AS map\n" +
                        "RETURN map");
    }

    public void testNoPaddingSpaceForParams() {
        doTest("RETURN { param }", "RETURN {param}");
    }

    public void testSpaceBetweenLabelTypeAndProps() {
        doTest("MATCH (p:Person{property: -1})-[:KNOWS   {since: 2016}]->()\n" +
                        "RETURN p.name",
                "MATCH (p:Person {property: -1})-[:KNOWS {since: 2016}]->()\n" +
                        "RETURN p.name");
    }

    public void testNoSpaceInPattern() {
        doTest("MATCH (:Person) --> (:Vehicle)\n" +
                        "RETURN count(*)",
                "MATCH (:Person)-->(:Vehicle)\n" +
                        "RETURN COUNT(*)");
    }

    public void testNoSpaceInLabel() {
        doTest("MATCH (person    : Person  :  Owner  )\n" +
                        "RETURN person.name",
                "MATCH (person:Person:Owner)\n" +
                        "RETURN person.name");
    }

    public void testSpaceAfterCommaInLists() {
        doTest("MATCH (),()\n" +
                        "WITH ['a','b',3.14] AS list\n" +
                        "RETURN list,2,3,4",
                "MATCH (), ()\n" +
                        "WITH ['a', 'b', 3.14] AS list\n" +
                        "RETURN list, 2, 3, 4");
    }

    public void testNoPaddingSpaceInFunction() {
        doTest("RETURN split( 'original', 'i' )",
                "RETURN split('original', 'i')");
    }

    public void testEscapedCharsStayEscaped() {
        doTest("RETURN 'I\'m escaping \\n to the new \"line\"'",
                "RETURN 'I\'m escaping \\n to the new \"line\"'");
    }

    public void testAssertFormatting() {
        doTest("CALL apoc.schema.assert({}, {});",
                "CALL apoc.schema.assert({}, {});");
    }
}
