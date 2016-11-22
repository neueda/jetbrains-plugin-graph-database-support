package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.MetadataRetrieveEvent;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataContainer;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DataSourcesComponentMetadata implements ProjectComponent {

    private CypherMetadataProviderService cypherMetadataProviderService;
    private DatabaseManagerService databaseManager;
    private MessageBus messageBus;

    public DataSourcesComponentMetadata(Project project) {
        this.messageBus = project.getMessageBus();
        this.databaseManager = ServiceManager.getService(DatabaseManagerService.class);
        this.cypherMetadataProviderService = ServiceManager.getService(project, CypherMetadataProviderService.class);
    }

    public Optional<DataSourceMetadata> getMetadata(DataSourceApi dataSource) {
        MetadataRetrieveEvent metadataRetrieveEvent = messageBus.syncPublisher(MetadataRetrieveEvent.METADATA_RETRIEVE_EVENT);

        metadataRetrieveEvent.startMetadataRefresh(dataSource);
        switch (dataSource.getDataSourceType()) {
            case NEO4J_BOLT:
                try {
                    DataSourceMetadata metadata = getNeo4jBoltMetadata(dataSource);
                    updateNeo4jBoltMetadata(dataSource, metadata);
                    metadataRetrieveEvent.metadataRefreshSucceed(dataSource);
                    return Optional.of(metadata);
                } catch (Exception exception) {
                    metadataRetrieveEvent.metadataRefreshFailed(dataSource, exception);
                }
                break;
            default:
                metadataRetrieveEvent.metadataRefreshFailed(dataSource, new RuntimeException("Metadata are not supported"));
                break;
        }
        return Optional.empty();
    }

    private DataSourceMetadata getNeo4jBoltMetadata(DataSourceApi dataSource) {
        GraphDatabaseApi db = databaseManager.getDatabaseFor(dataSource);
        Neo4jBoltCypherDataSourceMetadata metadata = new Neo4jBoltCypherDataSourceMetadata();

        GraphQueryResult labelsQueryResult = db.execute("CALL db.labels()");
        GraphQueryResult relationshipQueryResult = db.execute("CALL db.relationshipTypes()");
        GraphQueryResult propertyKeys = db.execute("CALL db.propertyKeys()");
        GraphQueryResult storedProceduresResult = db.execute("CALL dbms.procedures()");

        metadata.addLabels(labelsQueryResult);
        metadata.addRelationshipTypes(relationshipQueryResult);
        metadata.addPropertyKeys(propertyKeys);
        metadata.addStoredProcedures(storedProceduresResult);

        return metadata;
    }

    private void updateNeo4jBoltMetadata(DataSourceApi dataSource, DataSourceMetadata metadata) {
        // Refresh cypher metadata provider
        cypherMetadataProviderService.wipeContainer(dataSource.getName());
        CypherMetadataContainer container = cypherMetadataProviderService.getContainer(dataSource.getName());

        metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.LABELS).stream()
                .map((row) -> row.get("label"))
                .forEach(container::addLabel);
        metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.RELATIONSHIP_TYPES).stream()
                .map((row) -> row.get("relationshipType"))
                .forEach(container::addRelationshipType);
        metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.PROPERTY_KEYS).stream()
                .map((row) -> row.get("propertyKey"))
                .forEach(container::addPropertyKey);
        metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.STORED_PROCEDURES)
                .forEach(row -> container.addProcedure(row.get("name"), row.get("signature")));
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
