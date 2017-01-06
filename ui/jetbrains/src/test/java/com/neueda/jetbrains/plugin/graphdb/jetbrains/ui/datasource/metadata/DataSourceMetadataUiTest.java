package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSourceV1;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ContextMenu;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.ContextMenuService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.tree.TreePath;
import java.util.HashMap;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.*;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class DataSourceMetadataUiTest {

    public static final String UUID = "localuuid";
    public static final String PERSON = "Person";
    private DataSourceMetadataUi ui;
    private PatchedDefaultMutableTreeNode root;
    private Neo4jBoltCypherDataSourceMetadata metadata = new Neo4jBoltCypherDataSourceMetadata();

    private ContextMenuService sut = new ContextMenuService();
    private PatchedDefaultMutableTreeNode datasource;

    @Before
    public void setUp() {
        ui = new DataSourceMetadataUi(null);
        root = new PatchedDefaultMutableTreeNode("treeRoot");
        DataSourceV1 dataSourceV1 = new DataSourceV1(UUID, "local", DataSourceType.NEO4J_BOLT, new HashMap<>());
        datasource = new PatchedDefaultMutableTreeNode(dataSourceV1);
        root.add(datasource);

        root.add(datasource);
        metadata = new Neo4jBoltCypherDataSourceMetadata();

        HashMap<String, String> labels = new HashMap<>();
        labels.put("label", PERSON);

        HashMap<String, String> propertyKeys = new HashMap<>();
        propertyKeys.put("propertyKey", "name");

        HashMap<String, String> procedures = new HashMap<>();
        procedures.put("signature", "db.labels() :: (label :: STRING?)");
        procedures.put("name", "db.labels");
        procedures.put("description", "List all labels in the database.");

        metadata.addDataSourceMetadata(singletonList(labels), LABELS);
        metadata.addDataSourceMetadata(singletonList(propertyKeys), PROPERTY_KEYS);
        metadata.addDataSourceMetadata(singletonList(procedures), STORED_PROCEDURES);
        metadata.addDataSourceMetadata(singletonList(new HashMap<>()), RELATIONSHIP_TYPES);
        metadata.addDataSourceMetadata(singletonList(new HashMap<>()), USER_FUNCTIONS);
    }

    @Test
    public void personLabelClicked() {
        ui.updateNeo4jBoltCypherMetadataUi(datasource, metadata);

        Object[] pathObjects = new Object[]{
                root,
                datasource,
                datasource.getChildAt(0),
                datasource.getChildAt(0).getChildAt(0)};
        TreePath path = new TreePath(pathObjects);

        assertThat(sut.isContextMenuNeeded(path).get())
                .isEqualToComparingFieldByField(new ContextMenu(LABELS, UUID, PERSON));
    }

    @Test
    public void labelItselfClicked() {
        ui.updateNeo4jBoltCypherMetadataUi(datasource, metadata);

        Object[] pathObjects = new Object[]{
                root,
                datasource,
                datasource.getChildAt(0)};
        TreePath path = new TreePath(pathObjects);

        assertThat(sut.isContextMenuNeeded(path)).isPresent();
    }

    @Test
    public void storedProcedureClicked() {
        ui.updateNeo4jBoltCypherMetadataUi(datasource, metadata);

        Object[] pathObjects = new Object[]{
                root,
                datasource,
                datasource.getChildAt(2),
                datasource.getChildAt(2).getChildAt(0)};
        TreePath path = new TreePath(pathObjects);

        assertThat(sut.isContextMenuNeeded(path)).isPresent();
    }

}