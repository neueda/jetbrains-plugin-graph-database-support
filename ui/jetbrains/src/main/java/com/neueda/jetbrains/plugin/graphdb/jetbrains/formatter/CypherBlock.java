package com.neueda.jetbrains.plugin.graphdb.jetbrains.formatter;

import com.intellij.formatting.ASTBlock;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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
                @Nullable Alignment alignment,
                SpacingBuilder spacingBuilder) {
        this.node = node;
        this.codeStyleSettings = settings;
        this.wrap = wrap;
        this.indent = indent;
        this.alignment = alignment;
        this.spacingBuilder = spacingBuilder;
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
            subBlocks = Stream.of(node.getChildren(null))
                    .filter(node -> !CypherBlock.isWhitespaceOrEmpty(node))
                    .map(this::makeSubBlock)
                    .collect(toList());
        }
        return subBlocks;
    }

    private Block makeSubBlock(@NotNull ASTNode node) {
        return new CypherBlock(node, codeStyleSettings, null, Indent.getNoneIndent(), null, spacingBuilder);
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
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        return new ChildAttributes(null, null);
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
}
