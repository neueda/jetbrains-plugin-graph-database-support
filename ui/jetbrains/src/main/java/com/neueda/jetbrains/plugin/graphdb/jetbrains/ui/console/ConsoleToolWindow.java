package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants;
import org.jetbrains.annotations.NotNull;

public class ConsoleToolWindow implements ToolWindowFactory {

    public static void ensureOpen(Project project) {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = toolWindowManager.getToolWindow(GraphConstants.ToolWindow.CONSOLE_TOOL_WINDOW);

        if (!toolWindow.isActive()) {
            toolWindow.activate(null, false);
            return;
        }

        if (!toolWindow.isVisible()) {
            toolWindow.show(null);
        }
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ServiceManager.getService(project, GraphConsoleView.class).initToolWindow(project, toolWindow);
    }
}
