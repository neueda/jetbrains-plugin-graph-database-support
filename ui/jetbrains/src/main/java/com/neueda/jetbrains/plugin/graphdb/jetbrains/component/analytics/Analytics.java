package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;

public final class Analytics {

    public static void event(String component, String action) {
        AnalyticsApplicationComponent.getInstance().event(component, action);
    }

    public static void event(DataSourceApi dataSourceApi, String action) {
        event("dataSource[" + dataSourceApi.getDataSourceType() + "]", action);
    }
}
