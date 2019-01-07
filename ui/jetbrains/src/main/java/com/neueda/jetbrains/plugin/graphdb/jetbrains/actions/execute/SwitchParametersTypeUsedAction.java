package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import org.jetbrains.annotations.NotNull;

public class SwitchParametersTypeUsedAction extends AnAction {

    public SwitchParametersTypeUsedAction() {

    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        SettingsComponent settings = SettingsComponent.getInstance();
        if (settings.areFileSpecificParamsUsed()) {
            settings.enableFileSpecificParams(false);
            e.getPresentation().setIcon(AllIcons.Actions.Edit);
            e.getPresentation().setText("Using global parameters");
        } else {
            settings.enableFileSpecificParams(true);
            e.getPresentation().setIcon(AllIcons.Actions.EditSource);
            e.getPresentation().setText("Using file-specific parameters");
        }
    }
}
