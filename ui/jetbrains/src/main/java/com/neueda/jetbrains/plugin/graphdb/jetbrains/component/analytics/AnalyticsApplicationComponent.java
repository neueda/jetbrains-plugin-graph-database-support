package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;

public interface AnalyticsApplicationComponent extends ApplicationComponent {

    static AnalyticsApplicationComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(AnalyticsApplicationComponent.class);
    }

    boolean isAnalyticEnabled();

    void enableAnalytics(boolean state);

    void event(String component, String action);
}
