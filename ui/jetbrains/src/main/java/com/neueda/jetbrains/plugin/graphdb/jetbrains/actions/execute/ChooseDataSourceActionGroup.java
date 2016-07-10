package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChooseDataSourceActionGroup extends ActionGroup {

    private final MessageBus messageBus;
    private final DataSourcesComponent component;
    private final ExecuteQueryPayload executeQueryPayload;

    public ChooseDataSourceActionGroup(MessageBus messageBus,
                                       DataSourcesComponent component,
                                       ExecuteQueryPayload executeQueryPayload) {
        this.messageBus = messageBus;
        this.component = component;
        this.executeQueryPayload = executeQueryPayload;
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent e) {
        return component.getDataSourceContainer().getDataSources().stream()
                .map((dataSource) -> new ChooseDataSourceAction(dataSource, component, messageBus, executeQueryPayload))
                .toArray(AnAction[]::new);
    }
}
