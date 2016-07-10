package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import org.jetbrains.annotations.Nullable;

public abstract class DataSourceDialog extends DialogWrapper {

    protected DataSourceDialog(@Nullable Project project) {
        super(project);
    }

    public abstract DataSourceApi constructDataSource();

    public boolean go() {
        init();
        return showAndGet();
    }
}
