package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics;

import com.brsanthu.googleanalytics.EventHit;
import com.brsanthu.googleanalytics.GoogleAnalytics;
import com.brsanthu.googleanalytics.GoogleAnalyticsRequest;
import com.intellij.openapi.application.ApplicationAdapter;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.listener.AnalyticsListener;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.PluginUtil;
import org.jetbrains.annotations.NotNull;

public class GoogleAnalyticsApplicationComponent implements AnalyticsApplicationComponent {

    private static final String TRACKING_ID = "UA-67609275-4";

    private ApplicationInfo applicationInfo;
    private GoogleAnalytics ga;

    @NotNull
    @Override
    public String getComponentName() {
        return "GraphDatabaseSupport.GoogleAnalytics";
    }

    @Override
    public void initComponent() {
        applicationInfo = ApplicationInfo.getInstance();
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
        request.clientId(SettingsComponent.getInstance().getUserId());
        request.dataSource("plugin");

        request.customDimension(1, applicationInfo.getBuild().getProductCode()); // jetbrainsProductCode
        request.customDimension(2, applicationInfo.getBuild().asStringWithoutProductCode()); // jetbrainsBuildNumber
        request.customDimension(3, applicationInfo.getFullVersion()); // jetbrainsProductVersion
        request.customDimension(4, String.valueOf(PluginUtil.isEnabled())); // pluginEnabled
        request.customDimension(5, PluginUtil.getVersion()); // pluginVersion
        request.customDimension(6, String.valueOf(GraphConstants.IS_DEVELOPMENT)); // pluginDevelopmentMode
    }

    private void post(GoogleAnalyticsRequest request) {
        if (SettingsComponent.getInstance().isAnalyticEnabled()) {
            ga.postAsync(request);
        }
    }

    private void forcePost(GoogleAnalyticsRequest request) {
        ga.postAsync(request);
    }
}
