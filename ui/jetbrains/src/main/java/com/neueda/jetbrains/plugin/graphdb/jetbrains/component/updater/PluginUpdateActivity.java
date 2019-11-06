package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.updater;

import com.intellij.notification.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.Analytics;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.PluginUtil;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphBundle;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkEvent;

public class PluginUpdateActivity implements StartupActivity, DumbAware {

    private static final String NOTIFICATION_ID = "GraphDatabaseSupportUpdateNotification";
    private boolean isUpdateNotificationShown = false;

    @Override
    public void runActivity(@NotNull Project project) {
        String currentVersion = PluginUtil.getVersion();
        String knownVersion = SettingsComponent.getInstance().getKnownPluginVersion();

        boolean isUpdated = !currentVersion.equals(knownVersion);
        if (isUpdated || GraphConstants.IS_DEVELOPMENT) {
            if (!isUpdateNotificationShown) {
                SettingsComponent.getInstance().setKnownPluginVersion(currentVersion);
                showNotification(project, currentVersion);
                isUpdateNotificationShown = true;
            }
        }
    }

    private void showNotification(Project project, String currentVersion) {
        NotificationGroup group = new NotificationGroup(NOTIFICATION_ID, NotificationDisplayType.STICKY_BALLOON, true);
        Notification notification = group.createNotification(
                GraphBundle.message("updater.title", currentVersion),
                GraphBundle.message("updater.notification"),
                NotificationType.INFORMATION,
                new UrlOpeningListenerWithAnalytics(false)
        );
        Notifications.Bus.notify(notification, project);
    }

    static class UrlOpeningListenerWithAnalytics extends NotificationListener.UrlOpeningListener {

        UrlOpeningListenerWithAnalytics(boolean expireNotification) {
            super(expireNotification);
        }

        @Override
        protected void hyperlinkActivated(@NotNull Notification notification, @NotNull HyperlinkEvent event) {
            Analytics.event("landingPage", "landFromNotification");
            super.hyperlinkActivated(notification, event);
        }
    }

}
