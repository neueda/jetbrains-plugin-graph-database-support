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
        addDataSourceMetadata(labelsQueryResult, "labels");
    }

    public void addRelationshipTypes(GraphQueryResult relationshipQueryResult) {
        addDataSourceMetadata(relationshipQueryResult, "relationships");
    }

    public void addPropertyKeys(GraphQueryResult propertyKeysResult) {
        addDataSourceMetadata(propertyKeysResult, "propertyKeys");
    }

    public void addStoredProcedures(GraphQueryResult storedProceduresResult) {
        addDataSourceMetadata(storedProceduresResult, "procedures");
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
