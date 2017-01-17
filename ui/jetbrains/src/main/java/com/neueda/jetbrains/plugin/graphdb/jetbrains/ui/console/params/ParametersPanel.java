package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import com.fasterxml.jackson.core.JsonParseException;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.json.JsonFileType;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.EditorTextField;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.FileUtil;

import java.awt.*;
import java.io.IOException;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent.*;

public class ParametersPanel implements ParametersProvider {

    private static final FileDocumentManager FILE_DOCUMENT_MANAGER = FileDocumentManager.getInstance();

    private EditorTextField editor;

    public void initialize(GraphConsoleView graphConsoleView, Project project) {
        MessageBus messageBus = project.getMessageBus();

        editor = getJsonEditorFromScratchFile(project);

        graphConsoleView.getParametersTab().add(editor, BorderLayout.CENTER);

        ParametersService service = ServiceManager.getService(project, ParametersService.class);
        service.registerParametersProvider(this);

        messageBus.connect().subscribe(QueryParametersRetrievalErrorEvent.QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC,
                (exception, editor) -> {
                    String errorMessage;
                    if (exception instanceof JsonParseException && exception.getMessage() != null) {
                        errorMessage = String.format("%s: %s", PARAMS_ERROR_COMMON_MSG, exception.getMessage());
                    } else {
                        errorMessage = PARAMS_ERROR_COMMON_MSG;
                    }
                    HintManager.getInstance().showErrorHint(editor, errorMessage);
                });
    }

    private static EditorTextField getJsonEditorFromScratchFile(Project project) {
        VirtualFile file;
        try {
            file = FileUtil.getScratchFile(project, "Neo4jGraphDbConsoleParametersPanel");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Document document = FILE_DOCUMENT_MANAGER.getDocument(file);
        return new EditorTextField(document, project, new JsonFileType(), false, false);
    }

    public String getParametersJson() {
        return editor.getText();
    }

}
