package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourcesComponentMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.LabelTreeNodeModel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.MetadataTreeNodeModel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.RelationshipTypeTreeNodeModel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeNodeModelApi;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.*;

public class DataSourceMetadataUi {

    private static final String RELATIONSHIP_TYPES_TITLE = "relationship types (%s)";
    private static final String PROPERTY_KEYS_TITLE = "property keys";
    private static final String LABELS_TITLE = "labels (%s)";
    private static final String STORED_PROCEDURES_TITLE = "stored procedures";
    private static final String USER_FUNCTIONS_TITLE = "user functions";

    private final DataSourcesComponentMetadata dataSourcesComponent;

    public DataSourceMetadataUi(DataSourcesComponentMetadata dataSourcesComponent) {
        this.dataSourcesComponent = dataSourcesComponent;
    }

    public boolean updateDataSourceMetadataUi(PatchedDefaultMutableTreeNode node, DataSourceApi nodeDataSource) {
        switch (nodeDataSource.getDataSourceType()) {
            case NEO4J_BOLT:
                return dataSourcesComponent.getMetadata(nodeDataSource)
                    .map(genericMetadata -> (Neo4jBoltCypherDataSourceMetadata) genericMetadata)
                    .map(neo4jMetadata -> updateNeo4jBoltCypherMetadataUi(node, neo4jMetadata))
                    .orElse(false);
            default:
                return false;
        }
    }

    // ui
    boolean updateNeo4jBoltCypherMetadataUi(PatchedDefaultMutableTreeNode dataSourceRootTreeNode,
                                            Neo4jBoltCypherDataSourceMetadata dataSourceMetadata) {
        // Remove existing metadata from ui
        dataSourceRootTreeNode.removeAllChildren();
        TreeNodeModelApi model = (TreeNodeModelApi) dataSourceRootTreeNode.getUserObject();
        DataSourceApi dataSourceApi = model.getDataSourceApi();

        // Labels
        int labelCount = dataSourceMetadata.getLabels().size();
        PatchedDefaultMutableTreeNode labelsTreeNode = new PatchedDefaultMutableTreeNode(
            new MetadataTreeNodeModel(LABELS, dataSourceApi, String.format(LABELS_TITLE, labelCount), GraphIcons.Nodes.LABEL));
        dataSourceMetadata.getLabels()
            .stream()
            .map(label -> new LabelTreeNodeModel(LABEL, dataSourceApi, label.getName(), label.getCount()))
            .forEach(labelModel -> labelsTreeNode.add(of(labelModel)));
        dataSourceRootTreeNode.add(labelsTreeNode);

        // RelTypes
        int relationshipTypesCount = dataSourceMetadata.getRelationshipTypes().size();
        String relationshipTypesName = String.format(RELATIONSHIP_TYPES_TITLE, relationshipTypesCount);
        PatchedDefaultMutableTreeNode relationshipTypesTreeNode = new PatchedDefaultMutableTreeNode(
            new MetadataTreeNodeModel(RELATIONSHIPS, dataSourceApi, relationshipTypesName, GraphIcons.Nodes.RELATIONSHIP_TYPE));
        dataSourceMetadata.getRelationshipTypes()
            .stream()
            .map(rel -> new RelationshipTypeTreeNodeModel(RELATIONSHIP, dataSourceApi, rel.getName(), rel.getCount()))
            .forEach(relModel -> relationshipTypesTreeNode.add(of(relModel)));
        dataSourceRootTreeNode.add(relationshipTypesTreeNode);

        // Property Keys
        PatchedDefaultMutableTreeNode propertyKeysTreeNode = new PatchedDefaultMutableTreeNode(
            new MetadataTreeNodeModel(PROPERTY_KEYS, dataSourceApi, PROPERTY_KEYS_TITLE, GraphIcons.Nodes.PROPERTY_KEY));
        dataSourceMetadata
            .getMetadata(Neo4jBoltCypherDataSourceMetadata.PROPERTY_KEYS)
            .forEach((row) -> propertyKeysTreeNode.add(of(new MetadataTreeNodeModel(PROPERTY_KEY, dataSourceApi, row.get("propertyKey")))));
        dataSourceRootTreeNode.add(propertyKeysTreeNode);

        // Stored procedures
        PatchedDefaultMutableTreeNode storedProceduresTreeNode = new PatchedDefaultMutableTreeNode(
            new MetadataTreeNodeModel(STORED_PROCEDURES, dataSourceApi, STORED_PROCEDURES_TITLE, GraphIcons.Nodes.STORED_PROCEDURE));
        dataSourceMetadata
            .getMetadata(Neo4jBoltCypherDataSourceMetadata.STORED_PROCEDURES)
            .forEach((row) -> {
                PatchedDefaultMutableTreeNode nameNode = of(new MetadataTreeNodeModel(STORED_PROCEDURE, dataSourceApi, row.get("name")));
                PatchedDefaultMutableTreeNode descriptionNode = of(new MetadataTreeNodeModel(STORED_PROCEDURE, dataSourceApi, row.get("signature")));
                nameNode.add(descriptionNode);
                storedProceduresTreeNode.add(nameNode);
            });
        dataSourceRootTreeNode.add(storedProceduresTreeNode);

        // User Functions
        if (dataSourceMetadata.isMetadataExists(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS)) {
            PatchedDefaultMutableTreeNode userFunctionTreeNode = new PatchedDefaultMutableTreeNode(
                new MetadataTreeNodeModel(USER_FUNCTIONS, dataSourceApi, USER_FUNCTIONS_TITLE, GraphIcons.Nodes.USER_FUNCTION));

            dataSourceMetadata
                .getMetadata(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS)
                .forEach((row) -> {
                    PatchedDefaultMutableTreeNode nameNode = of(new MetadataTreeNodeModel(USER_FUNCTION, dataSourceApi, row.get("name")));
                    PatchedDefaultMutableTreeNode descriptionNode = of(new MetadataTreeNodeModel(USER_FUNCTION, dataSourceApi, row.get("signature")));
                    nameNode.add(descriptionNode);
                    userFunctionTreeNode.add(nameNode);
                });

            dataSourceRootTreeNode.add(userFunctionTreeNode);
        }

        return true;
    }

    private PatchedDefaultMutableTreeNode of(MetadataTreeNodeModel model) {
        return new PatchedDefaultMutableTreeNode(model);
    }
}
