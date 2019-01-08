package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.ui.console;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import org.jetbrains.annotations.NotNull;

public class ToggleFileSpecificParametersUsedAction extends AnAction {

    public ToggleFileSpecificParametersUsedAction() {

    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        SettingsComponent settings = SettingsComponent.getInstance();
        boolean setToUseFileSpecificParams;
        if (settings.areFileSpecificParamsUsed()) {
            settings.enableFileSpecificParams(false);
            e.getPresentation().setIcon(AllIcons.Actions.Edit);
            e.getPresentation().setText("Using global parameters");
            setToUseFileSpecificParams = false;
        } else {
            settings.enableFileSpecificParams(true);
            e.getPresentation().setIcon(AllIcons.Actions.EditSource);
            e.getPresentation().setText("Using file-specific parameters");
            setToUseFileSpecificParams = true;
        }
        Project project = getEventProject(e);
        if (project != null) {
            ToggleFileSpecificParametersEvent publisher = project.getMessageBus().syncPublisher(
                    ToggleFileSpecificParametersEvent.TOGGLE_FILE_SPECIFIC_PARAMETERS_EVENT_TOPIC
            );
            publisher.toggle(setToUseFileSpecificParams);
        }
    }
}
