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
import java.util.Map;
import java.util.function.Predicate;

import static com.intellij.openapi.util.Pair.pair;
import static com.intellij.util.containers.ContainerUtil.list;

public class CypherBlock implements ASTBlock {

    private static final Map<IElementType, List<IElementType>> ALIGNMENT_MAPPING = ContainerUtil.newHashMap(
            pair(CypherTypes.PATTERN, list(CypherTypes.PATTERN_PART, CypherTypes.RELATIONSHIPS_PATTERN)),
            pair(CypherTypes.MAP_LITERAL, list(CypherTypes.PROPERTY_KEY_NAME, CypherTypes.EXPRESSION)),
            pair(CypherTypes.RETURN_ITEMS, list(CypherTypes.OP_MUL, CypherTypes.RETURN_ITEM, CypherTypes.WHERE)),
            pair(CypherTypes.FILTER_EXPRESSION, list(CypherTypes.ID_IN_COLL, CypherTypes.WHERE))
    );

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
            AlignmentStrategy.AlignmentPerTypeStrategy strategy = createStrategy(getNode());

            subBlocks = new ArrayList<>();
            for (ASTNode subNode = node.getFirstChildNode(); subNode != null; subNode = subNode.getTreeNext()) {
                if (isWhitespaceOrEmpty.test(subNode)) {
                    continue;
                }

                Alignment alignment = strategy != null
                            ? strategy.getAlignment(getNode().getElementType(), subNode.getElementType())
                            : null;

                CypherBlock block = makeSubBlock(subNode, alignment);
                subBlocks.add(block);
            }
        }
        return subBlocks;
    }

    private AlignmentStrategy.AlignmentPerTypeStrategy createStrategy(ASTNode node) {
        if (node == null) {
            return null;
        }

        List<IElementType> childTypes = ALIGNMENT_MAPPING.get(node.getElementType());
        if (childTypes == null) {
            return null;
        }

        return AlignmentStrategy.createAlignmentPerTypeStrategy(childTypes,
                node.getElementType(), true);
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

        if ((parentType == CypherTypes.SINGLE_PART_QUERY || parentType == CypherTypes.MULTI_PART_QUERY)
                && (type == CypherTypes.READING_CLAUSE || type == CypherTypes.UPDATING_CLAUSE || type == CypherTypes.READING_WITH_RETURN)) {
            return Indent.getNoneIndent();
        }
        if (parentType == CypherTypes.READING_WITH_RETURN && type == CypherTypes.READING_CLAUSE) {
            return Indent.getNoneIndent();
        }
        if (type == CypherTypes.READING_CLAUSE || type == CypherTypes.UPDATING_CLAUSE) {
            return Indent.getNormalIndent();
        }
        if (type == CypherTypes.MERGE_ACTION) {
            return Indent.getNormalIndent();
        }
        if (isReturnBodyKeywords(node)) {
            return Indent.getNormalIndent();
        }
        if (type == CypherTypes.PATTERN_PART || type == CypherTypes.RELATIONSHIP_PATTERN) {
            return Indent.getContinuationIndent();
        }
        if (type == CypherTypes.RETURN_ITEM) {
            return Indent.getContinuationIndent();
        }
        if (type == CypherTypes.WHERE) {
            return Indent.getNormalIndent();
        }
        if (parentType == CypherTypes.CASE_EXPRESSION || parentType == CypherTypes.CASE_ALTERNATIVES) {
            if (type == CypherTypes.K_WHEN || type == CypherTypes.K_ELSE || type == CypherTypes.K_END) {
                return Indent.getContinuationIndent();
            }
        }
        if (type == CypherTypes.PROPERTY_KEY_NAME) {
            return Indent.getContinuationIndent();
        }
        if (parentType == CypherTypes.ARRAY && type == CypherTypes.EXPRESSION) {
            return Indent.getContinuationIndent();
        }

        return Indent.getNoneIndent();
    }

    @NotNull
    private Wrap calcWrap(@NotNull ASTNode node) {
        IElementType type = node.getElementType();

        if (type == CypherTypes.READING_CLAUSE || type == CypherTypes.UPDATING_CLAUSE || type == CypherTypes.READING_WITH_RETURN
                || type == CypherTypes.RETURN || type == CypherTypes.WITH) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (type == CypherTypes.K_USING) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (type == CypherTypes.CALL) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (type == CypherTypes.MERGE_ACTION) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (isReturnBodyKeywords(node)) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (type == CypherTypes.PATTERN_PART || type == CypherTypes.RELATIONSHIP_PATTERN) {
            return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        }
        if (type == CypherTypes.WHERE && TreeUtil.findParent(node, CypherTypes.LIST_COMPREHENSION) != null) {
            return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        }
        if (type == CypherTypes.WHERE) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (type == CypherTypes.RETURN_ITEM) {
            return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        }
        if (type == CypherTypes.FOREACH) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (type == CypherTypes.K_ELSE || type == CypherTypes.K_END) {
            return Wrap.createWrap(WrapType.ALWAYS, true);
        }
        if (type == CypherTypes.PROPERTY_KEY_NAME) {
            return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        }
        if (node.getTreeParent().getElementType() == CypherTypes.ARRAY && type == CypherTypes.EXPRESSION) {
            return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        }
        if (type == CypherTypes.K_AS) {
            return Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, true);
        }

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
        return new ChildAttributes(Indent.getNoneIndent(), null);
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
