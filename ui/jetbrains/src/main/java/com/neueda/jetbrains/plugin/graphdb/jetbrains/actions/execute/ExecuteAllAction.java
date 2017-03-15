package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.psi.PsiFile;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.ConsoleToolWindow;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params.ParametersService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.Notifier;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.file.CypherFileType;

public class ExecuteAllAction extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        PsiFile psiFile = e.getDataContext().getData(PlatformDataKeys.PSI_FILE);
        if (psiFile != null && psiFile.getFileType() instanceof CypherFileType) {
            e.getPresentation().setEnabled(true);
        } else {
            e.getPresentation().setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = getEventProject(e);
        if (project == null) {
            Notifier.error("Query execution error", "No project present.");
            return;
        }
        MessageBus messageBus = project.getMessageBus();
        ParametersService parameterService = ServiceManager.getService(project, ParametersService.class);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);

        StatementCollector statementCollector = new StatementCollector(messageBus, parameterService);

        // This should never happen
        if (psiFile == null) {
            Notifier.error("Internal error", "No PsiFile present.");
        }
        psiFile.acceptChildren(statementCollector);
        if (!statementCollector.hasErrors()) {
            ExecuteQueryPayload executeQueryPayload =
                new ExecuteQueryPayload(statementCollector.getQueries(), statementCollector.getParameters(), psiFile.getName());
            ConsoleToolWindow.ensureOpen(project);

            DataSourcesComponent dataSourcesComponent = project.getComponent(DataSourcesComponent.class);
            ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                "Choose Data Source",
                new ChooseDataSourceActionGroup(messageBus, dataSourcesComponent, executeQueryPayload),
                e.getDataContext(),
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                false
            );
            popup.showInBestPositionFor(e.getDataContext());
        } else {
            Notifier.error("Query execution error", "File contains errors");
        }
    }
}
