package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree;

public interface TreeNodeDirectory extends TreeNode {

    public void addLeaf(String name);

    public TreeNodeDirectory getDirectory(String name);

}
