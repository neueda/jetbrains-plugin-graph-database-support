package com.neueda.jetbrains.plugin.graphdb.jetbrains.formatter;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CypherLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @NotNull
    @Override
    public Language getLanguage() {
        return CypherLanguage.INSTANCE;
    }

    @Nullable
    @Override
    public IndentOptionsEditor getIndentOptionsEditor() {
        return new SmartIndentOptionsEditor();
    }

    @Nullable
    @Override
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        CommonCodeStyleSettings settings = new CommonCodeStyleSettings(getLanguage());
        CommonCodeStyleSettings.IndentOptions indentOptions = settings.initIndentOptions();
        indentOptions.INDENT_SIZE = 2;
        indentOptions.CONTINUATION_INDENT_SIZE = 2;
        indentOptions.TAB_SIZE = 2;
        indentOptions.USE_TAB_CHARACTER = false;

        return settings;
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "MATCH (tom:Person {name:\"Tom Hanks\"})-[:ACTED_IN]->(m)<-[:ACTED_IN]-(coActors)," +
                "(coActors)-[:ACTED_IN]->(m2)<-[:ACTED_IN]-(cocoActors) " +
                "WHERE NOT (tom)-[:ACTED_IN]->(m2) " +
                "RETURN cocoActors.name AS Recommended, count(*) AS Strength " +
                "ORDER BY Strength DESC;";
    }
}
