package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree;

public interface TreeNodeDirectory extends TreeNode {

    void addLeaf(String name);

    TreeNodeDirectory getDirectory(String name);

}
