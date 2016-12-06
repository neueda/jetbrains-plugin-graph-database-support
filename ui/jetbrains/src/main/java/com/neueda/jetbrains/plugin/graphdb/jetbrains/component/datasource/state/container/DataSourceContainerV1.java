package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.container;

import java.util.*;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceContainer;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSourceV1;

public class DataSourceContainerV1 implements DataSourceContainer {

    public List<DataSourceApi> dataSources = new ArrayList<>();

    @Override
    public List getGenericDataSources() {
        return dataSources;
    }

    @Override
    public DataSourceApi findDataSource(String uuid) {
        Optional<DataSourceApi> foundDataSource = dataSources.stream()
                   .filter((dataSource) -> dataSource.getUUID().equals(uuid))
                   .findFirst();

        if (foundDataSource.isPresent()) {
            return foundDataSource.get();
        } else {
            throw new IllegalStateException("Data source with UUID[" + uuid + "] does not exist");
        }
    }

    @Override
    public void addDataSource(DataSourceApi dataSource) {
        dataSources.add(dataSource);
    }

    @Override
    public void updateDataSource(DataSourceApi oldDataSource, DataSourceApi newDataSource) {
        int index = dataSources.indexOf(oldDataSource);
        dataSources.set(index, newDataSource);
    }

    @Override
    public void removeDataSources(List<DataSourceApi> dataSourcesForRemoval) {
        dataSources.removeAll(dataSourcesForRemoval);
    }

    @Override
    public Optional<DataSourceApi> getDataSource(final String dataSourceName) {
        return dataSources.stream()
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
