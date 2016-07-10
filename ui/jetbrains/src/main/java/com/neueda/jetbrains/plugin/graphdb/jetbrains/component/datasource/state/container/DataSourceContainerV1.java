package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.container;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceContainer;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSourceV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class DataSourceContainerV1 implements DataSourceContainer {

    public List<DataSourceV1> dataSources = new ArrayList<>();

    @Override
    public List getGenericDataSources() {
        return dataSources;
    }

    @Override
    public DataSourceApi findDataSource(String uuid) {
        Optional<DataSourceV1> foundDataSource = dataSources.stream()
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
        dataSources.add((DataSourceV1) dataSource);
    }

    @Override
    public void updateDataSource(DataSourceApi oldDataSource, DataSourceApi newDataSource) {
        int index = dataSources.indexOf(oldDataSource);
        dataSources.set(index, (DataSourceV1) newDataSource);
    }

    @Override
    public void removeDataSources(List<DataSourceApi> dataSourcesForRemoval) {
        dataSources.removeAll(dataSourcesForRemoval);
    }

    public boolean isDataSourceExists(String dataSourceName) {
        Optional<DataSourceV1> possibleDataSource = dataSources.stream()
                .filter((dataSource) -> dataSource.getName().equals(dataSourceName))
                .findAny();
        return possibleDataSource.isPresent();
    }

    @Override
    public DataSourceApi createDataSource(DataSourceApi dataSourceToEdit, DataSourceType dataSourceType,
                                          String dataSourceName, Map<String, String> configuration) {
        String uuid = dataSourceToEdit == null ? UUID.randomUUID().toString() : dataSourceToEdit.getUUID();
        return new DataSourceV1(uuid, dataSourceName, dataSourceType, configuration);
    }
}
