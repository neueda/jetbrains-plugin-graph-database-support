package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.actions;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DataSourceActionGroup extends ActionGroup {

    private final DataSourceApi dataSourceApi;

    public DataSourceActionGroup(DataSourceApi dataSourceApi) {
        this.dataSourceApi = dataSourceApi;
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent e) {
        return new AnAction[]{
                new DataSourceAction("Open editor", "", null, dataSourceApi),
                new DataSourceOpenBrowserAction("Open in browser", "", null, dataSourceApi)
        };
    }
}
