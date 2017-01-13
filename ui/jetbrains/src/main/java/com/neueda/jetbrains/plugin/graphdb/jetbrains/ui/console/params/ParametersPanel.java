package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.json.JsonFileType;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.GraphConsoleView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent;
import org.codehaus.jackson.JsonParseException;

import java.awt.*;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent.*;

public class ParametersPanel implements ParametersProvider {

    private EditorTextField editor;

    public void initialize(GraphConsoleView graphConsoleView, Project project) {
        MessageBus messageBus = project.getMessageBus();
        editor = new EditorTextField(EditorFactory.getInstance().createDocument("// Provide query parameters in JSON format here:"),
                project, new JsonFileType(), false, false);
        graphConsoleView.getParametersTab().add(editor, BorderLayout.CENTER);

        ParametersService service = ServiceManager.getService(project, ParametersService.class);
        service.registerParametersProvider(this);

        messageBus.connect().subscribe(QueryParametersRetrievalErrorEvent.QUERY_PARAMETERS_RETRIEVAL_ERROR_EVENT_TOPIC,
                (exception, editor) -> {
                    String errorMessage;
                    if (exception instanceof JsonParseException) {
                        if (exception.getMessage() == null) {
                            errorMessage = PARAMS_ERROR_COMMON_MSG;
                        } else {
                            errorMessage = String.format("%s: %s", PARAMS_ERROR_COMMON_MSG, exception.getMessage());
                        }
                    } else {
                        errorMessage = PARAMS_ERROR_COMMON_MSG;
                    }
                    HintManager.getInstance().showErrorHint(editor, errorMessage);
                });
    }

    public String getParametersJson() {
        return editor.getText();
    }

}
