package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.listener;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsListener {

    private static final List<String> EXTENSIONS = new ArrayList<String>() {{
        add("cyp");
        add("cql");
        add("cypher");
    }};

    public static void init() {
        projectManagerListener();
    }

    private static void projectManagerListener() {
        ProjectManagerListener listener = new ProjectManagerListener() {
            @Override
            public void projectOpened(Project project) {
                project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
                    @Override
                    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                        if (EXTENSIONS.contains(file.getExtension())) {
                            Analytics.event("file[" + file.getExtension() + "]", "opened");
                        }
                    }

                    @Override
                    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                        if (EXTENSIONS.contains(file.getExtension())) {
                            Analytics.event("file[" + file.getExtension() + "]", "closed");
                        }
                    }
                });
            }

            @Override
            public void projectClosed(Project project) {
            }
        };
        ProjectManager.getInstance().addProjectManagerListener(listener);
    }
}
