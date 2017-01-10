package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSourceV1;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.ContextMenuService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ContextMenu;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ValueWithIcon;
import org.junit.Before;
import org.junit.Test;

import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;
import java.util.HashMap;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.*;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.DataSourceMetadataUi.LABELS_TITLE;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.DataSourceMetadataUi.RELATIONSHIP_TYPES_TITLE;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.DataSourceMetadataUi.STORED_PROCEDURES_TITLE;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests here rely on a specific implementation details:
 *
 * - PatchedDefaultMutableTreeNode is used for metadata tree
 * - ValueWithIcon is used as userObject in datasource children
 */
public class DataSourceMetadataUiTest {

    private static final String UUID = "localuuid";
    private static final String PERSON = "Person";
    private static final String REL = "rel";

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

        HashMap<String, String> relationshipTypes = new HashMap<>();
        relationshipTypes.put("relationshipType", "rel");

        metadata.addDataSourceMetadata(singletonList(labels), LABELS);
        metadata.addDataSourceMetadata(singletonList(propertyKeys), PROPERTY_KEYS);
        metadata.addDataSourceMetadata(singletonList(procedures), STORED_PROCEDURES);
        metadata.addDataSourceMetadata(singletonList(relationshipTypes), RELATIONSHIP_TYPES);
        metadata.addDataSourceMetadata(singletonList(new HashMap<>()), USER_FUNCTIONS);

        ui.updateNeo4jBoltCypherMetadataUi(datasource, metadata);
    }

    @Test
    public void personLabelClicked() {
        Object[] pathObjects = new Object[]{
                root,
                datasource,
                getChildByName(datasource, LABELS_TITLE),
                getChildByName(datasource, LABELS_TITLE).getChildAt(0)};
        TreePath path = new TreePath(pathObjects);

        assertThat(sut.getContextMenu(path).get())
                .isEqualToComparingFieldByField(new ContextMenu(LABELS, UUID, PERSON));
    }

    @Test
    public void labelItselfClicked() {
        Object[] pathObjects = new Object[]{
                root,
                datasource,
                getChildByName(datasource, LABELS_TITLE)};
        TreePath path = new TreePath(pathObjects);

        assertThat(sut.getContextMenu(path)).isNotPresent();
    }

    @Test
    public void storedProcedureClicked() {
        Object[] pathObjects = new Object[]{
                root,
                datasource,
                getChildByName(datasource, STORED_PROCEDURES_TITLE),
                getChildByName(datasource, STORED_PROCEDURES_TITLE).getChildAt(0)};
        TreePath path = new TreePath(pathObjects);

        assertThat(sut.getContextMenu(path)).isNotPresent();
    }

    @Test
    public void relClicked() {
        Object[] pathObjects = new Object[]{
                root,
                datasource,
                getChildByName(datasource, RELATIONSHIP_TYPES_TITLE),
                getChildByName(datasource, RELATIONSHIP_TYPES_TITLE).getChildAt(0)};
        TreePath path = new TreePath(pathObjects);

        assertThat(sut.getContextMenu(path).get())
                .isEqualToComparingFieldByField(new ContextMenu(RELATIONSHIP_TYPES, UUID, REL));
    }

    private TreeNode getChildByName(TreeNode node, String name) {
        Enumeration children = node.children();
        while (children.hasMoreElements()) {
            PatchedDefaultMutableTreeNode child = (PatchedDefaultMutableTreeNode) children.nextElement();
            String value = ((ValueWithIcon) child.getUserObject()).getValue();
            if (name.equals(value)) {
                return child;
            }
        }
        return null;
    }

}