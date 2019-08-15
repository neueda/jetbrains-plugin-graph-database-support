package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPath;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeNodeModelApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.model.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

public final class UiHelper {

    public static final int INLINE_TEXT_LENGTH = 80;
    public static final String MAP = "map";
    public static final String LIST = "list";
    public static final String TEXT = "text";
    public static final String PATH = "path";
    public static final String RELATIONSHIP = "relationship";
    public static final String NODE = "node";
    public static final String PROPERTIES = "properties";
    public static final String LABELS = "labels";
    public static final String ID = "id";

    private UiHelper() {
    }

    public static boolean canBeTree(Object object) {
        return object instanceof List
                || object instanceof Map
                || object instanceof GraphNode
                || object instanceof GraphRelationship
                || object instanceof GraphPath;
    }

    public static PatchedDefaultMutableTreeNode keyValueToTreeNode(String key, Object value, DataSourceApi dataSourceApi, Object rootObject) {
        if (value instanceof List) {
            return listToTreeNode(key, (List) value, dataSourceApi, rootObject);
        }
        if (value instanceof Map) {
            return mapToTreeNode(key, (Map) value, dataSourceApi, rootObject);
        }
        if (value instanceof GraphNode) {
            return nodeToTreeNode(key, (GraphNode) value, dataSourceApi);
        }
        if (value instanceof GraphRelationship) {
            return relationshipToTreeNode(key, (GraphRelationship) value, dataSourceApi);
        }
        if (value instanceof GraphPath) {
            return pathToTreeNode(key, (GraphPath) value, dataSourceApi, rootObject);
        }
        return objectToTreeNode(key, value, dataSourceApi, rootObject);
    }

    public static PatchedDefaultMutableTreeNode nodeToTreeNode(String key, GraphNode node, DataSourceApi dataSourceApi) {
        PatchedDefaultMutableTreeNode treeRoot = new PatchedDefaultMutableTreeNode(modelOf(node, key, NODE, dataSourceApi, node));
        addGraphEntityData(treeRoot, node, dataSourceApi);
        return treeRoot;
    }

    public static PatchedDefaultMutableTreeNode relationshipToTreeNode(String key, GraphRelationship relationship, DataSourceApi dataSourceApi) {
        PatchedDefaultMutableTreeNode treeRoot = new PatchedDefaultMutableTreeNode(modelOf(relationship, key, RELATIONSHIP, dataSourceApi, relationship));

        addGraphEntityData(treeRoot, relationship, dataSourceApi);

        treeRoot.add(objectToTreeNode("startNodeId", relationship.getStartNodeId(), dataSourceApi, relationship));
        if (relationship.getStartNode() != null) {
            treeRoot.add(nodeToTreeNode("startNode", relationship.getStartNode(), dataSourceApi));
        }
        treeRoot.add(objectToTreeNode("endNodeId", relationship.getEndNodeId(), dataSourceApi, relationship));
        if (relationship.getEndNode() != null) {
            treeRoot.add(nodeToTreeNode("endNode", relationship.getEndNode(), dataSourceApi));
        }
        return treeRoot;
    }

    private static void addGraphEntityData(PatchedDefaultMutableTreeNode treeRoot, GraphEntity graphEntity, DataSourceApi dataSourceApi) {
        treeRoot.add(objectToTreeNode(ID, graphEntity.getId(), dataSourceApi, graphEntity));
        if (graphEntity.isTypesSingle()) {
            treeRoot.add(objectToTreeNode(graphEntity.getTypesName(), graphEntity.getTypes().get(0), dataSourceApi, graphEntity));
        } else {
            treeRoot.add(listToTreeNode(graphEntity.getTypesName(), graphEntity.getTypes(), dataSourceApi, graphEntity));
        }
        treeRoot.add(mapToTreeNode(PROPERTIES, graphEntity.getPropertyContainer().getProperties(), dataSourceApi, graphEntity));
    }

    private static PatchedDefaultMutableTreeNode pathToTreeNode(String key, GraphPath path, DataSourceApi dataSourceApi, Object rootObject) {
        PatchedDefaultMutableTreeNode root = new PatchedDefaultMutableTreeNode(modelOf(null, key, PATH, dataSourceApi, rootObject));
        List<Object> components = path.getComponents();
        for (int i = 0; i < components.size(); i++) {
            root.add(keyValueToTreeNode(String.valueOf(i), components.get(i), dataSourceApi, rootObject));
        }
        return root;
    }

    private static PatchedDefaultMutableTreeNode objectToTreeNode(String key, Object value, DataSourceApi dataSourceApi, Object rootObject) {
        if (value instanceof String) {
            String string = (String) value;
            if (string.length() <= INLINE_TEXT_LENGTH) {
                return new PatchedDefaultMutableTreeNode(modelOf(representUiString(string), key, null, dataSourceApi, rootObject));
            } else {
                PatchedDefaultMutableTreeNode parent = new PatchedDefaultMutableTreeNode(modelOf(null, key, TEXT, dataSourceApi, rootObject));
                parent.add(new PatchedDefaultMutableTreeNode(string));
                return parent;
            }
        }
        return new PatchedDefaultMutableTreeNode(modelOf(Objects.toString(value), key, null, dataSourceApi, rootObject));
    }

    private static PatchedDefaultMutableTreeNode listToTreeNode(String key, List list, DataSourceApi dataSourceApi, Object rootObject) {
        PatchedDefaultMutableTreeNode node;
        if (LABELS.equals(key)) {
            node = new PatchedDefaultMutableTreeNode(new LabelsModel(dataSourceApi, rootObject));
        } else {
            node = new PatchedDefaultMutableTreeNode(modelOf(null, key, LIST, dataSourceApi, rootObject));
        }

        for (int i = 0; i < list.size(); i++) {
            node.add(keyValueToTreeNode(String.valueOf(i), list.get(i), dataSourceApi, rootObject));
        }
        return node;
    }

    private static PatchedDefaultMutableTreeNode mapToTreeNode(String key, Map map, DataSourceApi dataSourceApi, Object rootObject) {
        PatchedDefaultMutableTreeNode node;
        if (PROPERTIES.equals(key)) {
            node = new PatchedDefaultMutableTreeNode(new PropertiesModel(dataSourceApi, rootObject));
        } else {
            node = new PatchedDefaultMutableTreeNode(modelOf(null, key, MAP, dataSourceApi, rootObject));
        }

        map.forEach((mapKey, mapValue) -> node.add(keyValueToTreeNode(mapKey.toString(), mapValue, dataSourceApi, rootObject)));
        return node;
    }

    private static TreeNodeModelApi modelOf(Object value, String key, String description, DataSourceApi dataSourceApi, Object rootObject) {
        if (value instanceof GraphNode) {
            return new NodeModel((GraphNode) value, key, dataSourceApi);
        } else if (value instanceof GraphRelationship) {
            return new RelationshipModel((GraphRelationship) value, key, dataSourceApi);
        } else if (value instanceof List) {
            return new ListModel(key, dataSourceApi);
        } else if (value instanceof Map) {
            return new MapModel(key, dataSourceApi);
        } else {
            return new ObjectModel(value, key, description, dataSourceApi, rootObject);
        }
    }

    public static String representUiString(String value) {
            return format("\"%s\"", Objects.toString(value));
    }

    @SuppressWarnings("unchecked")
    public static  <T> Optional<T> cast(Object o, Class<T> clazz) {
        if (clazz.isInstance(o)) {
            return Optional.of((T) o);
        } else {
            return Optional.empty();
        }
    }
}
