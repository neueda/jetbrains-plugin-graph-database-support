package lv.neueda.jetbrains.plugin.graphdb.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import lv.neueda.jetbrains.plugin.graphdb.event.ExecuteQueryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteQueryAction extends AnAction {

    private static final Logger log = LoggerFactory.getLogger(ExecuteQueryAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);

        if (project == null) {
            log.warn("Project is null. Event is not processed.");
            return;
        }
        if (editor == null) {
            log.warn("Editor is null. Event is not processed.");
            return;
        }

        MessageBus messageBus = project.getMessageBus();
        String content = editor.getDocument().getCharsSequence().toString();

        ExecuteQueryEvent executeQueryEvent = messageBus.syncPublisher(ExecuteQueryEvent.EXECUTE_QUERY_TOPIC);
        log.warn("Publish executeQuery query.");
        executeQueryEvent.execute(content);
    }
}
