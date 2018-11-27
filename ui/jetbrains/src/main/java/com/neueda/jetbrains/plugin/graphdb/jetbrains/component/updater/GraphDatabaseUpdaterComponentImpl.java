package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.updater;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.Notifier;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.PluginUtil;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphBundle;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants;
import org.jetbrains.annotations.NotNull;

public class GraphDatabaseUpdaterComponentImpl implements GraphDatabaseUpdaterComponent {

    private boolean isUpdated;
    private boolean isUpdateNotificationShown;

    @NotNull
    @Override
    public String getComponentName() {
        return "GraphDatabaseSupport.Updater";
    }

    @Override
    public void initComponent() {
        String currentVersion = PluginUtil.getVersion();
        String knownVersion = SettingsComponent.getInstance().getKnownPluginVersion();

        isUpdated = !currentVersion.equals(knownVersion);
        isUpdateNotificationShown = false;
        if (isUpdated || GraphConstants.IS_DEVELOPMENT) {
            SettingsComponent.getInstance().setKnownPluginVersion(currentVersion);

            ProjectManager.getInstance().addProjectManagerListener(new ProjectManagerListener() {
                @Override
                public void projectOpened(Project project) {
                    if (!isUpdateNotificationShown) {
                        Notifier.info(
                                GraphBundle.message("updater.title", currentVersion),
                                GraphBundle.message("updater.notification"));
                        isUpdateNotificationShown = true;
                    }
                }
            });
        }
    }

    @Override
    public void disposeComponent() {
    }
}
