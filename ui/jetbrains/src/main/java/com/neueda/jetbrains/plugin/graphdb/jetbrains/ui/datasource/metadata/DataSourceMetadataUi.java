package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourcesComponentMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeNodeModel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeNodeModelApi;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.*;

public class DataSourceMetadataUi {

    private static final String RELATIONSHIP_TYPES_TITLE = "relationship types";
    private static final String PROPERTY_KEYS_TITLE = "property keys";
    private static final String LABELS_TITLE = "labels";
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
                           .map(dataSourceMetadata -> updateNeo4jBoltCypherMetadataUi(node, dataSourceMetadata))
                           .orElse(false);
            default:
                return false;
        }
    }

    // ui
    boolean updateNeo4jBoltCypherMetadataUi(PatchedDefaultMutableTreeNode dataSourceRootTreeNode,
                                                    DataSourceMetadata dataSourceMetadata) {
        // Remove existing metadata from ui
        dataSourceRootTreeNode.removeAllChildren();
        TreeNodeModelApi model = (TreeNodeModelApi) dataSourceRootTreeNode.getUserObject();
        DataSourceApi dataSourceApi = model.getDataSourceApi();

        // Labels
        PatchedDefaultMutableTreeNode labelsTreeNode = new PatchedDefaultMutableTreeNode(
                   new TreeNodeModel(LABELS, dataSourceApi, LABELS_TITLE, GraphIcons.Nodes.LABEL));
        dataSourceMetadata
                   .getMetadata(Neo4jBoltCypherDataSourceMetadata.LABELS)
                   .forEach((row) -> labelsTreeNode.add(of(new TreeNodeModel(LABEL, dataSourceApi, row.get("label")))));
        dataSourceRootTreeNode.add(labelsTreeNode);

        // RelTypes
        PatchedDefaultMutableTreeNode relationshipTypesTreeNode = new PatchedDefaultMutableTreeNode(
                new TreeNodeModel(RELATIONSHIPS, dataSourceApi, RELATIONSHIP_TYPES_TITLE, GraphIcons.Nodes.RELATIONSHIP_TYPE));
        dataSourceMetadata
                   .getMetadata(Neo4jBoltCypherDataSourceMetadata.RELATIONSHIP_TYPES)
                   .forEach((row) -> relationshipTypesTreeNode.add(of(new TreeNodeModel(RELATIONSHIP, dataSourceApi, row.get("relationshipType")))));
        dataSourceRootTreeNode.add(relationshipTypesTreeNode);

        // Property Keys
        PatchedDefaultMutableTreeNode propertyKeysTreeNode = new PatchedDefaultMutableTreeNode(
                new TreeNodeModel(PROPERTY_KEYS, dataSourceApi, PROPERTY_KEYS_TITLE, GraphIcons.Nodes.PROPERTY_KEY));
        dataSourceMetadata
                   .getMetadata(Neo4jBoltCypherDataSourceMetadata.PROPERTY_KEYS)
                   .forEach((row) -> propertyKeysTreeNode.add(of(new TreeNodeModel(PROPERTY_KEY, dataSourceApi, row.get("propertyKey")))));
        dataSourceRootTreeNode.add(propertyKeysTreeNode);

        // Stored procedures
        PatchedDefaultMutableTreeNode storedProceduresTreeNode = new PatchedDefaultMutableTreeNode(
                new TreeNodeModel(STORED_PROCEDURES, dataSourceApi, STORED_PROCEDURES_TITLE, GraphIcons.Nodes.STORED_PROCEDURE));
        dataSourceMetadata
                   .getMetadata(Neo4jBoltCypherDataSourceMetadata.STORED_PROCEDURES)
                   .forEach((row) -> {
                       PatchedDefaultMutableTreeNode nameNode = of(new TreeNodeModel(STORED_PROCEDURE, dataSourceApi, row.get("name")));
                       PatchedDefaultMutableTreeNode descriptionNode = of(new TreeNodeModel(STORED_PROCEDURE, dataSourceApi, row.get("signature")));
                       nameNode.add(descriptionNode);
                       storedProceduresTreeNode.add(nameNode);
                   });
        dataSourceRootTreeNode.add(storedProceduresTreeNode);

        // User Functions
        if (dataSourceMetadata.isMetadataExists(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS)) {
            PatchedDefaultMutableTreeNode userFunctionTreeNode = new PatchedDefaultMutableTreeNode(
                    new TreeNodeModel(USER_FUNCTIONS, dataSourceApi, USER_FUNCTIONS_TITLE, GraphIcons.Nodes.USER_FUNCTION));

            dataSourceMetadata
                       .getMetadata(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS)
                       .forEach((row) -> {
                           PatchedDefaultMutableTreeNode nameNode = of(new TreeNodeModel(USER_FUNCTION, dataSourceApi, row.get("name")));
                           PatchedDefaultMutableTreeNode descriptionNode = of(new TreeNodeModel(USER_FUNCTION, dataSourceApi, row.get("signature")));
                           nameNode.add(descriptionNode);
                           userFunctionTreeNode.add(nameNode);
                       });

            dataSourceRootTreeNode.add(userFunctionTreeNode);
        }

        return true;
    }

    private PatchedDefaultMutableTreeNode of(TreeNodeModel model) {
        return new PatchedDefaultMutableTreeNode(model);
    }
}
