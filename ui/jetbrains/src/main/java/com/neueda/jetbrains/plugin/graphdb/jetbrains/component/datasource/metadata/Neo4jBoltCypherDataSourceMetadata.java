package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

import java.util.*;

import com.neueda.jetbrains.plugin.graphdb.database.api.query.*;

public class Neo4jBoltCypherDataSourceMetadata implements DataSourceMetadata {

    public static final String LABELS = "labels";
    public static final String RELATIONSHIP_TYPES = "relationships";
    public static final String PROPERTY_KEYS = "propertyKeys";
    public static final String STORED_PROCEDURES = "procedures";

    private Map<String, List<Map<String, String>>> dataReceiver = new HashMap<>();

    @Override
    public List<Map<String, String>> getMetadata(String metadataKey) {
        return dataReceiver.get(metadataKey);
    }

    public void addLabels(GraphQueryResult labelsQueryResult) {
        addDataSourceMetadata(labelsQueryResult, LABELS);
    }

    public void addRelationshipTypes(GraphQueryResult relationshipQueryResult) {
        addDataSourceMetadata(relationshipQueryResult, RELATIONSHIP_TYPES);
    }

    public void addPropertyKeys(GraphQueryResult propertyKeysResult) {
        addDataSourceMetadata(propertyKeysResult, PROPERTY_KEYS);
    }

    public void addStoredProcedures(GraphQueryResult storedProceduresResult) {
        addDataSourceMetadata(storedProceduresResult, STORED_PROCEDURES);
    }

    private void addDataSourceMetadata(GraphQueryResult graphQueryResult, String key) {
        List<Map<String, String>> dataSourceMetadata = new ArrayList<>();

        List<GraphQueryResultColumn> columns = graphQueryResult.getColumns();
        for (GraphQueryResultRow row : graphQueryResult.getRows()) {
            Map<String, String> data = new HashMap<>();

            for (GraphQueryResultColumn column : columns) {
                data.put(column.getName(), (String) row.getValue(column));
            }

            dataSourceMetadata.add(data);
        }

        dataReceiver.put(key, dataSourceMetadata);
    }
}
