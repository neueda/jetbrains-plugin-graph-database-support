package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.formatting;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherLanguage;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base.BaseIntegrationTest;

import static java.util.Collections.singletonList;

public class CypherFormattingTest extends BaseIntegrationTest {

    public void testFormatter() {
        PsiFile file = myFixture.addFileToProject("test.cypher", "match (a:Person)-[]-(b) return a,b;");
        myFixture.configureFromExistingVirtualFile(file.getVirtualFile());

        CommonCodeStyleSettings settings = CodeStyleSettingsManager.getSettings(getProject())
                .getCommonSettings(CypherLanguage.INSTANCE);
        settings.SPACE_AFTER_COLON = true;
        settings.SPACE_WITHIN_BRACKETS = true;
        settings.SPACE_AFTER_COMMA = true;

        new WriteCommandAction.Simple(getProject()) {
            @Override
            protected void run() throws Throwable {
                PsiFile f = myFixture.getFile();
                CodeStyleManager.getInstance(getProject())
                        .reformatText(f, singletonList(f.getTextRange()));
            }
        }.execute();

        myFixture.checkResult("match ( a: Person )-[]-( b ) return a, b;");
    }
}
