package com.neueda.jetbrains.plugin.graphdb.jetbrains.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class CypherCodeStyleSettings extends CustomCodeStyleSettings {

    public boolean SPACE_AFTER_COLON = false;
    public boolean SPACE_BEFORE_COLON = false;

    CypherCodeStyleSettings(CodeStyleSettings container) {
        super("CypherCodeStyleSettings", container);
    }
}
