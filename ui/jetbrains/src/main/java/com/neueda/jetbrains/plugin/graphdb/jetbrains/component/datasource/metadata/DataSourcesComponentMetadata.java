package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphMetadata;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.services.ExecutorService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.MetadataRetrieveEvent;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataContainer;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType.NEO4J_BOLT;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType.OPENCYPHER_GREMLIN;
import static java.util.stream.Collectors.toList;

public class DataSourcesComponentMetadata implements ProjectComponent {

    private final Map<DataSourceType, Function<DataSourceApi, DataSourceMetadata>> handlers = new HashMap<>();
    private CypherMetadataProviderService cypherMetadataProviderService;
    private ExecutorService executorService;
    private DatabaseManagerService databaseManager;
    private MessageBus messageBus;

    public DataSourcesComponentMetadata(MessageBus messageBus,
                                        DatabaseManagerService databaseManager,
                                        CypherMetadataProviderService cypherMetadataProviderService,
                                        ExecutorService executorService
    ) {
        this.messageBus = messageBus;
        this.databaseManager = databaseManager;
        this.cypherMetadataProviderService = cypherMetadataProviderService;
        this.executorService = executorService;

        handlers.put(NEO4J_BOLT, this::getNeo4jBoltMetadata);
        handlers.put(OPENCYPHER_GREMLIN, this::getOpenCypherGremlinMetadata);
    }

    public CompletableFuture<Optional<DataSourceMetadata>> getMetadata(DataSourceApi dataSource) {
        MetadataRetrieveEvent metadataRetrieveEvent = messageBus.syncPublisher(MetadataRetrieveEvent.METADATA_RETRIEVE_EVENT);

        metadataRetrieveEvent.startMetadataRefresh(dataSource);

        CompletableFuture<Optional<DataSourceMetadata>> future = new CompletableFuture<>();
        DataSourceType sourceType = dataSource.getDataSourceType();
        if (handlers.containsKey(sourceType)) {
            executorService.runInBackground(
                    () -> handlers.get(sourceType).apply(dataSource),
                    (metadata) -> {
                        updateNeo4jBoltMetadata(dataSource, (Neo4jBoltCypherDataSourceMetadata) metadata);
                        metadataRetrieveEvent.metadataRefreshSucceed(dataSource, metadata);
                        future.complete(Optional.of(metadata));
                    },
                    (exception) -> {
                        metadataRetrieveEvent.metadataRefreshFailed(dataSource, exception);
                        future.complete(Optional.empty());
                    }
            );
        } else {
            metadataRetrieveEvent.metadataRefreshFailed(dataSource, new RuntimeException("Metadata are not supported"));
            future.complete(Optional.empty());
        }
        return future;
    }

    private DataSourceMetadata getNeo4jBoltMetadata(DataSourceApi dataSource) {
        GraphDatabaseApi db = databaseManager.getDatabaseFor(dataSource);
        Neo4jBoltCypherDataSourceMetadata metadata = new Neo4jBoltCypherDataSourceMetadata();

        GraphQueryResult indexesResult = db.execute("CALL db.indexes()");
        GraphQueryResult constraintsResult = db.execute("CALL db.constraints()");
        GraphQueryResult labelsQueryResult = db.execute("CALL db.labels()");
        GraphQueryResult relationshipQueryResult = db.execute("CALL db.relationshipTypes()");
        GraphQueryResult propertyKeysResult = db.execute("CALL db.propertyKeys()");
        GraphQueryResult storedProceduresResult = db.execute("CALL dbms.procedures()");

        metadata.addIndexes(indexesResult);
        metadata.addConstraints(constraintsResult);

        List<String> listOfLabels = extractLabels(labelsQueryResult);
        if (!listOfLabels.isEmpty()) {
            GraphQueryResult labelCount = db.execute(queryLabelCount(listOfLabels));
            metadata.addLabels(labelCount, listOfLabels);
        }

        List<String> listOfRelationshipTypes = extractRelationshipTypes(relationshipQueryResult);
        if (!listOfRelationshipTypes.isEmpty()) {
            GraphQueryResult relationshipTypeCountResult = db.execute(queryRelationshipTypeCount(listOfRelationshipTypes));
            metadata.addRelationshipTypes(relationshipTypeCountResult, listOfRelationshipTypes);
        }

        metadata.addPropertyKeys(propertyKeysResult);
        metadata.addStoredProcedures(storedProceduresResult);

        boolean supportsUserFunctions = metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.STORED_PROCEDURES)
                .stream()
                .anyMatch((map) -> map.get("name").equals("dbms.functions"));

        if (supportsUserFunctions) {
            GraphQueryResult userFunctionsResult = db.execute("CALL dbms.functions()");
            metadata.addUserFunctions(userFunctionsResult);
        }

        return metadata;
    }

    private DataSourceMetadata getOpenCypherGremlinMetadata(DataSourceApi dataSource) {
        GraphDatabaseApi db = databaseManager.getDatabaseFor(dataSource);
        Neo4jBoltCypherDataSourceMetadata result = new Neo4jBoltCypherDataSourceMetadata();

        GraphMetadata metadata = db.metadata();

        for (Map.Entry<String, Number> entry : metadata.labels().entrySet()) {
            result.addLabel(new Neo4jLabelMetadata(entry.getKey(), entry.getValue().longValue()));
        }

        for (Map.Entry<String, Number> entry : metadata.relationships().entrySet()) {
            result.addRelationshipType(new Neo4jRelationshipTypeMetadata(entry.getKey(), entry.getValue().longValue()));
        }

        for (String vertexProperty : metadata.vertexProperties()) {
            result.addPropertyKey(vertexProperty);
        }

        for (String edgeProperty : metadata.edgeProperties()) {
            result.addPropertyKey(edgeProperty);
        }

        return result;
    }

    private List<String> extractRelationshipTypes(GraphQueryResult relationshipQueryResult) {
        GraphQueryResultColumn column = relationshipQueryResult.getColumns().get(0);
        return relationshipQueryResult.getRows()
                .stream()
                .map(row -> (String) row.getValue(column))
                .collect(toList());
    }

    private List<String> extractLabels(GraphQueryResult labelsQueryResult) {
        GraphQueryResultColumn column = labelsQueryResult.getColumns().get(0);
        return labelsQueryResult.getRows()
                .stream()
                .map(row -> (String) row.getValue(column))
                .collect(toList());
    }

    private String queryRelationshipTypeCount(List<String> relationshipTypes) {
        return relationshipTypes
                .stream()
                .map(relationshipType -> "MATCH ()-[r:`" + relationshipType + "`]->() RETURN count(r)")
                .collect(Collectors.joining(" UNION ALL "));
    }

    private String queryLabelCount(List<String> labels) {
        return labels
                .stream()
                .map(label -> "MATCH (n:`" + label + "`) RETURN count(n)")
                .collect(Collectors.joining(" UNION ALL "));
    }

    private void updateNeo4jBoltMetadata(DataSourceApi dataSource, Neo4jBoltCypherDataSourceMetadata metadata) {
        // Refresh cypher metadata provider
        cypherMetadataProviderService.wipeContainer(dataSource.getName());
        CypherMetadataContainer container = cypherMetadataProviderService.getContainer(dataSource.getName());

        metadata.getLabels()
                .stream()
                .map(Neo4jLabelMetadata::getName)
                .forEach(container::addLabel);
        metadata.getRelationshipTypes()
                .stream()
                .map(Neo4jRelationshipTypeMetadata::getName)
                .forEach(container::addRelationshipType);
        metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.PROPERTY_KEYS).stream()
                .map((row) -> row.get("propertyKey"))
                .forEach(container::addPropertyKey);
        metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.STORED_PROCEDURES)
                .forEach(row -> container.addProcedure(row.get("name"), row.get("signature"), row.get("description")));

        List<Map<String, String>> userFunctionMetadata = metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS);
        if (userFunctionMetadata != null) {
            userFunctionMetadata
                    .forEach(row -> container.addUserFunction(row.get("name"), row.get("signature"), row.get("description")));
        }
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void projectOpened() {
    }

    @Override
    public void projectClosed() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "GraphDatabaseSupport.DataSourcesMetadata";
    }
}
