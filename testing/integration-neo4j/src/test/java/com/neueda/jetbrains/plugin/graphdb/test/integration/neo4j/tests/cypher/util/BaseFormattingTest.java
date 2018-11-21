package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base.BaseIntegrationTest;

import static java.util.Collections.singletonList;

public abstract class BaseFormattingTest extends BaseIntegrationTest {
    protected void doTest(String actual, String expected) {
        PsiFile file = myFixture.addFileToProject("test.cypher", actual);
        myFixture.configureFromExistingVirtualFile(file.getVirtualFile());

        WriteCommandAction.runWriteCommandAction(getProject(), () -> {
            PsiFile f = myFixture.getFile();
            CodeStyleManager.getInstance(getProject())
                    .reformatText(f, singletonList(f.getTextRange()));
        });

        myFixture.checkResult(expected);
    }
}
