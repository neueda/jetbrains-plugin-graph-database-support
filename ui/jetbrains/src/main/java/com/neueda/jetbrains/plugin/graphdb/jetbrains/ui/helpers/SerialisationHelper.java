package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltNode;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltPath;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.data.Neo4jBoltRelationship;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.model.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class SerialisationHelper {

    private static final char EXPORT_ROW_DELIMITER = '\n';
    private static final char EXPORT_CELL_DELIMITER = '\t';
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JSON_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSON_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);
    }

    public static String convertToCsv(Object entity) {
        if (entity instanceof NodeModel) {
            Neo4jBoltNode obj = (Neo4jBoltNode) ((NodeModel) entity).getValue().orElse(null);
            return convertToCsv(obj);
        } else if (entity instanceof Neo4jBoltNode) {
            return convertToCsv((Neo4jBoltNode) entity);
        }
        if (entity instanceof RelationshipModel) {
            Neo4jBoltRelationship obj = (Neo4jBoltRelationship) ((RelationshipModel) entity).getValue().orElse(null);
            return convertToCsv(obj);
        } else if (entity instanceof Neo4jBoltRelationship) {
            return convertToCsv((Neo4jBoltRelationship) entity);
        }
        if (entity instanceof Neo4jBoltPath) {
            return convertToCsv((Neo4jBoltPath) entity);
        }
        if (entity instanceof ObjectModel) {
            return convertToCsv((ObjectModel) entity);
        }

        try {
            return JSON_MAPPER.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private static String convertToCsv(Neo4jBoltNode entity) {
        return "{\"id\":\"" + entity.getId() + '"'
                + ",\"" + entity.getTypesName() + "\":" + convertToCsv(entity.getTypes())
                + ",\"properties\":" + convertToCsv(entity.getPropertyContainer().getProperties())
                + '}';
    }

    private static String convertToCsv(Neo4jBoltRelationship entity) {
        return "{\"id\":\"" + entity.getId() + '"'
                + ",\"" + entity.getTypesName() + "\":\"" + entity.getTypes().get(0) + '"'
                + ",\"properties\":" + convertToCsv(entity.getPropertyContainer().getProperties())
                + ",\"startNodeId\":\"" + entity.getStartNodeId() + '"'
                + ",\"endNodeId\":\"" + entity.getEndNodeId() + '"'
                + '}';
    }

    private static String convertToCsv(Neo4jBoltPath entity) {
        return '{' + entity.getComponents().stream().map(SerialisationHelper::convertToCsv).collect(Collectors.joining(",")) + '}';
    }

    private static String convertToCsv(ObjectModel entity) {
        Object rootObj = entity.getRootObjectValue()
                .orElse(0); // tiny hack to skip null-checks further
        if (rootObj instanceof Neo4jBoltPath) {
            Neo4jBoltPath obj = (Neo4jBoltPath) rootObj;
            return convertToCsv(obj);
        }
        if (Iterable.class.isAssignableFrom(rootObj.getClass())) {
            Iterable obj = (Iterable) rootObj;
            return convertToCsv(obj);
        }
        if (Map.class.isAssignableFrom(rootObj.getClass())) {
            Map obj = (Map) rootObj;
            return convertToCsv(obj);
        }
        return '"' + entity.getText().orElse(null) + "\":\"" + entity.getValue().orElse(null) + '"';
    }


    private static String convertToCsv(DefaultMutableTreeNode entity) {
        Object userObj = entity.getUserObject();
        if (userObj instanceof LabelsModel) {
            LabelsModel labelsModel = (LabelsModel) userObj;
            Object rootObj = labelsModel.getRootObjectValue().orElse(null);
            if (rootObj instanceof Neo4jBoltNode) {
                Neo4jBoltNode obj = ((Neo4jBoltNode) rootObj);
                return '"' + obj.getTypesName() + "\":" + convertToCsv(obj.getTypes());
            } else if (rootObj instanceof Neo4jBoltRelationship) {
                Neo4jBoltRelationship obj = ((Neo4jBoltRelationship) rootObj);
                return '"' + obj.getTypesName() + "\":\"" + obj.getTypes().get(0) + '"';
            }
            return null;
        }
        if (userObj instanceof PropertiesModel) {
            PropertiesModel propertiesModel = (PropertiesModel) userObj;
            Object rootObj = propertiesModel.getRootObjectValue().orElse(null);
            if (rootObj instanceof Neo4jBoltNode) {
                Neo4jBoltNode obj = ((Neo4jBoltNode) rootObj);
                return "\"properties\":" + convertToCsv(obj.getPropertyContainer().getProperties());
            } else if (rootObj instanceof Neo4jBoltRelationship) {
                Neo4jBoltRelationship obj = ((Neo4jBoltRelationship) rootObj);
                return "\"properties\":" + convertToCsv(obj.getPropertyContainer().getProperties());
            }
            return null;
        }
        return convertToCsv(userObj);
    }

    public static String convertToCsv(Collection<TreePath> entities) {
        if (entities.size() == 1) {
            return entities.stream().map(e -> convertToCsv(((DefaultMutableTreeNode) e.getLastPathComponent()))).collect(Collectors.joining(","));
        } else {
            return '{' + entities.stream().map(e -> convertToCsv(((DefaultMutableTreeNode) e.getLastPathComponent()))).collect(Collectors.joining(",")) + '}';
        }
    }

    public static String convertToCsv(JTable tableToExport, boolean selectedOnly) {
        TableModel model = tableToExport.getModel();
        StringBuilder csv = new StringBuilder();

        if (!selectedOnly) {
            boolean firstCell = true;
            for (int i = 0; i < model.getColumnCount(); i++) {
                if (firstCell) {
                    firstCell = false;
                } else {
                    csv.append(EXPORT_CELL_DELIMITER);
                }
                csv.append(model.getColumnName(i));
            }

            csv.append(EXPORT_ROW_DELIMITER);

            for (int i = 0; i < model.getRowCount(); i++) {
                firstCell = true;
                for (int j = 0; j < model.getColumnCount(); j++) {
                    firstCell = exportCell(model, csv, firstCell, i, j);
                }
                csv.append(EXPORT_ROW_DELIMITER);
            }
        } else {
            int[] rows = tableToExport.getSelectedRows();
            int[] cols = tableToExport.getSelectedColumns();

            for (int row : rows) {
                boolean firstCell = true;
                for (int col : cols) {
                    firstCell = exportCell(model, csv, firstCell, row, col);
                }
                csv.append(EXPORT_ROW_DELIMITER);
            }

        }
        return csv.toString();
    }

    @SuppressWarnings("SameReturnValue")
    private static boolean exportCell(TableModel model, StringBuilder csv, boolean firstCell, int i, int j) {
        if (!firstCell) {
            csv.append(EXPORT_CELL_DELIMITER);
        }
        Object t = model.getValueAt(i, j);
        if (Tree.class.isAssignableFrom(t.getClass())) {
            Tree tt = (Tree) t;
            TreeModel tm = tt.getModel();
            try {
                csv.append(convertToCsv(((DefaultMutableTreeNode) tm.getRoot()).getUserObject()));
            } catch (Exception e) {
                // skipping non-convertible nodes
            }
        } else {
            csv.append(t.toString());
        }
        return false;
    }
}
