package com.neueda.jetbrains.plugin.graphdb.language.cypher.formatter;

import com.intellij.formatting.ASTBlock;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.formatting.alignment.AlignmentStrategy;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.ContainerUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CypherBlock implements ASTBlock {

    private ASTNode node;
    private Wrap wrap;
    private Indent indent;
    private Alignment alignment;
    private SpacingBuilder spacingBuilder;

    private List<Block> subBlocks = null;
    private CodeStyleSettings codeStyleSettings;

    CypherBlock(@NotNull ASTNode node,
                @Nullable Alignment alignment,
                @Nullable Indent indent,
                @Nullable Wrap wrap,
                @NotNull CodeStyleSettings settings,
                @NotNull SpacingBuilder spacingBuilder) {
        this.node = node;
        this.codeStyleSettings = settings;
        this.wrap = wrap;
        this.indent = indent;
        this.alignment = alignment;
        this.spacingBuilder = spacingBuilder;
    }

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

            AlignmentStrategy.AlignmentPerTypeStrategy strategy = null;

            boolean isPattern = getNode().getElementType() == CypherTypes.PATTERN;
            boolean isProperty = getNode().getElementType() == CypherTypes.MAP_LITERAL;

            if (isPattern) {
                strategy = AlignmentStrategy.createAlignmentPerTypeStrategy(
                        ContainerUtil.list(CypherTypes.PATTERN_PART, CypherTypes.RELATIONSHIPS_PATTERN),
                        CypherTypes.PATTERN, true);
            }

            if (isProperty) {
                strategy = AlignmentStrategy.createAlignmentPerTypeStrategy(
                        ContainerUtil.list(CypherTypes.PROPERTY_KEY_NAME, CypherTypes.EXPRESSION),
                        CypherTypes.MAP_LITERAL, true);
            }

            subBlocks = new ArrayList<>();
            for (ASTNode subNode = node.getFirstChildNode(); subNode != null; subNode = subNode.getTreeNext()) {
                if (isWhitespaceOrEmpty.test(subNode)) {
                    continue;
                }
                Alignment alignment = strategy != null ? strategy.getAlignment(subNode.getElementType()) : null;
                CypherBlock block = makeSubBlock(subNode, alignment);
                subBlocks.add(block);
            }
        }
        return subBlocks;
    }

    private CypherBlock makeSubBlock(@NotNull ASTNode node, Alignment alignment) {
        Wrap wrap = calcWrap(node);
        Indent indent = calcIndent(node);

        return new CypherBlock(node, alignment, indent, wrap, codeStyleSettings, spacingBuilder);
    }

    @NotNull
    private Indent calcIndent(@NotNull ASTNode node) {
        IElementType parentType = this.node.getElementType();
        IElementType type = node.getElementType();

        if (parentType == CypherTypes.SINGLE_QUERY && type == CypherTypes.CLAUSE) return Indent.getNoneIndent();
        if (type == CypherTypes.CLAUSE) return Indent.getNormalIndent();
        if (type == CypherTypes.MERGE_ACTION) return Indent.getNormalIndent();
        if (isReturnBodyKeywords(node)) return Indent.getNormalIndent();
        if (type == CypherTypes.PATTERN_PART || type == CypherTypes.RELATIONSHIP_PATTERN)
            return Indent.getContinuationIndent();
        if (type == CypherTypes.PROPERTY_KEY_NAME) return Indent.getContinuationIndent();
        if (type == CypherTypes.WHERE) return Indent.getNormalIndent();
        if (type == CypherTypes.K_AS) return Indent.getContinuationIndent();

        return Indent.getNoneIndent();
    }

    @NotNull
    private Wrap calcWrap(@NotNull ASTNode node) {
        IElementType type = node.getElementType();

        if (type == CypherTypes.CLAUSE) return Wrap.createWrap(WrapType.ALWAYS, true);
        if (type == CypherTypes.K_USING) return Wrap.createWrap(WrapType.ALWAYS, true);
        if (type == CypherTypes.CALL) return Wrap.createWrap(WrapType.ALWAYS, true);
        if (type == CypherTypes.MERGE_ACTION) return Wrap.createWrap(WrapType.ALWAYS, true);
        if (isReturnBodyKeywords(node)) return Wrap.createWrap(WrapType.ALWAYS, true);
        if (type == CypherTypes.PATTERN_PART || type == CypherTypes.RELATIONSHIP_PATTERN)
            return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        if (type == CypherTypes.WHERE && TreeUtil.findParent(node, CypherTypes.LIST_COMPREHENSION) != null)
            return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        if (type == CypherTypes.WHERE) return Wrap.createWrap(WrapType.ALWAYS, true);
        if (type == CypherTypes.PROPERTY_KEY_NAME) return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        if (type == CypherTypes.K_AS) return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        if (type == CypherTypes.FOREACH) return Wrap.createWrap(WrapType.ALWAYS, true);

        return Wrap.createWrap(WrapType.NONE, false);
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
        //todo Add more complex logic on spacing, if needed

        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        Indent indent = Indent.getContinuationIndent(true);
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

    private static boolean isReturnBodyKeywords(ASTNode node) {
        return FormatterUtil.isOneOf(node,
                CypherTypes.LIMIT,
                CypherTypes.SKIP,
                CypherTypes.ORDER);
    }
}
