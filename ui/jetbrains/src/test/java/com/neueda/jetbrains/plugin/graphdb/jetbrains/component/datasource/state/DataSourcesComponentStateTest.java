package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSource;

public class DataSourcesComponentStateTest {

    private DataSourcesComponentState state;

    @Before
    public void setUp() throws Exception {
        state = new DataSourcesComponentState();
    }

    @Test
    public void testMigrateFromInitial() throws Exception {
        state.dataSources.add(new DataSource(DataSourceType.NEO4J_BOLT, "testName", Collections.emptyMap()));
        state.migrate();

        List<? extends DataSourceApi> dataSources = state.containerV1.getDataSources();
        assertThat(state.dataSources).hasSize(0);
        assertThat(dataSources).hasSize(1);
        DataSourceApi dataSource = dataSources.get(0);

        assertThat(dataSource.getUUID())
                   .isNotNull()
                   .hasSameSizeAs(UUID.randomUUID().toString());
        assertThat(dataSource.getName())
                   .isEqualTo("testName");
        assertThat(dataSource.getDataSourceType())
                   .isEqualTo(DataSourceType.NEO4J_BOLT);
    }

    @Test
    public void findDataSourceByUuidThrowsExceptionIfNotExists() throws Exception {
        Optional<? extends DataSourceApi> dataSource = state.getCurrentContainer().findDataSource("notexists");
        assertThat(dataSource).isNotPresent();
    }

    @Test
    public void findDataSourceByUuidReturnObjectIfExists() throws Exception {
        DataSourceApi dataSource = state.getCurrentContainer()
                   .createDataSource(null, DataSourceType.NEO4J_BOLT, "testName", Collections.emptyMap());

        state.getCurrentContainer().addDataSource(dataSource);
        DataSourceApi foundDataSource = state.getCurrentContainer().findDataSource(dataSource.getUUID()).get();

        assertThat(foundDataSource.getUUID()).isEqualTo(dataSource.getUUID());
        assertThat(foundDataSource.getName()).isEqualTo(dataSource.getName());
    }
}
