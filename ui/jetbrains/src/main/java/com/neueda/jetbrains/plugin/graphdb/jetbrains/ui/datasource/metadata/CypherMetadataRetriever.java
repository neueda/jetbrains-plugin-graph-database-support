package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultRow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ValueWithIcon;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.CypherMetadataProviderService;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.CypherMetadataType;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

import java.util.List;
import java.util.stream.Collectors;

public class CypherMetadataRetriever {

    private final DatabaseManagerService databaseManager;
    private final MessageBus messageBus;
    private final CypherMetadataProviderService cypherMetadataProviderService;

    public CypherMetadataRetriever(MessageBus messageBus, CypherMetadataProviderService cypherMetadataProviderService) {
        this.messageBus = messageBus;
        this.cypherMetadataProviderService = cypherMetadataProviderService;
        this.databaseManager = ServiceManager.getService(DatabaseManagerService.class);
    }

    public boolean refresh(PatchedDefaultMutableTreeNode node, DataSource nodeDataSource) {
        MetadataRetrieveEvent metadataRetrieveEvent = messageBus.syncPublisher(MetadataRetrieveEvent.METADATA_RETRIEVE_EVENT);

        metadataRetrieveEvent.startMetadataRefresh(nodeDataSource);
        if (nodeDataSource.getDataSourceType().equals(DataSourceType.NEO4J_BOLT)) {
            return refreshNeo4jBoltCypherMetadata(metadataRetrieveEvent, node, nodeDataSource);
        } else {
            metadataRetrieveEvent.metadataRefreshFailed(nodeDataSource, new RuntimeException("Metadata are not supported"));
            return false;
        }
    }

    private boolean refreshNeo4jBoltCypherMetadata(MetadataRetrieveEvent metadataRetrieveEvent, PatchedDefaultMutableTreeNode node, DataSource nodeDataSource) {
        try {
            GraphDatabaseApi db = databaseManager.getDatabaseFor(nodeDataSource);

            List<String> labels = getValues(db.execute("CALL db.labels()"));
            List<String> relationshipTypes = getValues(db.execute("CALL db.relationshipTypes()"));
            List<String> propertyKeys = getValues(db.execute("CALL db.propertyKeys()"));
            GraphQueryResult storedProceduresResult = db.execute("CALL dbms.procedures()");
            List<String> storedProcedures = getValues(storedProceduresResult);

            // Refresh data source window
            node.removeAllChildren();
            PatchedDefaultMutableTreeNode labelsNode = new PatchedDefaultMutableTreeNode(
                    new ValueWithIcon(GraphIcons.Nodes.LABEL, "labels"));
            PatchedDefaultMutableTreeNode relationshipTypesNode = new PatchedDefaultMutableTreeNode(
                    new ValueWithIcon(GraphIcons.Nodes.RELATIONSHIP_TYPE, "relationship types"));
            PatchedDefaultMutableTreeNode propertyKeysNode = new PatchedDefaultMutableTreeNode(
                    new ValueWithIcon(GraphIcons.Nodes.PROPERTY_KEY, "property keys"));
            PatchedDefaultMutableTreeNode storedProceduresNode = new PatchedDefaultMutableTreeNode(
                    new ValueWithIcon(GraphIcons.Nodes.STORED_PROCEDURE, "stored procedures"));

            labels.forEach((label) -> labelsNode.add(new PatchedDefaultMutableTreeNode(label)));
            relationshipTypes.forEach((relType) -> relationshipTypesNode.add(new PatchedDefaultMutableTreeNode(relType)));
            propertyKeys.forEach((propertyKey) -> propertyKeysNode.add(new PatchedDefaultMutableTreeNode(propertyKey)));

            GraphQueryResultColumn nameColumn = storedProceduresResult.getColumns().get(0);
            GraphQueryResultColumn descriptionColumn = storedProceduresResult.getColumns().get(1);
            for (GraphQueryResultRow row : storedProceduresResult.getRows()) {
                PatchedDefaultMutableTreeNode nameNode = new PatchedDefaultMutableTreeNode(row.getValue(nameColumn));
                PatchedDefaultMutableTreeNode descriptionNode = new PatchedDefaultMutableTreeNode(row.getValue(descriptionColumn));
                nameNode.add(descriptionNode);
                storedProceduresNode.add(nameNode);
            }

            node.add(labelsNode);
            node.add(relationshipTypesNode);
            node.add(propertyKeysNode);
            node.add(storedProceduresNode);

            // Refresh cypher metadata provider
            cypherMetadataProviderService.removeMetadata(nodeDataSource.getName());
            cypherMetadataProviderService.registerMetadata(nodeDataSource.getName(), CypherMetadataType.LABELS,
                    labels.stream().map(label -> LookupElementBuilder.create(label)
                            .withIcon(GraphIcons.Nodes.LABEL)
                            .withTypeText("label"))
                            .collect(Collectors.toList()));
            cypherMetadataProviderService.registerMetadata(nodeDataSource.getName(), CypherMetadataType.RELATIONSHIP_TYPES,
                    relationshipTypes.stream().map(relType -> LookupElementBuilder.create(relType)
                            .withIcon(GraphIcons.Nodes.RELATIONSHIP_TYPE)
                            .withTypeText("relationship type"))
                            .collect(Collectors.toList()));
            cypherMetadataProviderService.registerMetadata(nodeDataSource.getName(), CypherMetadataType.PROPERTY_KEYS,
                    propertyKeys.stream().map(propertyKey -> LookupElementBuilder.create(propertyKey)
                            .withIcon(GraphIcons.Nodes.PROPERTY_KEY)
                            .withTypeText("property"))
                            .collect(Collectors.toList()));
            cypherMetadataProviderService.registerMetadata(nodeDataSource.getName(), CypherMetadataType.PROCEDURES,
                    storedProcedures.stream().map(procedure -> LookupElementBuilder.create(procedure)
                            .withIcon(GraphIcons.Nodes.STORED_PROCEDURE)
                            .withTypeText("procedure"))
                            .collect(Collectors.toList()));

            metadataRetrieveEvent.metadataRefreshSucceed(nodeDataSource);
            return true;
        } catch (Exception e) {
            metadataRetrieveEvent.metadataRefreshFailed(nodeDataSource, e);
            return false;
        }
    }

    private List<String> getValues(GraphQueryResult result) {
        return result.getRows().stream()
                .map((row) -> (String) row.getValue(result.getColumns().get(0)))
                .collect(Collectors.toList());
    }
}
