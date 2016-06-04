package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.treeStructure.Tree;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.DataSourcesToolWindow;

import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataSourceInteractions {

    private final DataSourcesToolWindow window;
    private final ToolbarDecorator decorator;
    private final Project project;

    public DataSourceInteractions(Project project, DataSourcesToolWindow window) {
        this.project = project;
        this.window = window;
        this.decorator = window.getDecorator();

        initAddAction();
    }

    private void initAddAction() {
        decorator.setAddAction(anActionButton -> {
            ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                    "New Data Source",
                    new NewDataSourceActionGroup(project, window),
                    anActionButton.getDataContext(),
                    JBPopupFactory.ActionSelectionAid.NUMBERING,
                    false
            );
            popup.show(anActionButton.getPreferredPopupPoint());
        });
        decorator.setRemoveAction(anActionButton -> {
            JComponent contextComponent = anActionButton.getContextComponent();
            if (contextComponent instanceof Tree) {
                Tree tree = (Tree) contextComponent;
                DefaultMutableTreeNode[] selectedNodes = tree.getSelectedNodes(DefaultMutableTreeNode.class,
                        (node) -> node.getUserObject() instanceof DataSource);

                List<DataSource> dataSourcesForRemoval = Arrays.stream(selectedNodes)
                        .map((node) -> (DataSource) node.getUserObject())
                        .collect(Collectors.toList());

                window.removeDataSources(dataSourcesForRemoval);
            }
        });
    }
}
