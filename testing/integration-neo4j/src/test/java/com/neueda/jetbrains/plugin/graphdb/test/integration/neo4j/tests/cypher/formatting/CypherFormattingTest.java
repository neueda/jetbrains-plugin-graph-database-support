package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.formatting;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.formatter.CypherCodeStyleSettings;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherLanguage;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base.BaseIntegrationTest;

import static java.util.Collections.singletonList;

public class CypherFormattingTest extends BaseIntegrationTest {

    public void testFormatter() {
        PsiFile file = myFixture.addFileToProject("test.cypher",
                "match (a:Person{name:'Dmitry'})-[]-(b) where b.title='Neueda' return a,b;");
        myFixture.configureFromExistingVirtualFile(file.getVirtualFile());

        CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(getProject());
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(CypherLanguage.INSTANCE);
        CypherCodeStyleSettings customSettings = settings.getCustomSettings(CypherCodeStyleSettings.class);

        customSettings.SPACE_AFTER_COLON = true;
        commonSettings.SPACE_WITHIN_BRACKETS = true;
        commonSettings.SPACE_AFTER_COMMA = true;

        new WriteCommandAction.Simple(getProject()) {
            @Override
            protected void run() throws Throwable {
                PsiFile f = myFixture.getFile();
                CodeStyleManager.getInstance(getProject())
                        .reformatText(f, singletonList(f.getTextRange()));
            }
        }.execute();

        myFixture.checkResult("match ( a: Person {name: 'Dmitry'} )-[]-( b )\n" +
                "where b.title = 'Neueda'\n" +
                "return a, b;");
    }
}
