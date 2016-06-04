package com.neueda.jetbrains.plugin.graphdb.component.datasource;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//@State(name = "GraphDatabaseSupport.DataSourcesState")
@State(name = "GraphDatabaseSupport.DataSourcesState",
        storages = {@Storage("GraphDatabaseSupport_DataSourcesState.xml")})
public class DataSourcesComponent implements ProjectComponent, PersistentStateComponent<DataSourcesComponent.State> {

    public static class State {
    }

    private State state;

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
    public void loadState(State state) {
        this.state = state;
    }

    /**
     * Project is opened.
     */
    @Override
    public void projectOpened() {
        if (state == null) {
            state = new State();
        }
    }

    /**
     * Get state for persisting.
     */
    @Nullable
    @Override
    public State getState() {
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
