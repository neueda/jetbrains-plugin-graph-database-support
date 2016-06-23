package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;

public class ChooseDataSourceAction extends AnAction {

    private final DataSource dataSource;
    private final MessageBus messageBus;
    private final ExecuteQueryPayload executeQueryPayload;

    public ChooseDataSourceAction(DataSource dataSource, DataSourcesComponent component,
                                  MessageBus messageBus, ExecuteQueryPayload executeQueryPayload) {
        super(dataSource.getName(), null, component.getDataSourceDescription(dataSource).getIcon());
        this.dataSource = dataSource;
        this.messageBus = messageBus;
        this.executeQueryPayload = executeQueryPayload;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        ExecuteQueryEvent executeQueryEvent = messageBus.syncPublisher(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC);
        executeQueryEvent.executeQuery(dataSource, executeQueryPayload);
    }
}
