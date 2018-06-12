package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import java.util.Optional;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.execution.console.ConsoleRootType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceDescription;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.NameUtil;

public class GraphDbEditorsConsoleRootType extends ConsoleRootType {

    @NotNull
    public static GraphDbEditorsConsoleRootType getInstance() {
        return findByClass(GraphDbEditorsConsoleRootType.class);
    }

    GraphDbEditorsConsoleRootType() {
        super("graphdb-editors", "Graph Database Data Source editor");
    }

    @Nullable
    @Override
    public Icon substituteIcon(@NotNull Project project, @NotNull VirtualFile file) {
        return getDataSource(project, file)
                   .map(DataSourceApi::getDescription)
                   .map(DataSourceDescription::getIcon)
                   .orElse(null);
    }

    @Nullable
    @Override
    public String substituteName(@NotNull Project project, @NotNull VirtualFile file) {
        return getDataSource(project, file)
                   .map(DataSourceApi::getName)
                   .orElse(null);
    }

    private Optional<? extends DataSourceApi> getDataSource(@NotNull Project project, @NotNull VirtualFile file) {
        if (file.isDirectory()) {
            return Optional.empty();
        }

        DataSourcesComponent component = project.getComponent(DataSourcesComponent.class);
        return component.getDataSourceContainer()
                   .findDataSource(NameUtil.extractDataSourceUUID(file.getName()));
    }
}
