package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.inspection;

import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.inspection.CypherExplainWarningInspection;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.NameUtil;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base.BaseIntegrationTest;

public class CypherExplainWarningInspectionTest extends BaseIntegrationTest {

    private DataSourceApi dsApi;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.dsApi = dataSource().neo4j31();
        myFixture.enableInspections(CypherExplainWarningInspection.class);
    }

    public void testNonDataSourceFile_NoHighlight() {
        PsiFile psiFile = myFixture.addFileToProject("a.cyp", "MATCH (a)-->(b) RETURN *");
        myFixture.configureFromExistingVirtualFile(psiFile.getVirtualFile());
        myFixture.checkHighlighting();
    }

    public void testDataSourceFile_NoHighlight() {
        String fileName = NameUtil.createDataSourceFileName(dsApi);
        PsiFile psiFile = myFixture.addFileToProject(fileName,
                "MATCH (a)-->(b) RETURN *");
        myFixture.configureFromExistingVirtualFile(psiFile.getVirtualFile());
        myFixture.checkHighlighting();
    }

    public void testDataSourceFile_HighlightExplainWarning() {
        String fileName = NameUtil.createDataSourceFileName(dsApi);
        PsiFile psiFile = myFixture.addFileToProject(fileName,
                        "MATCH (a)-[r:" +
                                "<warning descr=\"The provided relationship type is not in the database.\">" +
                                "ART</warning>]-(b) RETURN *;");
        myFixture.configureFromExistingVirtualFile(psiFile.getVirtualFile());
        myFixture.checkHighlighting();
    }

    public void testDataSourceFile_NoHighlightQueryError() {
        String fileName = NameUtil.createDataSourceFileName(dsApi);
        PsiFile psiFile = myFixture.addFileToProject(fileName,
                        "MATCH (a)-->() RETURN b;");
        myFixture.configureFromExistingVirtualFile(psiFile.getVirtualFile());
        myFixture.checkHighlighting();
    }

    public void testDataSourceFile_NoHighlightParserError() {
        String fileName = NameUtil.createDataSourceFileName(dsApi);
        PsiFile psiFile = myFixture.addFileToProject(fileName,
                        "MATCH a<error>-</error>->() RETURN *;");
        myFixture.configureFromExistingVirtualFile(psiFile.getVirtualFile());
        myFixture.checkHighlighting();
    }

}
