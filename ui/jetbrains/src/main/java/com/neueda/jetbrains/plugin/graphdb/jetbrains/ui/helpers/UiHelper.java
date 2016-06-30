package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPath;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;

import java.util.List;
import java.util.Map;


public final class UiHelper {

    public static final int INLINE_TEXT_LENGTH = 80;
    public static final int INLINE_LIST_SIZE = 10;

    public static boolean canBeTree(Object object) {
        return object instanceof List
                || object instanceof Map
                || object instanceof GraphNode
                || object instanceof GraphRelationship
                || object instanceof GraphPath;
    }

    public static PatchedDefaultMutableTreeNode keyValueToTreeNode(String key, Object value) {
        if (value instanceof List) {
            return listToTreeNode(key, (List) value);
        }
        if (value instanceof Map) {
            return mapToTreeNode(key, (Map) value);
        }
        if (value instanceof GraphNode) {
            return nodeToTreeNode(key, (GraphNode) value);
        }
        if (value instanceof GraphRelationship) {
            return relationshipToTreeNode(key, (GraphRelationship) value);
        }
        if (value instanceof GraphPath) {
            return pathToTreeNode(key, (GraphPath) value);
        }
        return objectToTreeNode(key, value);
    }

    public static PatchedDefaultMutableTreeNode nodeToTreeNode(String key, GraphNode node) {
        PatchedDefaultMutableTreeNode treeRoot = new PatchedDefaultMutableTreeNode(new KeyValuePair(key, "node"));
        addGraphEntityData(treeRoot, node);
        return treeRoot;
    }

    public static PatchedDefaultMutableTreeNode relationshipToTreeNode(String key, GraphRelationship relationship) {
        PatchedDefaultMutableTreeNode treeRoot = new PatchedDefaultMutableTreeNode(new KeyValuePair(key, "relationship"));

        addGraphEntityData(treeRoot, relationship);

        treeRoot.add(objectToTreeNode("startNodeId", relationship.getStartNodeId()));
        if (relationship.getStartNode() != null) {
            treeRoot.add(nodeToTreeNode("startNode", relationship.getStartNode()));
        }
        treeRoot.add(objectToTreeNode("endNodeId", relationship.getEndNodeId()));
        if (relationship.getEndNode() != null) {
            treeRoot.add(nodeToTreeNode("endNode", relationship.getEndNode()));
        }
        return treeRoot;
    }

    public static void addGraphEntityData(PatchedDefaultMutableTreeNode treeRoot, GraphEntity graphEntity) {
        treeRoot.add(objectToTreeNode("id", graphEntity.getId()));
        if (graphEntity.isTypesSingle()) {
            treeRoot.add(objectToTreeNode(graphEntity.getTypesName(), graphEntity.getTypes().get(0)));
        } else {
            treeRoot.add(listToTreeNode(graphEntity.getTypesName(), graphEntity.getTypes()));
        }
        treeRoot.add(mapToTreeNode("properties", graphEntity.getPropertyContainer().getProperties()));
    }

    public static PatchedDefaultMutableTreeNode pathToTreeNode(String key, GraphPath path) {
        PatchedDefaultMutableTreeNode root = new PatchedDefaultMutableTreeNode(new KeyValuePair(key, "path"));
        List<Object> components = path.getComponents();
        for (int i = 0; i < components.size(); i++) {
            root.add(keyValueToTreeNode(String.valueOf(i), components.get(i)));
        }
        return root;
    }

    private static PatchedDefaultMutableTreeNode objectToTreeNode(String key, Object value) {
        if (value instanceof String) {
            String string = (String) value;
            if (string.length() <= INLINE_TEXT_LENGTH) {
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
        /* DISABLE list inlining
        if (list.size() <= INLINE_LIST_SIZE) {
            String stringRepresentation = list.toString();
            if (stringRepresentation.length() <= INLINE_TEXT_LENGTH) {
                return new PatchedDefaultMutableTreeNode(new KeyValuePair(key, stringRepresentation, true));
            }
        }*/

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
