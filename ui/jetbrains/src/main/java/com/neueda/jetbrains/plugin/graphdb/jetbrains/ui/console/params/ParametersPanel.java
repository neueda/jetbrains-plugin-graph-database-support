package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import com.google.common.base.Throwables;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.json.JsonFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.FileUtil;

import javax.swing.*;
import java.awt.*;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent.*;

public class ParametersPanel implements ParametersProvider {

    private static final FileDocumentManager FILE_DOCUMENT_MANAGER = FileDocumentManager.getInstance();

    private Editor editor;
    private GraphConsoleView graphConsoleView;
    private MessageBus messageBus;
    private ParametersService service;

    public void initialize(GraphConsoleView graphConsoleView, Project project) {
        this.graphConsoleView = graphConsoleView;
        this.messageBus = project.getMessageBus();
        this.service = ServiceManager.getService(project, ParametersService.class);
        setupEditor(project);
    }

    public String getParametersJson() {
        return editor.getDocument().getText();
    }

    private void initializeUi() {
        graphConsoleView.getParametersTab().add(editor.getComponent(), BorderLayout.CENTER);
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
