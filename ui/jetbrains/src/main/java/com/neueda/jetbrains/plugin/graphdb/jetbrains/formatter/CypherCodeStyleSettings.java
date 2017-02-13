package com.neueda.jetbrains.plugin.graphdb.jetbrains.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class CypherCodeStyleSettings extends CustomCodeStyleSettings {
    CypherCodeStyleSettings(CodeStyleSettings container) {
        super("CypherCodeStyleSettings", container);
    }
}
