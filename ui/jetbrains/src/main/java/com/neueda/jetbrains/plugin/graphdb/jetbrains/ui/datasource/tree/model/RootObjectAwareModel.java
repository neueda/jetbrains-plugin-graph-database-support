package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.model;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph.EntityContextMenu;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.dto.ContextMenu;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.TreeNodeModelApi;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class RootObjectAwareModel implements TreeNodeModelApi {

    private DataSourceApi dataSourceApi;
    private Object rootObject;

    public RootObjectAwareModel(DataSourceApi dataSourceApi, Object rootObject) {
        this.dataSourceApi = dataSourceApi;
        this.rootObject = rootObject;
    }

    @Override
    public Optional<ContextMenu> getContextMenu() {
        if (rootObject instanceof GraphEntity) {
            return Optional.of(new EntityContextMenu(dataSourceApi, (GraphEntity) rootObject));
        }
        return Optional.empty();
    }

    @Nullable
    @Override
    public DataSourceApi getDataSourceApi() {
        return dataSourceApi;
    }
}
