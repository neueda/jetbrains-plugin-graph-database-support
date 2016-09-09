package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ValueWithIcon;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

import java.util.Optional;

public class DataSourceMetadataUi {

    private final DataSourcesComponent dataSourcesComponent;

    public DataSourceMetadataUi(DataSourcesComponent dataSourcesComponent) {
        this.dataSourcesComponent = dataSourcesComponent;
    }

    public boolean updateDataSourceMetadataUi(PatchedDefaultMutableTreeNode node, DataSourceApi nodeDataSource) {
        switch (nodeDataSource.getDataSourceType()) {
            case NEO4J_BOLT:
                Optional<DataSourceMetadata> dataSourceMetadata = dataSourcesComponent.getMetadata(nodeDataSource);
                if (dataSourceMetadata.isPresent()) {
                    return updateNeo4jBoltCypherMetadataUi(node, dataSourceMetadata.get());
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    // ui
    private boolean updateNeo4jBoltCypherMetadataUi(PatchedDefaultMutableTreeNode dataSourceRootTreeNode,
                                                    DataSourceMetadata dataSourceMetadata) {
        // Remove existing metadata from ui
        dataSourceRootTreeNode.removeAllChildren();

        // Prepare new metadata root tree nodes
        PatchedDefaultMutableTreeNode labelsTreeNode = new PatchedDefaultMutableTreeNode(
                new ValueWithIcon(GraphIcons.Nodes.LABEL, "labels"));
        PatchedDefaultMutableTreeNode relationshipTypesTreeNode = new PatchedDefaultMutableTreeNode(
                new ValueWithIcon(GraphIcons.Nodes.RELATIONSHIP_TYPE, "relationship types"));
        PatchedDefaultMutableTreeNode propertyKeysTreeNode = new PatchedDefaultMutableTreeNode(
                new ValueWithIcon(GraphIcons.Nodes.PROPERTY_KEY, "property keys"));
        PatchedDefaultMutableTreeNode storedProceduresTreeNode = new PatchedDefaultMutableTreeNode(
                new ValueWithIcon(GraphIcons.Nodes.STORED_PROCEDURE, "stored procedures"));

        // Update metadata tree nodes
        dataSourceMetadata
                .getMetadata("labels")
                .forEach((row) -> labelsTreeNode.add(new PatchedDefaultMutableTreeNode(row.get("label"))));
        dataSourceMetadata
                .getMetadata("relationships")
                .forEach((row) -> relationshipTypesTreeNode.add(new PatchedDefaultMutableTreeNode(row.get("relationshipType"))));
        dataSourceMetadata
                .getMetadata("propertyKeys")
                .forEach((row) -> propertyKeysTreeNode.add(new PatchedDefaultMutableTreeNode(row.get("propertyKey"))));
        dataSourceMetadata
                .getMetadata("procedures")
                .forEach((row) -> {
                    PatchedDefaultMutableTreeNode nameNode = new PatchedDefaultMutableTreeNode(row.get("name"));
                    PatchedDefaultMutableTreeNode descriptionNode = new PatchedDefaultMutableTreeNode(row.get("signature"));
                    nameNode.add(descriptionNode);
                    storedProceduresTreeNode.add(nameNode);
                });

        // Add metadata tree nodes back to UI
        dataSourceRootTreeNode.add(labelsTreeNode);
        dataSourceRootTreeNode.add(relationshipTypesTreeNode);
        dataSourceRootTreeNode.add(propertyKeysTreeNode);
        dataSourceRootTreeNode.add(storedProceduresTreeNode);

        return true;
    }
}
