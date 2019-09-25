package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesView;
import java.awt.*;
import javax.swing.*;
import org.jetbrains.annotations.Nullable;

public abstract class DataSourceDialog extends DialogWrapper {
    public static final int THICKNESS = 10;
    public static final int HEIGHT = 150;

    protected DataSourceDialog(@Nullable Project project, DataSourcesView dataSourcesView) {
        super(project);
        Disposer.register(project, myDisposable);
        init();
    }

    public abstract DataSourceApi constructDataSource();

    public boolean go() {
        init();
        return showAndGet();
    }

    public void validationPopup() {
        JPanel popupPanel = new JPanel(new BorderLayout());
        popupPanel.setBorder(JBUI.Borders.empty(THICKNESS));

        ValidationInfo validationInfo = doValidate();
        if (validationInfo != null) {
            JLabel connectionFailed = new JLabel("Connection failed: " + validationInfo.message, AllIcons.Process.State.RedExcl, JLabel.LEFT);
            popupPanel.add(connectionFailed, BorderLayout.CENTER);
        } else {
            try {
                DataSourceApi dataSource = constructDataSource();
                DatabaseManagerService databaseManager = ServiceManager.getService(DatabaseManagerService.class);
                GraphDatabaseApi db = databaseManager.getDatabaseFor(dataSource);
                GraphQueryResult result = db.execute("RETURN 'ok'");

                Object value = result.getRows().get(0).getValue(result.getColumns().get(0));
                if (value.equals("ok")) {
                    JLabel connectionSuccessful = new JLabel("Connection successful!", AllIcons.Process.State.GreenOK, JLabel.LEFT);
                    popupPanel.add(connectionSuccessful, BorderLayout.CENTER);
                } else {
                    throw new RuntimeException("Unexpected test query output: " + value);
                }
            } catch (Exception exception) {
                JLabel connectionFailed = new JLabel("Connection failed: " + exception.getMessage(), AllIcons.Process.State.RedExcl, JLabel.LEFT);

                JTextArea exceptionCauses = new JTextArea();
                exceptionCauses.setLineWrap(true);

                Throwable cause = exception.getCause();
                while (cause != null) {
                    exceptionCauses.append(cause.getMessage() + "\n");
                    cause = cause.getCause();
                }

                JBScrollPane scrollPane = new JBScrollPane(exceptionCauses);
                scrollPane.setPreferredSize(new Dimension(-1, HEIGHT));
                popupPanel.add(connectionFailed, BorderLayout.CENTER);
                popupPanel.add(scrollPane, BorderLayout.SOUTH);
            }
        }

        JBPopupFactory.getInstance()
            .createComponentPopupBuilder(popupPanel, getPreferredFocusedComponent())
            .setTitle("Test connection")
            .createPopup()
            .showInCenterOf(getContentPanel());
    }
}
