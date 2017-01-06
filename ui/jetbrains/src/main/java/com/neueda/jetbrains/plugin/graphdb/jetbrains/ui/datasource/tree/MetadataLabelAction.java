package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;

import javax.swing.*;
import java.util.Optional;

public class MetadataLabelAction extends AnAction {

    private static final String LABEL_QUERY_START = "MATCH (n:";
    private static final String LABEL_QUERY_END = ") RETURN n LIMIT 25";

    private String label;
    private String dataSourceUuid;

    public MetadataLabelAction(String label, String dataSourceUuid, String title, String description, Icon icon) {
        super(title, description, icon);
        this.label = label;
        this.dataSourceUuid = dataSourceUuid;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        MessageBus messageBus = project.getMessageBus();

        ExecuteQueryEvent executeQueryEvent = messageBus.syncPublisher(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC);

        ExecuteQueryPayload payload = new ExecuteQueryPayload(LABEL_QUERY_START + label + LABEL_QUERY_END);

        DataSourcesComponent dataSourcesComponent = project.getComponent(DataSourcesComponent.class);
        Optional<DataSourceApi> dataSource = dataSourcesComponent.getDataSourceContainer().findDataSource(dataSourceUuid);

        executeQueryEvent.executeQuery(dataSource.get(), payload);
    }
}
