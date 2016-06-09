package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;

import java.util.ArrayList;
import java.util.List;

@State(name = "GraphDatabaseSupport.DataSourcesState",
        storages = {@Storage("GraphDatabaseSupport_DataSourcesState.xml")})
public class DataSourcesComponentState {

    public List<DataSource> dataSources = new ArrayList<>();
}
