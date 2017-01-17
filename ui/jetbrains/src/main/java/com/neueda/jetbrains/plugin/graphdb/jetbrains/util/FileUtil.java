package com.neueda.jetbrains.plugin.graphdb.jetbrains.util;

import com.intellij.ide.scratch.ScratchFileService;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.GraphDbEditorsConsoleRootType;

import java.io.IOException;

public class FileUtil {

    public static VirtualFile getDataSourceFile(Project project, DataSourceApi dataSource) throws IOException {
        return ScratchFileService.getInstance().findFile(
                GraphDbEditorsConsoleRootType.getInstance(),
                NameUtil.createDataSourceFileName(dataSource),
                ScratchFileService.Option.create_if_missing
        );
    }

    public static VirtualFile getScratchFile(Project project, String fileName) throws IOException {
        return ScratchFileService.getInstance().findFile(
                GraphDbEditorsConsoleRootType.getInstance(),
                fileName + project.getName(),
                ScratchFileService.Option.create_if_missing
        );
    }

    public static FileEditor[] openFile(Project project, VirtualFile file) {
        return FileEditorManager.getInstance(project).openFile(file, true);
    }

    public static void closeFile(Project project, VirtualFile file) {
        FileEditorManager.getInstance(project).closeFile(file);
    }
}
