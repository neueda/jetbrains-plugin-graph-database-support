package com.neueda.jetbrains.plugin.graphdb.language.cypher.settings;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherIcons;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.highlight.CypherSyntaxHighlighter;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.highlight.CypherSyntaxColors;
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
            new AttributesDescriptor("Line comment", CypherSyntaxColors.LINE_COMMENT),
            new AttributesDescriptor("Block comment", CypherSyntaxColors.BLOCK_COMMENT),

            new AttributesDescriptor("Keyword", CypherSyntaxColors.KEYWORD),
            new AttributesDescriptor("Variable", CypherSyntaxColors.VARIABLE),
            new AttributesDescriptor("Parameter", CypherSyntaxColors.PARAMETER),
            new AttributesDescriptor("Function", CypherSyntaxColors.FUNCTION),
            new AttributesDescriptor("Label", CypherSyntaxColors.LABEL),
            new AttributesDescriptor("Relationship type", CypherSyntaxColors.RELATIONSHIP_TYPE),

            new AttributesDescriptor("Semicolon", CypherSyntaxColors.SEMICOLON),
            new AttributesDescriptor("Operation sign", CypherSyntaxColors.OPERATION_SIGN),
            new AttributesDescriptor("Parentheses", CypherSyntaxColors.PARENTHESES),
            new AttributesDescriptor("Curly braces", CypherSyntaxColors.CURLY_BRACES),
            new AttributesDescriptor("Square braces", CypherSyntaxColors.SQUARE_BRACES),
            new AttributesDescriptor("Comma", CypherSyntaxColors.COMMA),
            new AttributesDescriptor("Dot", CypherSyntaxColors.DOT),

            new AttributesDescriptor("String", CypherSyntaxColors.STRING),
            new AttributesDescriptor("Number", CypherSyntaxColors.NUMBER),
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
                + "CALL dbms.procedures()\n"
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
