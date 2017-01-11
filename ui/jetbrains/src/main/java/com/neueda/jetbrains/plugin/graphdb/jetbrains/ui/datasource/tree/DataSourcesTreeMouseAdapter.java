package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.actions.DataSourceActionGroup;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.actions.MetadataActionGroup;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DataSourcesTreeMouseAdapter extends MouseAdapter {

    private ContextMenuService contextMenuService = new ContextMenuService();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            Tree tree = (Tree) e.getComponent();
            TreePath pathForLocation = tree.getPathForLocation(e.getX(), e.getY());

            contextMenuService.getContextMenu(pathForLocation)
                    .ifPresent(dto -> popup(dto, tree));
        }
    }

    private void popup(ContextMenu dto, Tree tree) {
        DataContext dataContext = DataManager.getInstance().getDataContext(tree);
        if (dto.getMetadataType() == Neo4jTreeNodeType.LABEL
                || dto.getMetadataType() == Neo4jTreeNodeType.RELATIONSHIP
                || dto.getMetadataType() == Neo4jTreeNodeType.PROPERTY_KEY) {
            metadataContextMenu(dto.getMetadataType(), dto.getData(), dto.getDataSourceApi().getUUID(), dataContext);
        } else if (dto.getMetadataType() == Neo4jTreeNodeType.DATASOURCE) {
            dataSourceContextMenu(dto.getDataSourceApi(), dataContext);
        }
    }

    private void metadataContextMenu(NodeType type, String data, String uuid, DataContext dataContext) {
        ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                data,
                new MetadataActionGroup(type, data, uuid),
                dataContext,
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                true
        );

        popup.showInBestPositionFor(dataContext);
    }

    private void dataSourceContextMenu(DataSourceApi dataSourceApi, DataContext dataContext) {
        ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                dataSourceApi.getName(),
                new DataSourceActionGroup(dataSourceApi),
                dataContext,
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                true
        );

        popup.showInBestPositionFor(dataContext);
    }
}
