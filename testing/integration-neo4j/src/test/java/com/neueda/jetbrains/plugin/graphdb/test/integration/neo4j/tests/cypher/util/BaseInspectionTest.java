package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.NameUtil;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base.BaseIntegrationTest;

import java.util.Set;

public abstract class BaseInspectionTest extends BaseIntegrationTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myFixture.enableInspections(provideInspectionClasses());
    }

    protected abstract Set<Class<? extends LocalInspectionTool>> provideInspectionClasses();

    protected void addFileAndCheck(String filePath, String fileContent) {
        PsiFile psiFile = myFixture.addFileToProject(filePath, fileContent);
        configureAndCheck(psiFile.getVirtualFile());
    }

    protected void addDataSourceFileAndCheck(String fileContent) {
        String fileName = NameUtil.createDataSourceFileName(dataSource().neo4j40());
        addFileAndCheck(fileName, fileContent);
    }

    protected void deleteFile() {
        ApplicationManager.getApplication().runWriteAction(() -> myFixture.getFile().delete());
    }

    protected void addUnavailableDataSourceFileAndCheck(String fileContent) {
        String fileName = NameUtil.createDataSourceFileName(dataSource().unavailable());
        addFileAndCheck(fileName, fileContent);
    }

    private void configureAndCheck(VirtualFile virtualFile) {
        myFixture.configureFromExistingVirtualFile(virtualFile);
        myFixture.checkHighlighting();
    }
}
