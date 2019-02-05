package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.ui.console;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.CopyQueryOutputEvent;
import org.jetbrains.annotations.NotNull;

public class CopyQueryOutputAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = getEventProject(e);

        if (project == null) {
            return;
        }

        MessageBus messageBus = project.getMessageBus();
        messageBus.syncPublisher(CopyQueryOutputEvent.COPY_QUERY_OUTPUT_TOPIC).copyQueryOutputToClipboard();
    }
}
