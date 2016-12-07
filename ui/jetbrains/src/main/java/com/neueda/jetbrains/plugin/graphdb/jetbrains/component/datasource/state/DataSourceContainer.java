package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state;

import java.util.*;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;

public interface DataSourceContainer {

    List getGenericDataSources();

    @SuppressWarnings("unchecked")
    default List<DataSourceApi> getDataSources() {
        return (List<DataSourceApi>) getGenericDataSources();
    }

    Optional<DataSourceApi> findDataSource(String uuid);

    void addDataSource(DataSourceApi dataSource);

    void updateDataSource(DataSourceApi oldDataSource, DataSourceApi newDataSource);

    void removeDataSources(List<DataSourceApi> dataSourcesForRemoval);

    Optional<DataSourceApi> getDataSource(String dataSourceName);

    boolean isDataSourceExists(String dataSourceName);

    DataSourceApi createDataSource(DataSourceApi dataSourceToEdit, DataSourceType dataSourceType,
                                   String dataSourceName, Map<String, String> configuration);
}
