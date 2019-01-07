package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import com.google.common.base.Throwables;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.json.JsonFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorTabPresentationUtil;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ex.WindowManagerEx;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.FileUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.FileTypeExtensionUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent.PARAMS_ERROR_COMMON_MSG;

public class ParametersPanel implements ParametersProvider {

    private static final FileDocumentManager FILE_DOCUMENT_MANAGER = FileDocumentManager.getInstance();

    private Editor editor, localParamsEditor;
    private GraphConsoleView graphConsoleView;
    private MessageBus messageBus;
    private ParametersService service;
    private Project project;

    public void initialize(GraphConsoleView graphConsoleView, Project project) {
        this.graphConsoleView = graphConsoleView;
        this.messageBus = project.getMessageBus();
        this.service = ServiceManager.getService(project, ParametersService.class);
        this.project = project;
        setupEditor(project);
        FileEditor selectedEditor = FileEditorManager.getInstance(project).getSelectedEditor();
        if (selectedEditor != null) {
            setupLocalParamEditor(project, selectedEditor.getFile());
        }
    }

    public String getParametersJson() {
        return editor.getDocument().getText();
    }

    public String getLocalParametersJson() {
        return localParamsEditor != null? localParamsEditor.getDocument().getText() : null;
    }

    private void initializeUi() {
        graphConsoleView.getGlobalParametersTab().add(editor.getComponent(), BorderLayout.CENTER);
        service.registerParametersProvider(this);

        messageBus.connect().subscribe(QueryParametersRetrievalErrorEvent.QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC,
                (exception, editor) -> {
                    if (editor == null) {
                        return;
                    }
                    String errorMessage;
                    if (exception.getMessage() != null) {
                        errorMessage = String.format("%s: %s", PARAMS_ERROR_COMMON_MSG, exception.getMessage());
                    } else {
                        errorMessage = PARAMS_ERROR_COMMON_MSG;
                    }
                    HintManager.getInstance().showErrorHint(editor, errorMessage);
                });

        messageBus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            // If file opened, fileOpenedSync->selectionChanged->fileOpened are called
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                releaseLocalEditor();
                VirtualFile newFile = event.getNewFile();
                if (newFile != null && FileTypeExtensionUtil.isCypherFileTypeExtension(newFile.getExtension())) {
                    setupLocalParamEditor(project, newFile);
                }
            }
        });
    }

    private void setupEditor(Project project) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                VirtualFile file = FileUtil.getScratchFile(project, "Neo4jGraphDbConsoleParametersPanel.json");
                Document document = FILE_DOCUMENT_MANAGER.getDocument(file);
                editor = createEditor(project, document);
                editor.setHeaderComponent(new JLabel("Provide query parameters in JSON format here:"));
                setInitialContent(document);

                initializeUi();
            } catch (Throwable e) {
                Throwables.throwIfUnchecked(e);
                throw new RuntimeException(e);
            }
        });
    }

    private void releaseLocalEditor() {
        if (localParamsEditor != null) {
            graphConsoleView.getLocalParametersTab().remove(localParamsEditor.getComponent());
            if (!localParamsEditor.isDisposed()) {
                EditorFactory.getInstance().releaseEditor(localParamsEditor);
            }
        }
        localParamsEditor = null;
    }

    private void setupLocalParamEditor(Project project, VirtualFile file) {
        if (project == null || file == null) return;
        // TODO: create temp file for each cql file, handle changes/file closing
//        ApplicationManager.getApplication().runWriteAction(() -> {                // TODO: remove runWriteAction?
            // https://intellij-support.jetbrains.com/hc/en-us/community/posts/115000129030-ApplicationManager-getApplication-runWriteAction-vs-WriteCommandAction-runWriteCommandAction-
            try {
                VirtualFile localParamFile = FileUtil.getScratchFile(project, file.getPresentableName() + ".json");
                Document localParamDocument = FILE_DOCUMENT_MANAGER.getDocument(localParamFile);
                localParamsEditor = createEditor(project, localParamDocument);
                Window window = WindowManagerEx.getInstanceEx().getMostRecentFocusedWindow();
                EditorWindow editorWindow = FileEditorManagerEx.getInstanceEx(project).getSplittersFor(window).getCurrentWindow();
                String tabTitle = EditorTabPresentationUtil.getEditorTabTitle(project, file, editorWindow);
                localParamsEditor.setHeaderComponent(new JLabel("<html>Provide query parameters specific to <b>" + tabTitle + "</b> in JSON format here:</html>"));
                setInitialContent(localParamDocument);
                graphConsoleView.getLocalParametersTab().add(localParamsEditor.getComponent(), BorderLayout.CENTER);
            } catch (Throwable e) {
                Throwables.throwIfUnchecked(e);
                throw new RuntimeException(e);
            }
//        });
    }

    private void setInitialContent(Document document) {
        if (document.getText().isEmpty()) {
            final Runnable setTextRunner = () -> document.setText("{}");
            ApplicationManager.getApplication()
                    .invokeLater(() -> ApplicationManager.getApplication().runWriteAction(setTextRunner));
        }
    }

    private static Editor createEditor(Project project, Document document) {
        return EditorFactory.getInstance().createEditor(document, project, new JsonFileType(), false);
    }

}
