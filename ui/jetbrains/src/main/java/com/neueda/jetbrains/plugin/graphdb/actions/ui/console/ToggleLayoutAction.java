package com.neueda.jetbrains.plugin.graphdb.actions.ui.console;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;

public class ToggleLayoutAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);

        if (project == null) {
            return;
        }

        MessageBus messageBus = project.getMessageBus();
        messageBus.syncPublisher(ToggleLayoutEvent.TOGGLE_LAYOUT_TOPIC).toggleLayout();
    }
}
