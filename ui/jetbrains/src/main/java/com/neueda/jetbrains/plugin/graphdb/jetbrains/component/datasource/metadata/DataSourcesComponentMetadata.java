package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.MetadataRetrieveEvent;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.CypherMetadataProviderService;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.CypherMetadataType;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataSourcesComponentMetadata implements ProjectComponent {

    private DataSourcesComponent component;
    private CypherMetadataProviderService cypherMetadataProviderService;
    private DatabaseManagerService databaseManager;
    private MessageBus messageBus;

    public DataSourcesComponentMetadata(Project project, DataSourcesComponent component) {
        this.component = component;
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
        List<String> labels = metadata.getMetadata("labels").stream()
                .map((row) -> row.get("label"))
                .collect(Collectors.toList());
        List<String> relationshipTypes = metadata.getMetadata("relationships").stream()
                .map((row) -> row.get("relationshipType"))
                .collect(Collectors.toList());
        List<String> propertyKeys = metadata.getMetadata("propertyKeys").stream()
                .map((row) -> row.get("propertyKey"))
                .collect(Collectors.toList());
        List<String> storedProcedures = metadata.getMetadata("procedures").stream()
                .map((row) -> row.get("name"))
                .collect(Collectors.toList());

        cypherMetadataProviderService.removeMetadata(dataSource.getName());
        cypherMetadataProviderService.registerMetadata(dataSource.getName(), CypherMetadataType.LABELS,
                labels.stream().map(label -> LookupElementBuilder.create(label)
                        .withIcon(GraphIcons.Nodes.LABEL)
                        .withTypeText("label"))
                        .collect(Collectors.toList()));
        cypherMetadataProviderService.registerMetadata(dataSource.getName(), CypherMetadataType.RELATIONSHIP_TYPES,
                relationshipTypes.stream().map(relType -> LookupElementBuilder.create(relType)
                        .withIcon(GraphIcons.Nodes.RELATIONSHIP_TYPE)
                        .withTypeText("relationship type"))
                        .collect(Collectors.toList()));
        cypherMetadataProviderService.registerMetadata(dataSource.getName(), CypherMetadataType.PROPERTY_KEYS,
                propertyKeys.stream().map(propertyKey -> LookupElementBuilder.create(propertyKey)
                        .withIcon(GraphIcons.Nodes.PROPERTY_KEY)
                        .withTypeText("property"))
                        .collect(Collectors.toList()));
        cypherMetadataProviderService.registerMetadata(dataSource.getName(), CypherMetadataType.PROCEDURES,
                storedProcedures.stream().map(procedure -> LookupElementBuilder.create(procedure)
                        .withIcon(GraphIcons.Nodes.STORED_PROCEDURE)
                        .withTypeText("procedure"))
                        .collect(Collectors.toList()));
    }

    @Override
    public void initComponent() {
        component.getDataSourceContainer().getDataSources().forEach(this::getMetadata);
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
