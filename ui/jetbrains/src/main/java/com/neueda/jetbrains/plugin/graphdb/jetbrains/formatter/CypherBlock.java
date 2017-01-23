package com.neueda.jetbrains.plugin.graphdb.jetbrains.formatter;

import com.intellij.formatting.ASTBlock;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CypherBlock implements ASTBlock {

    private ASTNode node;
    private Wrap wrap;
    private Indent indent;
    private Alignment alignment;
    private SpacingBuilder spacingBuilder;

    private List<Block> subBlocks = null;
    private CodeStyleSettings codeStyleSettings;

    CypherBlock(@NotNull ASTNode node,
                @NotNull CodeStyleSettings settings,
                @Nullable Wrap wrap,
                @Nullable Indent indent,
                @Nullable Alignment alignment) {
        this.node = node;
        this.codeStyleSettings = settings;
        this.wrap = wrap;
        this.indent = indent;
        this.alignment = alignment;
        this.spacingBuilder = CypherFormattingModelBuilder.createSpacingBuilder(settings);
    }

    @Override
    public ASTNode getNode() {
        return node;
    }

    @NotNull
    @Override
    public TextRange getTextRange() {
        return node.getTextRange();
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        if (subBlocks == null) {
            Predicate<ASTNode> isWhitespaceOrEmpty = CypherBlock::isWhitespaceOrEmpty;
            subBlocks = Stream.of(node.getChildren(null))
                    .filter(isWhitespaceOrEmpty.negate())
                    .map(this::makeSubBlock)
                    .filter(Objects::nonNull)
                    .collect(toList());
        }
        return subBlocks;
    }

    private Block makeSubBlock(@NotNull ASTNode node) {
        Wrap wrap = null;
        Indent indent = Indent.getNoneIndent();

        if (isTopLevel(node)) {
            wrap = Wrap.createWrap(WrapType.ALWAYS, true);
            indent = Indent.getNoneIndent();
        }

        if (node.getElementType() == CypherTypes.K_ON) {
            if (TreeUtil.findParent(node, CypherTypes.MERGE) != null) {
                wrap = Wrap.createWrap(WrapType.ALWAYS, true);
                indent = Indent.getNormalIndent(true);
            }
        }

        if (isReturnBodyKeywords(node)) {
            wrap = Wrap.createWrap(WrapType.ALWAYS, true);
            indent = Indent.getNormalIndent(false);
        }

        if (isTopLevel(node)) {
            String original = node.getPsi().getText();
            String text = original.toUpperCase();
            if (!original.equals(text)) {
                node.getPsi(LeafPsiElement.class).rawReplaceWithText(text);
            }
        }

        return new CypherBlock(node, codeStyleSettings, wrap, indent, null);
    }

    @Nullable
    @Override
    public Wrap getWrap() {
        return wrap;
    }

    @Nullable
    @Override
    public Indent getIndent() {
        return indent;
    }

    @Nullable
    @Override
    public Alignment getAlignment() {
        return alignment;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        //todo Add more complex login on spacing, if needed

        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        Indent indent = Indent.getNoneIndent();
        //todo add child indentation rules

        return new ChildAttributes(indent, null);
    }

    @Override
    public boolean isIncomplete() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return node.getFirstChildNode() == null;
    }

    private static boolean isWhitespaceOrEmpty(ASTNode node) {
        return node.getElementType() == TokenType.WHITE_SPACE || node.getTextLength() == 0;
    }

    private static boolean isTopLevel(ASTNode node) {
        return FormatterUtil.isOneOf(node,
                CypherTypes.CREATE,
                CypherTypes.MERGE,
                CypherTypes.MATCH,
                CypherTypes.WHERE,
                CypherTypes.WITH,
                CypherTypes.UNION,
                CypherTypes.RETURN);
    }

    private static boolean isReturnBodyKeywords(ASTNode node) {
        return FormatterUtil.isOneOf(node,
                CypherTypes.LIMIT,
                CypherTypes.SKIP,
                CypherTypes.ORDER);
    }
}
