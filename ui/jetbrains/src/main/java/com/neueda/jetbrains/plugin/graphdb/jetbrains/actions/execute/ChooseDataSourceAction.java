package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;

public class ChooseDataSourceAction extends AnAction {

    private final DataSourceApi dataSource;
    private final MessageBus messageBus;
    private final ExecuteQueryPayload executeQueryPayload;

    public ChooseDataSourceAction(DataSourceApi dataSource, DataSourcesComponent component,
                                  MessageBus messageBus, ExecuteQueryPayload executeQueryPayload) {
        super(dataSource.getName(), null, dataSource.getDescription().getIcon());
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
