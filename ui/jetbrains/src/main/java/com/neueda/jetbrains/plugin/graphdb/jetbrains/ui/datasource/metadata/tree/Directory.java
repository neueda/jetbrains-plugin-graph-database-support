package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

class Directory implements TreeNode, TreeNodeDirectory {
    private final Map<String, Directory> directories;
    private final List<Leaf> functions;
    private final String name;
    private final Function<TreeNode, DefaultMutableTreeNode> directoryProducer;
    private final Function<TreeNode, DefaultMutableTreeNode> functionProducer;

    Directory(
            String name,
            Function<TreeNode, DefaultMutableTreeNode> directoryProducer,
            Function<TreeNode, DefaultMutableTreeNode> functionProducer
    ) {
        this.name = name;
        this.directoryProducer = directoryProducer;
        this.functionProducer = functionProducer;
        this.directories = new LinkedHashMap<>();
        this.functions = new ArrayList<>();
    }

    @Override
    public Directory getDirectory(String name) {
        return directories.computeIfAbsent(
                name,
                (newName) -> new Directory(newName, directoryProducer, functionProducer)
        );
    }

    @Override
    public void addLeaf(String name) {
        functions.add(new Leaf(name, functionProducer));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DefaultMutableTreeNode getMutableTreeNode() {
        DefaultMutableTreeNode root = directoryProducer.apply(this);

        directories.values().forEach((directory) -> root.add(directory.getMutableTreeNode()));
        functions.forEach((function) -> root.add(function.getMutableTreeNode()));

        return root;
    }
}
