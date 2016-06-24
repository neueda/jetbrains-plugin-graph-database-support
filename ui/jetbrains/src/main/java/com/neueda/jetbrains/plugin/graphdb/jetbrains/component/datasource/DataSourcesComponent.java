package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@State(name = "GraphDatabaseSupport.DataSourcesState",
        storages = {@Storage("GraphDatabaseSupport_DataSourcesState.xml")})
public class DataSourcesComponent implements ProjectComponent, PersistentStateComponent<DataSourcesComponentState> {

    private DataSourcesComponentState state;

    public boolean isDataSourceExists(String dataSourceName) {
        Optional<DataSource> possibleDataSource = state.dataSources.stream()
                .filter((dataSource) -> dataSource.getName().equals(dataSourceName))
                .findAny();
        return possibleDataSource.isPresent();
    }

    public List<DataSource> getDataSources() {
        return state.dataSources;
    }

    public void addDataSource(DataSource dataSource) {
        state.dataSources.add(dataSource);
    }

    public void updateDataSource(DataSource oldDataSource, DataSource newDataSource) {
        int index = state.dataSources.indexOf(oldDataSource);
        state.dataSources.set(index, newDataSource);
    }

    public void removeDataSources(List<DataSource> dataSourcesForRemoval) {
        state.dataSources.removeAll(dataSourcesForRemoval);
    }

    public DataSourceDescription getDataSourceDescription(DataSource dataSource) {
        switch (dataSource.getDataSourceType()) {
            case NEO4J_BOLT:
                return DataSourceDescription.NEO4J_BOLT;
            default:
                throw new IllegalStateException("Unknown data source type encountered: " + dataSource.getDataSourceType());
        }
    }

    /**
     * Initialization.
     */
    @Override
    public void initComponent() {
    }

    /**
     * Load persisted state.
     */
    @Override
    public void loadState(DataSourcesComponentState state) {
        this.state = state;
    }

    /**
     * Project is opened.
     */
    @Override
    public void projectOpened() {
        if (state == null) {
            state = new DataSourcesComponentState();
        }
    }

    /**
     * Get state for persisting.
     */
    @Nullable
    @Override
    public DataSourcesComponentState getState() {
        return state;
    }

    /**
     * Project is closing.
     */
    @Override
    public void projectClosed() {
    }

    /**
     * Clean up component.
     */
    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "GraphDatabaseSupport.DataSources";
    }
}
