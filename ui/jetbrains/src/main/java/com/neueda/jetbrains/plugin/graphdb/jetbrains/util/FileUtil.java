package com.neueda.jetbrains.plugin.graphdb.jetbrains.util;

import com.intellij.ide.scratch.ScratchFileService;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.FileAttribute;
import com.intellij.openapi.vfs.newvfs.NewVirtualFile;
import com.intellij.util.io.IOUtil;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params.ParameterRootType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.GraphDbEditorsConsoleRootType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FileUtil {

    private static final Key<String> MY_KEY = Key.create("file specific params");
    private static final FileAttribute QUERY_PARAMS_FILE_ATTRIBUTE = new FileAttribute("queryParamsFileAttribute");

    public static VirtualFile getDataSourceFile(Project project, DataSourceApi dataSource) throws IOException {
        return ScratchFileService.getInstance().findFile(
                GraphDbEditorsConsoleRootType.getInstance(),
                NameUtil.createDataSourceFileName(dataSource),
                ScratchFileService.Option.create_if_missing
        );
    }

    public static VirtualFile getScratchFile(Project project, String fileName) throws IOException {
        return ScratchFileService.getInstance().findFile(
                ParameterRootType.getInstance(),
                project.getName() + fileName,
                ScratchFileService.Option.create_if_missing
        );
    }

    public static String getParams(VirtualFile file) {
        String userData = file.getUserData(MY_KEY);
        if (userData != null) {
            return userData;
        }

        if (!(file instanceof NewVirtualFile)) {
            return "{}";
        }

        try (DataInputStream is = QUERY_PARAMS_FILE_ATTRIBUTE.readAttribute(file)) {
            if (is == null || is.available() <= 0) {
                return "{}";
            }

            String attributeData = IOUtil.readString(is);
            if (attributeData == null) {
                return "{}";
            } else {
                file.putUserData(MY_KEY, attributeData);
                return attributeData;
            }
        } catch (IOException e) {
            return "{}";
        }
    }

    public static void setParams(VirtualFile file, String params) {
        file.putUserData(MY_KEY, params);
        if (file instanceof NewVirtualFile) {
            try (DataOutputStream os = QUERY_PARAMS_FILE_ATTRIBUTE.writeAttribute(file)) {
                IOUtil.writeString(StringUtil.notNullize(params), os);
            } catch (IOException e) {
            }
        }
    }

    public static FileEditor[] openFile(Project project, VirtualFile file) {
        return FileEditorManager.getInstance(project).openFile(file, true);
    }

    public static void closeFile(Project project, VirtualFile file) {
        FileEditorManager.getInstance(project).closeFile(file);
    }
}
