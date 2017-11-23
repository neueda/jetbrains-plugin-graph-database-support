package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.function.Function;

class Leaf implements TreeNode {
    private final String name;
    private Function<TreeNode, DefaultMutableTreeNode> mutableTreeNodeProducer;

    public Leaf(String name, Function<TreeNode, DefaultMutableTreeNode> mutableTreeNodeProducer) {
        this.name = name;
        this.mutableTreeNodeProducer = mutableTreeNodeProducer;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DefaultMutableTreeNode getMutableTreeNode() {
        return mutableTreeNodeProducer.apply(this);
    }
}