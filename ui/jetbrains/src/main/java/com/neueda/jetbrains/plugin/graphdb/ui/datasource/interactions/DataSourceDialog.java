package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSource;
import org.jetbrains.annotations.Nullable;

public abstract class DataSourceDialog extends DialogWrapper {

    protected DataSourceDialog(@Nullable Project project) {
        super(project);
    }

    public abstract DataSource constructDataSource();

    public boolean go() {
        init();
        return showAndGet();
    }
}
