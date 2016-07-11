package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics;

import com.brsanthu.googleanalytics.EventHit;
import com.brsanthu.googleanalytics.GoogleAnalytics;
import com.brsanthu.googleanalytics.GoogleAnalyticsRequest;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationAdapter;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.PluginId;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.GraphConstants;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.listener.AnalyticsListener;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GoogleAnalyticsApplicationComponent implements AnalyticsApplicationComponent {

    private static final String TRACKING_ID = "UA-67609275-4";
    private static final String ANALYTICS_ENABLED_KEY = "GraphDbSupport.AnalyticsEnabled";
    private static final String ANALYTICS_CLIENT_ID_KEY = "GraphDbSupport.AnalyticsClientId";

    private ApplicationInfo applicationInfo;
    private IdeaPluginDescriptor graphDbSupportPlugin;
    private GoogleAnalytics ga;

    @NotNull
    @Override
    public String getComponentName() {
        return "GraphDatabaseSupport.GoogleAnalytics";
    }

    @Override
    public void initComponent() {
        applicationInfo = ApplicationInfo.getInstance();
        graphDbSupportPlugin = PluginManager.getPlugin(PluginId.getId(GraphConstants.PLUGIN_ID));
        AnalyticsListener.init();

        ga = new CustomGoogleAnalytics(TRACKING_ID);
        ga.getConfig().setValidate(GraphConstants.IS_DEVELOPMENT);

        sessionStart();
        ApplicationManager.getApplication().addApplicationListener(new ApplicationAdapter() {
            @Override
            public void applicationExiting() {
                sessionEnd();
            }
        });
    }

    @Override
    public void disposeComponent() {
        ga.close();
    }

    @Override
    public boolean isAnalyticEnabled() {
        if (!PropertiesComponent.getInstance().isValueSet(ANALYTICS_ENABLED_KEY)) {
            PropertiesComponent.getInstance().setValue(ANALYTICS_ENABLED_KEY, "true");
        }
        return "true".equals(PropertiesComponent.getInstance().getValue(ANALYTICS_ENABLED_KEY));
    }

    @Override
    public void enableAnalytics(boolean state) {
        forceEvent("analytics", state ? "enableAnalytics" : "disableAnalytics");
        PropertiesComponent.getInstance().setValue(ANALYTICS_ENABLED_KEY, String.valueOf(state));
    }

    public String getClientId() {
        if (!PropertiesComponent.getInstance().isValueSet(ANALYTICS_CLIENT_ID_KEY)) {
            PropertiesComponent.getInstance().setValue(ANALYTICS_CLIENT_ID_KEY, UUID.randomUUID().toString());
        }

        return PropertiesComponent.getInstance().getValue(ANALYTICS_CLIENT_ID_KEY);
    }

    @Override
    public void event(String component, String action) {
        EventHit request = new EventHit(component, action);
        enrich(request);
        post(request);
    }

    @Override
    public void forceEvent(String component, String action) {
        EventHit request = new EventHit(component, action);
        enrich(request);
        forcePost(request);
    }

    private void sessionStart() {
        EventHit request = new EventHit("analytics", "applicationStarted");
        request.sessionControl("start");
        enrich(request);
        post(request);
    }

    private void sessionEnd() {
        EventHit request = new EventHit("analytics", "applicationClosed");
        request.sessionControl("end");
        enrich(request);
        post(request);
    }

    private void enrich(GoogleAnalyticsRequest request) {
        request.clientId(getClientId());
        request.dataSource("plugin");

        request.customDimension(1, applicationInfo.getBuild().getProductCode()); // jetbrainsProductCode
        request.customDimension(2, applicationInfo.getBuild().asStringWithoutProductCode()); // jetbrainsBuildNumber
        request.customDimension(3, applicationInfo.getFullVersion()); // jetbrainsProductVersion
        request.customDimension(4, String.valueOf(graphDbSupportPlugin.isEnabled())); // pluginEnabled
        request.customDimension(5, graphDbSupportPlugin.getVersion()); // pluginVersion
        request.customDimension(6, String.valueOf(GraphConstants.IS_DEVELOPMENT)); // pluginDevelopmentMode
    }

    private void post(GoogleAnalyticsRequest request) {
        if (isAnalyticEnabled()) {
            ga.postAsync(request);
        }
    }

    private void forcePost(GoogleAnalyticsRequest request) {
        ga.postAsync(request);
    }
}
