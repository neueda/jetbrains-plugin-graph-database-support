package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utilities for working with AST trees.
 *
 * @author dmitry@vrublevsky.me
 */
public class TraverseUtil {

    public static Optional<PsiElement> findParent(PsiElement node, List<IElementType> searchTypes) {
        if (node == null) {
            return Optional.empty();
        }
        if (node.getNode() != null && searchTypes.contains(node.getNode().getElementType())) {
            return Optional.of(node);
        }

        return findParent(node.getParent(), searchTypes);
    }

    public static ASTNode findFirstDeepChildByType(ASTNode root, IElementType type) {
        if (root.getElementType().equals(type)) {
            return root;
        }

        for (ASTNode element = root.getFirstChildNode(); element != null; element = element.getTreeNext()) {
            ASTNode child = findFirstDeepChildByType(element, type);
            if (child != null) {
                return child;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends PsiElement> List<T> collectPsiElementsByType(PsiElement psiRoot,
                                                                          IElementType type) {
        ASTNode rootNode = psiRoot.getNode();
        List<ASTNode> nodes = new ArrayList<>();
        collectAstNodesByType(nodes, rootNode, type);

        return nodes.stream()
                .map(node -> (T) node.getPsi())
                .collect(Collectors.toList());
    }

    public static void collectAstNodesByType(List<ASTNode> nodes, ASTNode rootNode, IElementType type) {
        if (rootNode.getElementType().equals(type)) {
            nodes.add(rootNode);
        }
        for (ASTNode element = rootNode.getFirstChildNode(); element != null; element = element.getTreeNext()) {
            collectAstNodesByType(nodes, element, type);
        }
    }
}
