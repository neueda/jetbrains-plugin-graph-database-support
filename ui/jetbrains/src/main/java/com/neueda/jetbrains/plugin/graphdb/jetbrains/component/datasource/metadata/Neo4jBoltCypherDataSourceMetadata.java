package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Neo4jBoltCypherDataSourceMetadata implements DataSourceMetadata {

    public static final String LABELS = "labels";
    public static final String RELATIONSHIP_TYPES = "relationships";
    public static final String PROPERTY_KEYS = "propertyKeys";
    public static final String STORED_PROCEDURES = "procedures";
    public static final String USER_FUNCTIONS = "functions";

    private Map<String, List<Map<String, String>>> dataReceiver = new HashMap<>();

    @Override
    public List<Map<String, String>> getMetadata(String metadataKey) {
        return dataReceiver.get(metadataKey);
    }

    @Override
    public boolean isMetadataExists(final String metadataKey) {
        return dataReceiver.containsKey(metadataKey);
    }

    public void addLabels(GraphQueryResult labelsQueryResult) {
        addDataSourceMetadata(LABELS, labelsQueryResult);
    }

    public void addRelationshipTypes(GraphQueryResult relationshipQueryResult) {
        addDataSourceMetadata(RELATIONSHIP_TYPES, relationshipQueryResult);
    }

    public void addPropertyKeys(GraphQueryResult propertyKeysResult) {
        addDataSourceMetadata(PROPERTY_KEYS, propertyKeysResult);
    }

    public void addStoredProcedures(GraphQueryResult storedProceduresResult) {
        addDataSourceMetadata(STORED_PROCEDURES, storedProceduresResult);
    }

    public void addUserFunctions(GraphQueryResult userFunctionsResult) {
        addDataSourceMetadata(USER_FUNCTIONS, userFunctionsResult);
    }

    private void addDataSourceMetadata(String key, GraphQueryResult graphQueryResult) {
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

    public void addDataSourceMetadata(String key, List<Map<String, String>> data) {
        dataReceiver.put(key, data);
    }
}
