package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import com.google.common.base.Throwables;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.icons.AllIcons;
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
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.wm.ex.WindowManagerEx;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.FileUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.FileTypeExtensionUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent.*;

public class ParametersPanel implements ParametersProvider {

    private static final FileDocumentManager FILE_DOCUMENT_MANAGER = FileDocumentManager.getInstance();
    public static final Icon ICON_HELP = AllIcons.Actions.Help;

    private Editor globalParamEditor;
    private Editor fileSpecificParamEditor;
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
        if (selectedEditor != null && selectedEditor.getFile() != null &&
                FileTypeExtensionUtil.isCypherFileTypeExtension(selectedEditor.getFile().getExtension())) {
            setupFileSpecificEditor(project, selectedEditor.getFile());
        }
    }

    public String getGlobalParametersJson() {
        return globalParamEditor.getDocument().getText();
    }

    public String getFileSpecificParametersJson() {
        return fileSpecificParamEditor != null ? fileSpecificParamEditor.getDocument().getText() : null;
    }

    private void initializeUi() {
        graphConsoleView.getGlobalParametersTab().add(globalParamEditor.getComponent(), BorderLayout.CENTER);
        service.registerParametersProvider(this);
        MessageBusConnection mbConnection = messageBus.connect();
        mbConnection.subscribe(QueryParametersRetrievalErrorEvent.QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC,
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

        mbConnection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            // If file opened, fileOpenedSync->selectionChanged->fileOpened are called
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                releaseFileSpecificEditor(event.getOldFile());
                VirtualFile newFile = event.getNewFile();
                if (newFile != null && FileTypeExtensionUtil.isCypherFileTypeExtension(newFile.getExtension()) &&
                        project.getComponent(DataSourcesComponent.class).getDataSourceContainer().isDataSourceExists(getTabTitle(newFile))) {
                    setupFileSpecificEditor(project, newFile);
                }
            }
        });
    }

    private void setupEditor(Project project) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                VirtualFile file = FileUtil.getScratchFile(project, "Neo4jGraphDbConsoleParametersPanel.json");
                Document document = FILE_DOCUMENT_MANAGER.getDocument(file);
                globalParamEditor = createEditor(project, document);
                JLabel jLabel = new JLabel("<html>Global parameters:</html>");
                jLabel.setIcon(ICON_HELP);
                jLabel.setToolTipText("Enter parameters in JSON format. Will be applied to any data source when executed");
                globalParamEditor.setHeaderComponent(jLabel);
                setInitialContent(document);

                initializeUi();
            } catch (Throwable e) {
                Throwables.throwIfUnchecked(e);
                throw new RuntimeException(e);
            }
        });
    }

    private void setupFileSpecificEditor(Project project, VirtualFile cypherFile) {
        if (project == null || cypherFile == null) {
            return;
        }
        try {
            String params = FileUtil.getParams(cypherFile);
            LightVirtualFile lightVirtualFile = new LightVirtualFile("", JsonFileType.INSTANCE, params);
            Document document = FileDocumentManager.getInstance().getDocument(lightVirtualFile);
            fileSpecificParamEditor = createEditor(project, document);
            VirtualFileManager.getInstance().addVirtualFileListener(new VirtualFileListener() {
                @Override
                public void contentsChanged(@NotNull VirtualFileEvent event) {
                    if (event.getFile().equals(cypherFile) && document != null) {
                        FileUtil.setParams(cypherFile, document.getText());
                    }
                }
            });
            JLabel jLabel = new JLabel("<html>Parameters for data source <b>" +
                    getTabTitle(cypherFile) + "</b>:</html>");
            jLabel.setIcon(ICON_HELP);
            jLabel.setToolTipText("Enter parameters in JSON format. Will be applied to <b>" + getTabTitle(cypherFile) +
                    "</b> data source when executed");
            fileSpecificParamEditor.setHeaderComponent(jLabel);
            if (document != null) {
                setInitialContent(document);
            }
            graphConsoleView.getFileSpecificParametersTab().add(fileSpecificParamEditor.getComponent(), BorderLayout.CENTER);
        } catch (Throwable e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }

    private void releaseFileSpecificEditor(VirtualFile oldFile) {
        if (fileSpecificParamEditor != null) {
            // set params in fileSpecificParamDocument.addDocumentListener() listener is a more universal but worse solution:
            // gets triggered immediately, debounce required
            FileUtil.setParams(oldFile, fileSpecificParamEditor.getDocument().getText());
            graphConsoleView.getFileSpecificParametersTab().remove(fileSpecificParamEditor.getComponent());
            if (!fileSpecificParamEditor.isDisposed()) {
                EditorFactory.getInstance().releaseEditor(fileSpecificParamEditor);
            }
        }
        fileSpecificParamEditor = null;
    }

    private String getTabTitle(VirtualFile file) {
        Window window = WindowManagerEx.getInstanceEx().getMostRecentFocusedWindow();
        EditorWindow editorWindow = FileEditorManagerEx.getInstanceEx(project).getSplittersFor(window).getCurrentWindow();
        return EditorTabPresentationUtil.getEditorTabTitle(project, file, editorWindow);
    }

    private void setInitialContent(Document document) {
        if (document != null && document.getText().isEmpty()) {
            final Runnable setTextRunner = () -> document.setText("{}");
            ApplicationManager.getApplication()
                    .invokeLater(() -> ApplicationManager.getApplication().runWriteAction(setTextRunner));
        }
    }

    private static Editor createEditor(Project project, Document document) {
        return EditorFactory.getInstance().createEditor(document, project, JsonFileType.INSTANCE, false);
    }

}
