package lv.neueda.jetbrains.plugin.graphdb.bus;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import lv.neueda.jetbrains.plugin.graphdb.ui.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteQueryAction extends AnAction {

    private static final Logger log = LoggerFactory.getLogger(ExecuteQueryAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);

        if (project == null) {
            Notifier.error("Execute query error", "No project present.");
            return;
        }
        if (editor == null) {
            Notifier.error("Execute query error", "No editor present.");
            return;
        }

        MessageBus messageBus = project.getMessageBus();
        String content = editor.getDocument().getCharsSequence().toString();
        Caret caret = editor.getCaretModel().getPrimaryCaret();

        ExecuteQueryEvent executeQueryEvent = messageBus.syncPublisher(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC);

        executeQueryEvent.executeQuery(new ExecuteQueryPayload(content, caret));
    }
}
