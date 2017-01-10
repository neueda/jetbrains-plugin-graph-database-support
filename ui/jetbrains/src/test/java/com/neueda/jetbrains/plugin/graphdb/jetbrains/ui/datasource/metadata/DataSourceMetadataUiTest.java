package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSourceV1;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.ContextMenuService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ValueWithIcon;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;
import java.util.HashMap;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.*;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.DataSourceMetadataUi.*;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests here rely on a specific implementation details:
 *
 * - PatchedDefaultMutableTreeNode is used for metadata tree
 * - ValueWithIcon is used as userObject in datasource children
 */
public class DataSourceMetadataUiTest {

    private static final String UUID = "uuid";
    private static final String LABEL = "label";
    private static final String REL = "rel";
    private static final String PROPERTY = "prop";

    private PatchedDefaultMutableTreeNode root;

    private ContextMenuService sut = new ContextMenuService();
    private PatchedDefaultMutableTreeNode datasource;

    @Before
    public void setUp() {
        root = new PatchedDefaultMutableTreeNode("treeRoot");
        DataSourceMetadataUi ui = new DataSourceMetadataUi(null);
        DataSourceV1 dataSourceV1 = new DataSourceV1(UUID, "local", DataSourceType.NEO4J_BOLT, new HashMap<>());
        datasource = new PatchedDefaultMutableTreeNode(dataSourceV1);

        root.add(datasource);
        Neo4jBoltCypherDataSourceMetadata metadata = new Neo4jBoltCypherDataSourceMetadata();

        HashMap<String, String> labels = new HashMap<>();
        labels.put("label", LABEL);

        HashMap<String, String> propertyKeys = new HashMap<>();
        propertyKeys.put("propertyKey", PROPERTY);

        HashMap<String, String> procedures = new HashMap<>();
        procedures.put("signature", "db.labels() :: (label :: STRING?)");
        procedures.put("name", "db.labels");
        procedures.put("description", "List all labels in the database.");

        HashMap<String, String> relationshipTypes = new HashMap<>();
        relationshipTypes.put("relationshipType", REL);

        metadata.addDataSourceMetadata(singletonList(labels), LABELS);
        metadata.addDataSourceMetadata(singletonList(propertyKeys), PROPERTY_KEYS);
        metadata.addDataSourceMetadata(singletonList(procedures), STORED_PROCEDURES);
        metadata.addDataSourceMetadata(singletonList(relationshipTypes), RELATIONSHIP_TYPES);
        metadata.addDataSourceMetadata(singletonList(new HashMap<>()), USER_FUNCTIONS);

        ui.updateNeo4jBoltCypherMetadataUi(datasource, metadata);
    }

    @Test
    public void personLabelClicked() {
        TreePath path = new TreePath(getTreePath(LABELS_TITLE, 0));

        assertThat(sut.getContextMenu(path).get())
                .isEqualToComparingFieldByField(new ContextMenu(LABELS, UUID, LABEL));
    }

    @Test
    public void labelParentClicked() {
        TreePath path = new TreePath(getTreePath(LABELS_TITLE));

        assertThat(sut.getContextMenu(path)).isNotPresent();
    }

    @Test
    public void storedProcedureClicked() {
        TreePath path = new TreePath(getTreePath(STORED_PROCEDURES_TITLE, 0));

        assertThat(sut.getContextMenu(path)).isNotPresent();
    }

    @Test
    public void storedProcedureParentClicked() {
        TreePath path = new TreePath(getTreePath(STORED_PROCEDURES_TITLE));

        assertThat(sut.getContextMenu(path)).isNotPresent();
    }

    @Test
    public void relationshipClicked() {
        TreePath path = new TreePath(getTreePath(RELATIONSHIP_TYPES_TITLE, 0));

        assertThat(sut.getContextMenu(path).get())
                .isEqualToComparingFieldByField(new ContextMenu(RELATIONSHIP_TYPES, UUID, REL));
    }

    @Test
    public void relationshipParentClicked() {
        TreePath path = new TreePath(getTreePath(RELATIONSHIP_TYPES_TITLE));

        assertThat(sut.getContextMenu(path)).isNotPresent();
    }

    @Test
    public void propertyClicked() {
        TreePath path = new TreePath(getTreePath(PROPERTY_KEYS_TITLE, 0));

        assertThat(sut.getContextMenu(path).get())
                .isEqualToComparingFieldByField(new ContextMenu(PROPERTY_KEYS, UUID, PROPERTY));
    }

    @Test
    public void propertyParentClicked() {
        TreePath path = new TreePath(getTreePath(PROPERTY_KEYS_TITLE));

        assertThat(sut.getContextMenu(path)).isNotPresent();
    }

    @NotNull
    private Object[] getTreePath(String group) {
        return new Object[]{
                root,
                datasource,
                getChildByName(datasource, group)};
    }

    @NotNull
    private Object[] getTreePath(String group, Integer childIndex) {
        return new Object[]{
                root,
                datasource,
                getChildByName(datasource, group),
                getChildByName(datasource, group).getChildAt(childIndex)};
    }

    @Test
    public void datasourceClicked() {
        Object[] pathObjects = new Object[]{
                root,
                datasource};
        TreePath path = new TreePath(pathObjects);

        assertThat(sut.getContextMenu(path)).isNotPresent();
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