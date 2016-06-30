package com.neueda.jetbrains.plugin.graphdb.language.cypher.settings;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherIcons;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.highlight.CypherSyntaxHighlighter;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.highlight.CypherTextAttributeKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.util.Map;

/**
 * TODO: Description
 *
 * @author dmitry@vrublesvky.me
 */
public class CypherColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Line comment", CypherTextAttributeKey.LINE_COMMENT),
            new AttributesDescriptor("Block comment", CypherTextAttributeKey.BLOCK_COMMENT),

            new AttributesDescriptor("Keyword", CypherTextAttributeKey.KEYWORD),
            new AttributesDescriptor("Identifier", CypherTextAttributeKey.IDENTIFIER),
            //new AttributesDescriptor("Function", CypherTextAttributeKey.FUNCTION_NAME),

            new AttributesDescriptor("Semicolon", CypherTextAttributeKey.SEMICOLON),
            new AttributesDescriptor("Operation sign", CypherTextAttributeKey.OPERATION_SIGN),
            new AttributesDescriptor("Parentheses", CypherTextAttributeKey.PARENTHESES),
            new AttributesDescriptor("Curly braces", CypherTextAttributeKey.CURLY_BRACES),
            new AttributesDescriptor("Square braces", CypherTextAttributeKey.SQUARE_BRACES),
            new AttributesDescriptor("Comma", CypherTextAttributeKey.COMMA),
            new AttributesDescriptor("Dot", CypherTextAttributeKey.DOT),

            new AttributesDescriptor("String", CypherTextAttributeKey.STRING),
            new AttributesDescriptor("Number", CypherTextAttributeKey.NUMBER),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return CypherIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new CypherSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "// This is .cyp file\n"
                + "MATCH (n:NodeLabel {name: \"Dmitry\"})\n"
                + "MATCH (n)-[:LIKES]->(cypherLanguage:Language)\n"
                + "WHERE /* block comment */ n.property =\"string value\"\n"
                + "SET n.calculatedProperty = 2 + 2 \n"
                + "SET n.functionProperty = toInt(\"1\")\n"
                + "SET n.arrayProperty = [1, 2, 3, 4]\n"
                + "RETURN *;\n";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Cypher";
    }
}
