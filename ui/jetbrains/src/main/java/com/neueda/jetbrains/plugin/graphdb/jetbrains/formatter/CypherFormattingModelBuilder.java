package com.neueda.jetbrains.plugin.graphdb.jetbrains.formatter;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelProvider;
import com.intellij.formatting.Indent;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes.*;

public class CypherFormattingModelBuilder implements FormattingModelBuilder {
    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        CypherBlock block = new CypherBlock(element.getNode(), settings, Wrap.createWrap(WrapType.NONE, false),
                Indent.getNoneIndent(),
                Alignment.createAlignment(),
                createSpacingBuilder(settings));
        return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }

    private static SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
        CypherCodeStyleSettings customSettings = settings.getCustomSettings(CypherCodeStyleSettings.class);
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(CypherLanguage.INSTANCE);

        final int spacesBeforeComma = commonSettings.SPACE_BEFORE_COMMA ? 1 : 0;
        final int spacesBeforeColon = customSettings.SPACE_BEFORE_COLON ? 1 : 0;
        final int spacesAfterColon = customSettings.SPACE_AFTER_COLON ? 1 : 0;

        return new SpacingBuilder(settings, CypherLanguage.INSTANCE)
                .before(OP_COLON).spacing(spacesBeforeColon, spacesBeforeColon, 0, false, 0)
                .after(OP_COLON).spacing(spacesAfterColon, spacesAfterColon, 0, false, 0)
                .withinPair(PARENTHESE_OPEN, PARENTHESE_CLOSE)
                .spaceIf(commonSettings.SPACE_WITHIN_BRACKETS, true)
                .withinPair(BRACKET_CURLYOPEN, BRACKET_CURLYCLOSE)
                .spaceIf(commonSettings.SPACE_WITHIN_BRACES, true)
                .before(OP_COMMA).spacing(spacesBeforeComma, spacesBeforeComma, 0, false, 0)
                .after(OP_COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA);
    }
}
