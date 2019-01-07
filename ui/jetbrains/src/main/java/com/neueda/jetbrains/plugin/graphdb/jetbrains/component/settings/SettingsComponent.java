package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;

public interface SettingsComponent extends ApplicationComponent {

    static SettingsComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(SettingsComponent.class);
    }

    String getUserId();

    boolean isAnalyticEnabled();

    boolean areFileSpecificParamsUsed();

    void enableAnalytics(boolean state);

    void enableFileSpecificParams(boolean isFileSpecific);

    String getKnownPluginVersion();

    void setKnownPluginVersion(String version);
}
