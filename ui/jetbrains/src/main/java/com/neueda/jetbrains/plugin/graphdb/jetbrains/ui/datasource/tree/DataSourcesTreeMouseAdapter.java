package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto.ContextMenu;

import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.LABELS;

public class DataSourcesTreeMouseAdapter extends MouseAdapter {

    private ContextMenuService contextMenuService = new ContextMenuService();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            Tree tree = (Tree) e.getComponent();
            TreePath pathForLocation = tree.getPathForLocation(e.getX(), e.getY());

            contextMenuService.isContextMenuNeeded(pathForLocation)
                    .ifPresent(dto -> popup(dto, tree));
        }
    }

    private void popup(ContextMenu dto, Tree tree) {
        if (dto.getMetadataType().equals(LABELS)) {
            DataContext dataContext = DataManager.getInstance().getDataContext(tree);
            contextMenuForLabel(dataContext, dto.getData(), dto.getDatasourceUuid());
        }
    }

    private void contextMenuForLabel(DataContext dataContext, String label, String dataSourceUuid) {
        ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                label,
                new MetadataActionGroup(label, dataSourceUuid),
                dataContext,
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                true
        );

        popup.showInBestPositionFor(dataContext);
    }
}