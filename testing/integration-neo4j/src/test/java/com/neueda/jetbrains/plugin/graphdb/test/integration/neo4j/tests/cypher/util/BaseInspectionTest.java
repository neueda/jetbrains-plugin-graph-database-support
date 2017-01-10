package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.NameUtil;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base.BaseIntegrationTest;

import java.util.Optional;
import java.util.Set;

public abstract class BaseInspectionTest extends BaseIntegrationTest {
    private String dsApiUUID;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.dsApiUUID = dataSource().neo4j31().getUUID();
        myFixture.enableInspections(provideInspectionClasses());
    }

    protected abstract Set<Class<? extends LocalInspectionTool>> provideInspectionClasses();

    protected void addFileAndCheck(String filePath, String fileContent) {
        PsiFile psiFile = myFixture.addFileToProject(filePath, fileContent);
        configureAndCheck(psiFile.getVirtualFile());
    }

    protected void addDataSourceFileAndCheck(String fileContent) {
        String fileName = Optional.of(dsApiUUID)
                .flatMap(uuid -> component().dataSources().getDataSourceContainer().findDataSource(uuid))
                .map(NameUtil::createDataSourceFileName)
                .orElseThrow(IllegalStateException::new);

        addFileAndCheck(fileName, fileContent);
    }

    private void configureAndCheck(VirtualFile virtualFile) {
        myFixture.configureFromExistingVirtualFile(virtualFile);
        myFixture.checkHighlighting();
    }
}
