package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph.GraphPanel;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.TablePanel;
import com.neueda.jetbrains.plugin.graphdb.visualization.services.LookAndFeelService;
import org.jetbrains.annotations.NotNull;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class ConsoleToolWindow implements ToolWindowFactory {

    private JPanel consoleToolWindowContent;

    // Graph
    private JPanel graphToolbarPanel;
    private JPanel graphCanvas;
    private JBScrollPane entityDetailsScrollPane;
    private Tree entityDetailsTree;

    // Table
    private JBScrollPane tableScrollPane;
    private JBTable tableExecuteResults;
    private JPanel entityDetailsScrollContent;

    private LookAndFeelService lookAndFeelService;

    private TablePanel tablePanel;
    private GraphPanel graphPanel;

    public ConsoleToolWindow() {
        tablePanel = new TablePanel();
        graphPanel = new GraphPanel();
        lookAndFeelService = ServiceManager.getService(LookAndFeelService.class);
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // Bootstrap tool window
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(consoleToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

        updateLookAndFeel();
        initializeUiComponents(project.getMessageBus());
    }

    private void createUIComponents() {
        graphCanvas = new JPanel(new GridLayout(0, 1));
    }

    private void updateLookAndFeel() {
        tableScrollPane.setBorder(IdeBorderFactory.createEmptyBorder());
        entityDetailsScrollPane.setBorder(IdeBorderFactory.createEmptyBorder());
    }

    private void initializeUiComponents(MessageBus messageBus) {
        graphPanel.initialize(this, messageBus);
        tablePanel.initialize(this, messageBus);
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

    public LookAndFeelService getLookAndFeelService() {
        return lookAndFeelService;
    }

    public JPanel getGraphCanvas() {
        return graphCanvas;
    }

    public Tree getEntityDetailsTree() {
        return entityDetailsTree;
    }

    public JPanel getGraphToolbarPanel() {
        return graphToolbarPanel;
    }

    public JBTable getTableExecuteResults() {
        return tableExecuteResults;
    }
}
