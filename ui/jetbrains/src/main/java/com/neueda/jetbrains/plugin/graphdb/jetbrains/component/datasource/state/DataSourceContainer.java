package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;

import java.util.List;
import java.util.Map;

public interface DataSourceContainer {

    List getGenericDataSources();

    @SuppressWarnings("unchecked")
    default List<DataSourceApi> getDataSources() {
        return (List<DataSourceApi>) getGenericDataSources();
    }

    DataSourceApi findDataSource(String uuid);

    void addDataSource(DataSourceApi dataSource);

    void updateDataSource(DataSourceApi oldDataSource, DataSourceApi newDataSource);

    void removeDataSources(List<DataSourceApi> dataSourcesForRemoval);

    boolean isDataSourceExists(String dataSourceName);

    DataSourceApi createDataSource(DataSourceApi dataSourceToEdit, DataSourceType dataSourceType,
                                   String dataSourceName, Map<String, String> configuration);
}
