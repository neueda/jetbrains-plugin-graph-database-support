package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree;

import com.intellij.icons.AllIcons;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.MetadataTreeNodeModel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.function.Function;

public final class MutableTreeNodeProducers {

    private MutableTreeNodeProducers() {
    }

    public static Function<TreeNode, DefaultMutableTreeNode> function(Neo4jTreeNodeType type, DataSourceApi dataSourceApi) {
        return treeNode -> new PatchedDefaultMutableTreeNode(
                new MetadataTreeNodeModel(type, dataSourceApi, treeNode.getName(), AllIcons.Nodes.Function)
        );
    }

    public static Function<TreeNode, DefaultMutableTreeNode> directory(Neo4jTreeNodeType type, DataSourceApi dataSourceApi) {
        return treeNode -> new PatchedDefaultMutableTreeNode(
                new MetadataTreeNodeModel(type, dataSourceApi, treeNode.getName(), AllIcons.Nodes.Package)
        );
    }

}
