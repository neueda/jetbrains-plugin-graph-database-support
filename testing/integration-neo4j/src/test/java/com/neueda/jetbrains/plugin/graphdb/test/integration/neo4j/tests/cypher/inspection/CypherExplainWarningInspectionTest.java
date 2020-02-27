package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.inspection;

import com.intellij.codeInspection.LocalInspectionTool;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.inspection.CypherExplainWarningInspection;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseInspectionTest;

import java.util.Set;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;

public class CypherExplainWarningInspectionTest extends BaseInspectionTest {

    @Override
    protected Set<Class<? extends LocalInspectionTool>> provideInspectionClasses() {
        return singleton(CypherExplainWarningInspection.class);
    }

    public void testNonDataSourceFile_NoHighlight() {
        addFileAndCheck("a.cyp", "MATCH (a)-->(b) RETURN *");
    }

    public void testDataSourceFile_NoHighlight() {
        addDataSourceFileAndCheck("MATCH (a)-->(b) RETURN *");
    }

    public void testDataSourceFile_HighlightExplainWarning() {
//TODO: investigate if there's way of improvement of warning tag positioning, as it was more logical prior neo4.0

//previous positioning:// addDataSourceFileAndCheck("MATCH (a)-[r:<warning descr=\"The provided relationship type is not in the database.\">ART</warning>]-(b) RETURN *;");
        addDataSourceFileAndCheck("MATCH (a)-[r:ART]-(b)<warning descr=\"The provided relationship type is not in the database.\"> </warning>RETURN *;");
    }

    public void testDataSourceFile_NoHighlightQueryError() {
        addDataSourceFileAndCheck("MATCH (a)-->() RETURN b;");
    }

    public void testDataSourceFile_NoHighlightParserError() {
        addDataSourceFileAndCheck("MATCH a<error>-</error>->() RETURN *;");
    }

    public void testDataSourceFile_NoDataSource() {
        component().dataSources().getDataSourceContainer().removeDataSources(singletonList(dataSource().neo4j40()));
        addFileAndCheck(GraphConstants.BOUND_DATA_SOURCE_PREFIX + "imaginary-ds-uuid-with-36-symbols-in.cypher",
                "MATCH (a:Turbo)-->() RETURN *;");
    }

    public void testDataSourceFile_UserCreatedDSLikeFile() {
        component().dataSources().getDataSourceContainer().removeDataSources(singletonList(dataSource().neo4j40()));
        // uuid should be 36 symbols long, let's assume user created a file with a name, starting like ds file
        // but does not match the expected format
        addFileAndCheck(GraphConstants.BOUND_DATA_SOURCE_PREFIX + "ds-uuid-with-23-symbols.cypher",
                "MATCH (a:Turbo)-->() RETURN *;");
    }

    public void testDataSourceFile_DBIsUnavailable() {
        //no highlight and no exception
        addUnavailableDataSourceFileAndCheck("MATCH (a:Turbo)-->() RETURN *;");
    }
}
