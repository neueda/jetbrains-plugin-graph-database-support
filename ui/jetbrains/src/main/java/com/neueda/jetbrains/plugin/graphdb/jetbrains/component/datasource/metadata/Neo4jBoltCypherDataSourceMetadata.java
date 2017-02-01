package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Neo4jBoltCypherDataSourceMetadata implements DataSourceMetadata {

    public static final String PROPERTY_KEYS = "propertyKeys";
    public static final String STORED_PROCEDURES = "procedures";
    public static final String USER_FUNCTIONS = "functions";

    private Map<String, List<Map<String, String>>> dataReceiver = new HashMap<>();

    private List<Neo4jLabelMetadata> labels = new ArrayList<>();
    private List<Neo4jRelationshipTypeMetadata> relationshipTypes = new ArrayList<>();

    @Override
    public List<Map<String, String>> getMetadata(String metadataKey) {
        return dataReceiver.get(metadataKey);
    }

    @Override
    public boolean isMetadataExists(final String metadataKey) {
        return dataReceiver.containsKey(metadataKey);
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
                data.put(column.getName(), row.getValue(column).toString());
            }

            dataSourceMetadata.add(data);
        }

        dataReceiver.put(key, dataSourceMetadata);
    }

    public void addDataSourceMetadata(String key, List<Map<String, String>> data) {
        dataReceiver.put(key, data);
    }

    public void addLabels(GraphQueryResult labelCountResult, List<String> labelNames) {
        GraphQueryResultColumn column = labelCountResult.getColumns().get(0);
        for (int i = 0; i < labelCountResult.getRows().size(); i++) {
            GraphQueryResultRow row = labelCountResult.getRows().get(i);
            labels.add(new Neo4jLabelMetadata(labelNames.get(i), (Long) row.getValue(column)));
        }
    }

    public void addLabel(Neo4jLabelMetadata labelMetadata) {
        labels.add(labelMetadata);
    }

    public List<Neo4jLabelMetadata> getLabels() {
        return labels;
    }

    public void addRelationshipTypes(GraphQueryResult relationshipTypeCountResult, List<String> relationshipTypeNames) {
        GraphQueryResultColumn column = relationshipTypeCountResult.getColumns().get(0);
        for (int i = 0; i < relationshipTypeCountResult.getRows().size(); i++) {
            GraphQueryResultRow row = relationshipTypeCountResult.getRows().get(i);
            relationshipTypes.add(new Neo4jRelationshipTypeMetadata(relationshipTypeNames.get(i), (Long) row.getValue(column)));
        }
    }

    public void addRelationshipType(Neo4jRelationshipTypeMetadata relationshipTypeMetadata) {
        relationshipTypes.add(relationshipTypeMetadata);
    }

    public List<Neo4jRelationshipTypeMetadata> getRelationshipTypes() {
        return relationshipTypes;
    }
}
