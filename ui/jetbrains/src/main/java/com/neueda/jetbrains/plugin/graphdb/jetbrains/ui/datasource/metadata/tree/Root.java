package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Root implements TreeNode, TreeNodeDirectory {

    private final DefaultMutableTreeNode root;
    private final Map<String, Directory> directories;
    private final List<Leaf> functions;
    private final Function<TreeNode, DefaultMutableTreeNode> directoryProducer;
    private final Function<TreeNode, DefaultMutableTreeNode> functionProducer;

    public Root(
            DefaultMutableTreeNode root,
            Function<TreeNode, DefaultMutableTreeNode> directoryProducer,
            Function<TreeNode, DefaultMutableTreeNode> functionProducer
    ) {
        this.root = root;
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
        return "root";
    }

    @Override
    public DefaultMutableTreeNode getMutableTreeNode() {
        directories.values().forEach((directory) -> root.add(directory.getMutableTreeNode()));
        functions.forEach((function) -> root.add(function.getMutableTreeNode()));

        return root;
    }
}
