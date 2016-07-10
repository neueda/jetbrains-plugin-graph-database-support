package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state;

import com.intellij.util.xmlb.annotations.Transient;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.container.DataSourceContainerV1;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSource;

import java.util.ArrayList;
import java.util.List;

public class DataSourcesComponentState {

    public List<DataSource> dataSources = new ArrayList<>();

    public DataSourceContainerV1 containerV1 = new DataSourceContainerV1();

    @Transient
    private boolean migrated = false;

    public DataSourceContainer getCurrentContainer() {
        return containerV1;
    }

    public void migrate() {
        if (!migrated) {
            try {
                initialToV1();
            } finally {
                migrated = true;
            }
        }
    }

    private void initialToV1() {
        if (dataSources.size() > 0) {
            dataSources.forEach((dataSource -> containerV1.addDataSource(
                    containerV1.createDataSource(
                            null, dataSource.getDataSourceType(),
                            dataSource.getName(), dataSource.getConfiguration()
                    )
            )));
            dataSources.clear();
        }
    }
}
