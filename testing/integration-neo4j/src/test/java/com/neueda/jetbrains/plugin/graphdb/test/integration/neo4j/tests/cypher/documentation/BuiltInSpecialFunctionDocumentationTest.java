package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.documentation;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database.CypherDocumentation;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseDocumentationTest;

public class BuiltInSpecialFunctionDocumentationTest extends BaseDocumentationTest {

    private String shortestPathDocumentation;
    private String allShortestPathsDocumentation;
    private String filterDocumentation;
    private String extractDocumentation;
    private String reduceDocumentation;
    private String allDocumentation;
    private String anyDocumentation;
    private String noneDocumentation;
    private String singleDocumentation;
    private String existsDocumentation;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        shortestPathDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("shortestpath").get();
        allShortestPathsDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("allshortestpaths").get();
        filterDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("filter").get();
        extractDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("extract").get();
        reduceDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("reduce").get();
        allDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("all").get();
        anyDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("any").get();
        noneDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("none").get();
        singleDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("single").get();
        existsDocumentation = CypherDocumentation.BUILT_IN_FUNCTIONS.lookup("exists").get();
    }

    public void testShortestPath() throws Exception {
        configure("RETURN shortest<caret>Path((n));");
        verify(shortestPathDocumentation);
    }

    public void testShortestPathParameters() throws Exception {
        configure("RETURN shortestPath((n<caret>));");
        verify(shortestPathDocumentation);
    }

    public void testAllShortestPaths() throws Exception {
        configure("RETURN allShortest<caret>Paths((n));");
        verify(allShortestPathsDocumentation);
    }

    public void testAllShortestPathsParameters() throws Exception {
        configure("RETURN allShortestPaths((n<caret>));");
        verify(allShortestPathsDocumentation);
    }

    public void testFilter() throws Exception {
        configure("RETURN filt<caret>er(var IN expr WHERE expr)");
        verify(filterDocumentation);
    }

    public void testFilterParameters() throws Exception {
        configure("RETURN filter(var IN e<caret>xpr WHERE expr)");
        verify(filterDocumentation);
    }

    public void testExtract() throws Exception {
        configure("RETURN extra<caret>ct(var IN expr | expr);");
        verify(extractDocumentation);
    }

    public void testExtractParameters() throws Exception {
        configure("RETURN extract(var IN expr |<caret> expr);");
        verify(extractDocumentation);
    }

    public void testReduce() throws Exception {
        configure("RETURN reduc<caret>e(var = expr, var IN expr | expr);");
        verify(reduceDocumentation);
    }

    public void testReduceParameters() throws Exception {
        configure("RETURN reduce(var = expr, var I<caret>N expr | expr);");
        verify(reduceDocumentation);
    }

    public void testAll() throws Exception {
        configure("RETURN all<caret>(var IN expr);");
        verify(allDocumentation);
    }

    public void testAllParameters() throws Exception {
        configure("RETURN all(var I<caret>N expr);");
        verify(allDocumentation);
    }

    public void testAny() throws Exception {
        configure("RETURN any<caret>(var IN expr);");
        verify(anyDocumentation);
    }

    public void testAnyParameters() throws Exception {
        configure("RETURN any(var <caret>IN expr);");
        verify(anyDocumentation);
    }

    public void testNone() throws Exception {
        configure("RETURN non<caret>e(var IN expr);");
        verify(noneDocumentation);
    }

    public void testNoneParameters() throws Exception {
        configure("RETURN none(var <caret>IN expr);");
        verify(noneDocumentation);
    }

    public void testSingle() throws Exception {
        configure("RETURN sing<caret>le(var in expr);");
        verify(singleDocumentation);
    }

    public void testSingleParameters() throws Exception {
        configure("RETURN single(var <caret>in expr);");
        verify(singleDocumentation);
    }

    public void testExists() throws Exception {
        configure("RETURN exis<caret>ts(expr);");
        verify(existsDocumentation);
    }

    public void testExistsParameters() throws Exception {
        configure("RETURN exists(ex<caret>pr);");
        verify(existsDocumentation);
    }
}
