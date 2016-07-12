package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.updater;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerAdapter;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.Notifier;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.PluginUtil;
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
        if (isUpdated) {
            SettingsComponent.getInstance().setKnownPluginVersion(currentVersion);

            ProjectManager.getInstance().addProjectManagerListener(new ProjectManagerAdapter() {
                @Override
                public void projectOpened(Project project) {
                    if (!isUpdateNotificationShown) {
                        Notifier.info("Graph Database support plugin updated", "awesome update information");
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
