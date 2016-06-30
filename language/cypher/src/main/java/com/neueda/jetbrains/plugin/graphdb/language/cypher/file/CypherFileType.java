package com.neueda.jetbrains.plugin.graphdb.language.cypher.file;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

/**
 * Describe Cypher file basic metadata.
 *
 * @author dmitry@vrublevsky.me
 */
public final class CypherFileType extends LanguageFileType {

    public static final CypherFileType INSTANCE = new CypherFileType();

    private CypherFileType() {
        super(CypherLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Cypher";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Cypher query language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "cyp";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return CypherIcons.FILE;
    }
}
