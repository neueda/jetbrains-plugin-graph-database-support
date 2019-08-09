package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.OpenTabEvent;

import javax.swing.*;
import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants.ToolWindow.*;

public abstract class MetadataAction extends AnAction {

    private String data;
    private String dataSourceUuid;

    MetadataAction(String data, String dataSourceUuid, String title, String description, Icon icon) {
        super(title, description, icon);
        this.data = data;
        this.dataSourceUuid = dataSourceUuid;
    }

    protected abstract String getQuery(String data);

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        MessageBus messageBus = project.getMessageBus();

        ExecuteQueryEvent executeQueryEvent = messageBus.syncPublisher(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC);

        ExecuteQueryPayload payload = new ExecuteQueryPayload(getQuery(data));

        DataSourcesComponent dataSourcesComponent = project.getComponent(DataSourcesComponent.class);
        Optional<DataSourceApi> dataSource = dataSourcesComponent.getDataSourceContainer().findDataSource(dataSourceUuid);

        dataSource.ifPresent(dataSourceApi -> executeQueryEvent.executeQuery(dataSourceApi, payload));

        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(CONSOLE_TOOL_WINDOW);
        if (!toolWindow.isVisible()) {
            ConsoleToolWindow.ensureOpen(project);
            messageBus.syncPublisher(OpenTabEvent.OPEN_TAB_TOPIC).openTab(Tabs.TABLE);
        }
    }
}
