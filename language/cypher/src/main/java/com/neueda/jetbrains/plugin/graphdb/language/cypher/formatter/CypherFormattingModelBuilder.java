package com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter;

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
        return new SpacingBuilder(settings, CypherLanguage.INSTANCE)
                .before(OP_COLON).none()
                .afterInside(OP_COLON, MAP_LITERAL).spaces(1)
                .after(OP_COLON).none()

                .beforeInside(PARENTHESE_CLOSE, FOREACH).lineBreakInCode()

                .withinPair(PARENTHESE_OPEN, PARENTHESE_CLOSE).none()
                .withinPair(BRACKET_CURLYOPEN, BRACKET_CURLYCLOSE).none()

                .before(OP_COMMA).none()
                .after(OP_COMMA).spaces(1)

                .between(NODE_LABELS, PROPERTIES).spaces(1)
                .between(RELATIONSHIP_TYPES, PROPERTIES).spaces(1)
                .between(PARENTHESIZED_EXPRESSION, PATTERN).none()

                .between(NODE_LABEL, NODE_LABEL).none()
                .between(REL_TYPE, REL_TYPE).none()

                .between(NODE_PATTERN, RELATIONSHIP_PATTERN).none()
                .between(NODE_PATTERN, PATTERN_ELEMENT_CHAIN).none()
                .between(RELATIONSHIP_PATTERN, NODE_PATTERN).none()
                .between(PATTERN_ELEMENT_CHAIN, NODE_PATTERN).none()
                .between(VARIABLE, NODE_LABELS).none()

                .between(K_FOREACH, PARENTHESE_OPEN).spaces(1)

                .aroundInside(PATTERN_ELEMENT, PATTERN_PART).none()
                .aroundInside(DASH, RELATIONSHIP_PATTERN).none()
                .aroundInside(LEFT_ARROW_HEAD, RELATIONSHIP_PATTERN).none()
                .aroundInside(RIGHT_ARROW_HEAD, RELATIONSHIP_PATTERN).none()

                .afterInside(NODE_LABEL, HINT).none()
                .afterInside(OP_MUL, MAYBE_VARIABLE_LENGTH).none()

                .around(OP_PLUS).spaces(1)
                .around(OP_MINUS).spaces(1)
                .around(OP_MUL).spaces(1)
                .around(OP_DIVIDE).spaces(1)
                .around(OP_MODULO).spaces(1)
                .around(OP_POW).spaces(1)
                .around(OP_EQUAL).spaces(1)
                .around(OP_NOTEQUALS).spaces(1)
                .around(OP_INVALIDNOTEQUALS).spaces(1)
                .around(OP_LESSTHEN).spaces(1)
                .around(OP_GREATHERTHEN).spaces(1)
                .around(OP_LESSTHANEQUALS).spaces(1)
                .around(OP_GREATERTHANEQUALS).spaces(1)

                .between(STATEMENT_ITEM, STATEMENT_ITEM).blankLines(1)
                .after(PATTERN_COMPREHENSION).lineBreakInCode();
    }
}
