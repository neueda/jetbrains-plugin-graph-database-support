package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.container;

import java.util.*;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceContainer;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSourceV1;

@SuppressWarnings("SuspiciousMethodCalls")
public class DataSourceContainerV1 implements DataSourceContainer {

    public List<DataSourceV1> dataSources = new ArrayList<>();

    @Override
    public List getGenericDataSources() {
        return dataSources;
    }

    @Override
    public Optional<DataSourceApi> findDataSource(String uuid) {
        return getDataSources().stream()
                   .filter((dataSource) -> dataSource.getUUID().equals(uuid))
                   .findFirst();
    }

    @Override
    public void addDataSource(DataSourceApi dataSource) {
        getDataSources().add(dataSource);
    }

    @Override
    public void updateDataSource(DataSourceApi oldDataSource, DataSourceApi newDataSource) {
        int index = getDataSources().indexOf(oldDataSource);
        getDataSources().set(index, newDataSource);
    }

    @Override
    public void removeDataSources(List<DataSourceApi> dataSourcesForRemoval) {
        getDataSources().removeAll(dataSourcesForRemoval);
    }

    @Override
    public Optional<DataSourceApi> getDataSource(final String dataSourceName) {
        return getDataSources().stream()
                   .filter((dataSource) -> dataSource.getName().equals(dataSourceName))
                   .findAny();
    }

    public boolean isDataSourceExists(String dataSourceName) {
        return getDataSource(dataSourceName).isPresent();
    }

    @Override
    public DataSourceApi createDataSource(DataSourceApi dataSourceToEdit, DataSourceType dataSourceType,
                                          String dataSourceName, Map<String, String> configuration) {
        String uuid = dataSourceToEdit == null ? UUID.randomUUID().toString() : dataSourceToEdit.getUUID();
        return new DataSourceV1(uuid, dataSourceName, dataSourceType, configuration);
    }
}
