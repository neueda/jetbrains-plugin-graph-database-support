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
                Alignment.createAlignment()
        );
        return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }

    static SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
        CypherCodeStyleSettings customSettings = settings.getCustomSettings(CypherCodeStyleSettings.class);
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(CypherLanguage.INSTANCE);

        final int spacesBeforeComma = commonSettings.SPACE_BEFORE_COMMA ? 1 : 0;
        final int spacesBeforeColon = customSettings.SPACE_BEFORE_COLON ? 1 : 0;
        final int spacesAfterColon = customSettings.SPACE_AFTER_COLON ? 1 : 0;
        final int spacesAfterLabel = customSettings.SPACE_BETWEEN_LABEL_AND_PARAMS ? 1 : 0;

        final int spacesAroundEqualityOperator = commonSettings.SPACE_AROUND_EQUALITY_OPERATORS ? 1 : 0;
        final int spacesAroundAdditiveOperator = commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS ? 1 : 0;
        final int spacesAroundMulOperator = commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS ? 1 : 0;

        return new SpacingBuilder(settings, CypherLanguage.INSTANCE)
                .before(OP_COLON).spacing(spacesBeforeColon, spacesBeforeColon, 0, false, 0)
                .after(OP_COLON).spacing(spacesAfterColon, spacesAfterColon, 0, false, 0)

                .withinPair(PARENTHESE_OPEN, PARENTHESE_CLOSE)
                    .spaceIf(commonSettings.SPACE_WITHIN_BRACKETS, true)

                .withinPair(BRACKET_CURLYOPEN, BRACKET_CURLYCLOSE)
                    .spaceIf(commonSettings.SPACE_WITHIN_BRACES, true)

                .before(OP_COMMA).spacing(spacesBeforeComma, spacesBeforeComma, 0, false, 0)
                .after(OP_COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA)

                .between(NODE_LABELS, PROPERTIES).spaces(spacesAfterLabel)
                .between(PARENTHESIZED_EXPRESSION, PATTERN).spaceIf(false)
                .around(OP_PLUS).spaces(spacesAroundAdditiveOperator)
                .around(OP_MINUS).spaces(spacesAroundAdditiveOperator)
                .around(OP_MUL).spaces(spacesAroundMulOperator)
                .around(OP_DIVIDE).spaces(spacesAroundMulOperator)
                .around(OP_MODULO).spaces(spacesAroundMulOperator)
                .around(OP_POW).spaces(spacesAroundMulOperator)
                .around(OP_EQUAL).spaces(spacesAroundEqualityOperator)
                .around(OP_NOTEQUALS).spaces(spacesAroundEqualityOperator)
                .around(OP_LESSTHEN).spaces(spacesAroundEqualityOperator)
                .around(OP_GREATHERTHEN).spaces(spacesAroundEqualityOperator)
                .around(OP_LESSTHANEQUALS).spaces(spacesAroundEqualityOperator)
                .around(OP_GREATERTHANEQUALS).spaces(spacesAroundEqualityOperator)
                .between(STATEMENT_ITEM, STATEMENT_ITEM).blankLines(1);
    }
}
