package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourcesComponentMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree.MutableTreeNodeProducers;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree.Root;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.tree.TreeNodeDirectory;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.LabelTreeNodeModel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.MetadataTreeNodeModel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.RelationshipTypeTreeNodeModel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeNodeModelApi;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;

import javax.swing.tree.MutableTreeNode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.CONSTRAINT;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.CONSTRAINTS;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.INDEX;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.INDEXES;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.LABEL;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.LABELS;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.PROPERTY_KEY;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.PROPERTY_KEYS;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.RELATIONSHIP;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.RELATIONSHIPS;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.STORED_PROCEDURE;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.STORED_PROCEDURES;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.USER_FUNCTION;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.Neo4jTreeNodeType.USER_FUNCTIONS;

public class DataSourceMetadataUi {

    private static final String RELATIONSHIP_TYPES_TITLE = "relationship types (%s)";
    private static final String PROPERTY_KEYS_TITLE = "property keys";
    private static final String LABELS_TITLE = "labels (%s)";
    private static final String STORED_PROCEDURES_TITLE = "stored procedures";
    private static final String USER_FUNCTIONS_TITLE = "user functions";
    private static final String INDEXES_TITLE = "indexes (%s)";
    private static final String CONSTRAINTS_TITLE = "constraints (%s)";

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

        dataSourceRootTreeNode.add(createConstraintsNode(dataSourceMetadata, dataSourceApi));
        dataSourceRootTreeNode.add(createIndexesNode(dataSourceMetadata, dataSourceApi));

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

        List<Map<String, String>> storedProcedures = dataSourceMetadata
                .getMetadata(Neo4jBoltCypherDataSourceMetadata.STORED_PROCEDURES);

        createTreeFromMetadata(storedProcedures, storedProceduresTreeNode, STORED_PROCEDURE, dataSourceApi);

        dataSourceRootTreeNode.add(storedProceduresTreeNode);

        // User Functions
        if (dataSourceMetadata.isMetadataExists(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS)) {
            PatchedDefaultMutableTreeNode userFunctionTreeNode = new PatchedDefaultMutableTreeNode(
                new MetadataTreeNodeModel(USER_FUNCTIONS, dataSourceApi, USER_FUNCTIONS_TITLE, GraphIcons.Nodes.USER_FUNCTION));

            List<Map<String, String>> storedFunctions = dataSourceMetadata
                    .getMetadata(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS);

            createTreeFromMetadata(storedFunctions, userFunctionTreeNode, USER_FUNCTION, dataSourceApi);

            dataSourceRootTreeNode.add(userFunctionTreeNode);
        }

        return true;
    }

    private void createTreeFromMetadata(List<Map<String, String>> metadata,
                                        PatchedDefaultMutableTreeNode treeRoot,
                                        Neo4jTreeNodeType type,
                                        DataSourceApi dataSourceApi) {
        TreeNodeDirectory treeNodeRoot = new Root(
                treeRoot,
                MutableTreeNodeProducers.directory(type, dataSourceApi),
                MutableTreeNodeProducers.function(type, dataSourceApi)
        );

        metadata.forEach(data -> {
            String[] nameParts = data.getOrDefault("name", "").split("\\.");
            nameParts[nameParts.length - 1] = data.get("signature");
            createBranch(treeNodeRoot, Arrays.asList(nameParts));
        });

        treeNodeRoot.getMutableTreeNode();
    }

    private void createBranch(TreeNodeDirectory directory, List<String> names) {
        TreeNodeDirectory currentDirectory = directory;
        for (int i = 0; i < names.size(); i++) {
            if (i < names.size() - 1) {
                currentDirectory = currentDirectory.getDirectory(names.get(i));
            } else {
                currentDirectory.addLeaf(names.get(i));
            }
        }
    }

    private MutableTreeNode createIndexesNode(DataSourceMetadata dataSourceMetadata, DataSourceApi dataSourceApi) {
        List<Map<String, String>> indexesMetadata =
                dataSourceMetadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.INDEXES);
        PatchedDefaultMutableTreeNode indexTreeNode = new PatchedDefaultMutableTreeNode(
                new MetadataTreeNodeModel(INDEXES,
                        dataSourceApi,
                        String.format(INDEXES_TITLE, indexesMetadata.size()),
                        GraphIcons.Nodes.INDEX));
        indexesMetadata
                .forEach(row -> indexTreeNode.add(of(new MetadataTreeNodeModel(INDEX, dataSourceApi,
                        row.get("description").substring(6) + " " + row.get("state")))));

        return indexTreeNode;
    }

    private MutableTreeNode createConstraintsNode(DataSourceMetadata dataSourceMetadata, DataSourceApi dataSourceApi) {
        List<Map<String, String>> constraintsMetadata =
                dataSourceMetadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.CONSTRAINTS);
        PatchedDefaultMutableTreeNode indexTreeNode = new PatchedDefaultMutableTreeNode(
                new MetadataTreeNodeModel(CONSTRAINTS, dataSourceApi,
                        String.format(CONSTRAINTS_TITLE, constraintsMetadata.size()), GraphIcons.Nodes.CONSTRAINT));
        constraintsMetadata
                .forEach(row ->
                        indexTreeNode.add(of(new MetadataTreeNodeModel(CONSTRAINT, dataSourceApi,
                                row.get("description").substring(11)))));

        return indexTreeNode;
    }

    private PatchedDefaultMutableTreeNode of(MetadataTreeNodeModel model) {
        return new PatchedDefaultMutableTreeNode(model);
    }
}
