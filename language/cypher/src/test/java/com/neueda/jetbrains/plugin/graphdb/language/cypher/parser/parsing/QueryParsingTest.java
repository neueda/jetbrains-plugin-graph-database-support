package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.parsing;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util.BaseParsingTest;

public class QueryParsingTest extends BaseParsingTest {

    public QueryParsingTest() {
        super("query");
    }

    public void testMultipleQueries() {
        doTest(true);
    }

    public void testBulkImport() {
        doTest(true);
    }

    public void testRegular_Clause_Call() {
        doTest(true);
    }

    public void testRegular_Clause_Create() {
        doTest(true);
    }

    public void testRegular_Clause_Delete() {
        doTest(true);
    }

    public void testRegular_Clause_Foreach() {
        doTest(true);
    }

    public void testRegular_Clause_LoadCSV() {
        doTest(true);
    }

    public void testRegular_Clause_Match() {
        doTest(true);
    }

    public void testRegular_Clause_Merge() {
        doTest(true);
    }

    public void testRegular_Clause_Remove() {
        doTest(true);
    }

    public void testRegular_Clause_Return() {
        doTest(true);
    }

    public void testRegular_Clause_Set() {
        doTest(true);
    }

    public void testRegular_Clause_Start() {
        doTest(true);
    }

    public void testRegular_Clause_Union() {
        doTest(true);
    }

    public void testRegular_Clause_Unwind() {
        doTest(true);
    }

    public void testRegular_Clause_With() {
        doTest(true);
    }
}
