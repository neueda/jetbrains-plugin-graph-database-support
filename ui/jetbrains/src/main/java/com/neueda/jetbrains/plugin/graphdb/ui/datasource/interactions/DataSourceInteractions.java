package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.ToolbarDecorator;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.DataSourcesToolWindow;

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
    }

}
