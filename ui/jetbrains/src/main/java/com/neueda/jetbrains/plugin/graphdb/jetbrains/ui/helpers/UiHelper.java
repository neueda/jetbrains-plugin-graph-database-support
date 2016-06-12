package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;

import java.util.List;
import java.util.Map;

public class UiHelper {

    public static PatchedDefaultMutableTreeNode nodeToTree(GraphNode node) {
        PatchedDefaultMutableTreeNode treeRoot = new PatchedDefaultMutableTreeNode(node.getRepresentation());
        addNodeData(treeRoot, node);
        return treeRoot;
    }

    public static void addNodeData(PatchedDefaultMutableTreeNode root, GraphNode node) {
        root.add(objectToTreeNode("id", node.getId()));
        root.add(listToTreeNode("types", node.getTypes()));
        root.add(mapToTreeNode("properties", node.getPropertyContainer().getProperties()));
    }

    public static PatchedDefaultMutableTreeNode relationshipToTree(GraphRelationship relationship) {
        PatchedDefaultMutableTreeNode treeRoot = new PatchedDefaultMutableTreeNode(relationship.getRepresentation());
        treeRoot.add(objectToTreeNode("id", relationship.getId()));
        treeRoot.add(listToTreeNode("types", relationship.getTypes()));
        treeRoot.add(mapToTreeNode("properties", relationship.getPropertyContainer().getProperties()));
        treeRoot.add(objectToTreeNode("startNodeId", relationship.getStartNodeId()));
        if (relationship.getStartNode() != null) {
            PatchedDefaultMutableTreeNode startTreeNode = new PatchedDefaultMutableTreeNode(new KeyValuePair("start", "node"));
            addNodeData(startTreeNode, relationship.getStartNode());
            treeRoot.add(startTreeNode);
        }
        treeRoot.add(objectToTreeNode("endNodeId", relationship.getEndNodeId()));
        if (relationship.getEndNode() != null) {
            PatchedDefaultMutableTreeNode endTreeNode = new PatchedDefaultMutableTreeNode(new KeyValuePair("end", "node"));
            addNodeData(endTreeNode, relationship.getEndNode());
            treeRoot.add(endTreeNode);
        }
        return treeRoot;
    }

    public static PatchedDefaultMutableTreeNode keyValueToTreeNode(String key, Object value) {
        if (value instanceof List) {
            return listToTreeNode(key, (List) value);
        } else if (value instanceof Map) {
            return mapToTreeNode(key, (Map) value);
        } else {
            return objectToTreeNode(key, value);
        }
    }

    private static PatchedDefaultMutableTreeNode objectToTreeNode(String key, Object value) {
        if (value instanceof String) {
            String string = (String) value;
            if (string.length() <= 80) {
                return new PatchedDefaultMutableTreeNode(new KeyValuePair(key, string, true));
            } else {
                PatchedDefaultMutableTreeNode parent = new PatchedDefaultMutableTreeNode(new KeyValuePair(key, "text"));
                parent.add(new PatchedDefaultMutableTreeNode(string));
                return parent;
            }
        }
        return new PatchedDefaultMutableTreeNode(new KeyValuePair(key, value.toString(), true));
    }

    public static PatchedDefaultMutableTreeNode listToTreeNode(String key, List list) {
        PatchedDefaultMutableTreeNode node = new PatchedDefaultMutableTreeNode(new KeyValuePair(key, "list"));
        for (int i = 0; i < list.size(); i++) {
            node.add(keyValueToTreeNode(String.valueOf(i), list.get(i)));
        }
        return node;
    }

    public static PatchedDefaultMutableTreeNode mapToTreeNode(String key, Map map) {
        PatchedDefaultMutableTreeNode node = new PatchedDefaultMutableTreeNode(new KeyValuePair(key, "map"));
        map.forEach((mapKey, mapVal) -> {
            node.add(keyValueToTreeNode(mapKey.toString(), mapVal));
        });
        return node;
    }

    private UiHelper() {
    }
}
