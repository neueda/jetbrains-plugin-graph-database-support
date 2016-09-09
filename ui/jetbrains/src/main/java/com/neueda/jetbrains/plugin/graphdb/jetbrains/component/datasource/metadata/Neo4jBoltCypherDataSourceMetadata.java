package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Neo4jBoltCypherDataSourceMetadata implements DataSourceMetadata {

    private Map<String, List<Map<String, String>>> dataReceiver = new HashMap<>();

    @Override
    public List<Map<String, String>> getMetadata(String metadataKey) {
        return dataReceiver.get(metadataKey);
    }

    public void addLabels(GraphQueryResult labelsQueryResult) {
        List<Map<String, String>> labels = new ArrayList<>();

        List<GraphQueryResultColumn> columns = labelsQueryResult.getColumns();
        for (GraphQueryResultRow row : labelsQueryResult.getRows()) {
            Map<String, String> data = new HashMap<>();

            for (GraphQueryResultColumn column : columns) {
                data.put(column.getName(), (String) row.getValue(column));
            }

            labels.add(data);
        }

        dataReceiver.put("labels", labels);
    }

    public void addRelationshipTypes(GraphQueryResult relationshipQueryResult) {
        List<Map<String, String>> relationships = new ArrayList<>();

        List<GraphQueryResultColumn> columns = relationshipQueryResult.getColumns();
        for (GraphQueryResultRow row : relationshipQueryResult.getRows()) {
            Map<String, String> data = new HashMap<>();

            for (GraphQueryResultColumn column : columns) {
                data.put(column.getName(), (String) row.getValue(column));
            }

            relationships.add(data);
        }

        dataReceiver.put("relationships", relationships);
    }

    public void addPropertyKeys(GraphQueryResult propertyKeysResult) {
        List<Map<String, String>> propertyKeys = new ArrayList<>();

        List<GraphQueryResultColumn> columns = propertyKeysResult.getColumns();
        for (GraphQueryResultRow row : propertyKeysResult.getRows()) {
            Map<String, String> data = new HashMap<>();

            for (GraphQueryResultColumn column : columns) {
                data.put(column.getName(), (String) row.getValue(column));
            }

            propertyKeys.add(data);
        }

        dataReceiver.put("propertyKeys", propertyKeys);
    }

    public void addStoredProcedures(GraphQueryResult storedProceduresResult) {
        List<Map<String, String>> storedProcedures = new ArrayList<>();

        List<GraphQueryResultColumn> columns = storedProceduresResult.getColumns();
        for (GraphQueryResultRow row : storedProceduresResult.getRows()) {
            Map<String, String> data = new HashMap<>();

            for (GraphQueryResultColumn column : columns) {
                data.put(column.getName(), (String) row.getValue(column));
            }

            storedProcedures.add(data);
        }

        dataReceiver.put("procedures", storedProcedures);
    }
}
