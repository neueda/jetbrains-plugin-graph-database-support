package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSourceV1;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ContextMenu;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ValueWithIcon;

import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.LABELS;

public class ContextMenuService {

    private static final int DATASOURCE_INDEX = 1;
    private static final int METADATA_INDEX = 2;
    private static final int LABELS_DEPTH = 4;

    public Optional<ContextMenu> isContextMenuNeeded(TreePath path) {
        TreeNode lastPathComponent = (TreeNode) path.getLastPathComponent();

        if(path.getPathCount() == LABELS_DEPTH) {
            String metadataType = getMetadataType(path);
            String uuid = getDataSourceUuid(path);
            String data = getMetadataValue(lastPathComponent);

            if (metadataType.equals(LABELS)) {
                return Optional.of(new ContextMenu(LABELS, uuid, data));
            }
        }
        return Optional.empty();
    }

    private String getMetadataValue(TreeNode lastPathComponent) {
        return lastPathComponent.toString();
    }

    private String getMetadataType(TreePath path) {
        Object type = path.getPathComponent(METADATA_INDEX);

        String metadataType = null;
        if (type instanceof PatchedDefaultMutableTreeNode) {
            Object userObject = ((PatchedDefaultMutableTreeNode) type).getUserObject();
            metadataType = ((ValueWithIcon) userObject).getValue();
        }

        return metadataType;
    }

    private String getDataSourceUuid(TreePath path) {
        Object dataSourceNode = path.getPathComponent(DATASOURCE_INDEX);
        String dsUuid = null;
        if (dataSourceNode instanceof PatchedDefaultMutableTreeNode) {
            Object dsUserObject = ((PatchedDefaultMutableTreeNode) dataSourceNode).getUserObject();
            dsUuid = ((DataSourceV1) dsUserObject).getUUID();
        }

        return dsUuid;
    }
}
