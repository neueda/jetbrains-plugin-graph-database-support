package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings;

import com.intellij.ide.util.PropertiesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SettingsComponentImpl implements SettingsComponent {

    private static final String ANALYTICS_ENABLED_KEY = "GraphDbSupport.AnalyticsEnabled";
    private static final String USE_FILE_SPECIFIC_PARAMS_KEY = "GraphDbSupport.UseFileSpecificParams";
    private static final String USER_ID = "GraphDbSupport.UserId";
    private static final String KNOWN_PLUGIN_VERSION = "GraphDbSupport.KnownPluginVersion";
    private static final String GRAPH_VIEW_INVERT_ZOOM = "GraphDbSupport.GraphViewInvertZoom";

    @NotNull
    @Override
    public String getComponentName() {
        return "GraphDatabaseSupport.Settings";
    }

    @Override
    public void initComponent() {
    }


    @Override
    public void disposeComponent() {
    }

    @Override
    public String getUserId() {
        if (!properties().isValueSet(USER_ID)) {
            properties().setValue(USER_ID, UUID.randomUUID().toString());
        }
        return properties().getValue(USER_ID);
    }

    @Override
    public boolean isAnalyticEnabled() {
        return properties().getBoolean(ANALYTICS_ENABLED_KEY, true);
    }

    @Override
    public void enableAnalytics(boolean state) {
        Analytics.forceEvent("settings", state ? "enableAnalytics" : "disableAnalytics");
        properties().setValue(ANALYTICS_ENABLED_KEY, state, true);
    }

    @Override
    public boolean isGraphViewZoomInverted() {
        return properties().getBoolean(GRAPH_VIEW_INVERT_ZOOM, true);
    }

    @Override
    public void invertGraphViewZoom(boolean state) {
        properties().setValue(GRAPH_VIEW_INVERT_ZOOM, state, true);
    }

    @Override
    public String getKnownPluginVersion() {
        return properties().getValue(KNOWN_PLUGIN_VERSION, "unknown");
    }

    @Override
    public void setKnownPluginVersion(String version) {
        properties().setValue(KNOWN_PLUGIN_VERSION, version);
    }

    private PropertiesComponent properties() {
        return PropertiesComponent.getInstance();
    }
}
