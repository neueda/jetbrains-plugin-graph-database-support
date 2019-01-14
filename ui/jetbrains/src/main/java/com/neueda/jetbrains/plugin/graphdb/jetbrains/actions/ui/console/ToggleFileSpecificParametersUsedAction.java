package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.ui.console;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import org.jetbrains.annotations.NotNull;

public class ToggleFileSpecificParametersUsedAction extends AnAction {

    public ToggleFileSpecificParametersUsedAction() {

    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        SettingsComponent settings = SettingsComponent.getInstance();
        boolean areFileSpecificParamsUsed = settings.areFileSpecificParamsUsed();
        settings.enableFileSpecificParams(!areFileSpecificParamsUsed);
        syncIconAndText(!areFileSpecificParamsUsed, e.getPresentation());

        if (e.getProject() != null) {
            ToggleFileSpecificParametersEvent publisher = e.getProject().getMessageBus().syncPublisher(
                    ToggleFileSpecificParametersEvent.TOGGLE_FILE_SPECIFIC_PARAMETERS_EVENT_TOPIC
            );
            publisher.toggle(!areFileSpecificParamsUsed);
        }
    }

    public void syncIconAndText(boolean fileSpecificParams, Presentation presentation) {
        if (fileSpecificParams) {
            presentation.setIcon(AllIcons.Actions.EditSource);
            presentation.setText("Using file-specific parameters");
        } else {
            presentation.setIcon(AllIcons.Actions.Edit);
            presentation.setText("Using global parameters");
        }
    }
}
