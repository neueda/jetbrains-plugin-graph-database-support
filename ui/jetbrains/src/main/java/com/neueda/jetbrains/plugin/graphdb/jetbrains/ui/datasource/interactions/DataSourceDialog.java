package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.ui.popup.IconButton;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.services.ExecutorService;
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

    protected abstract void showLoading();
    protected abstract void hideLoading();

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
            createPopup(popupPanel, getContentPanel());
        } else {
            validateConnection(popupPanel, getContentPanel());
        }
    }

    private void createPopup(JPanel popupPanel, JComponent contentPanel) {
        if (contentPanel.isShowing()) {
            JBPopupFactory.getInstance()
                    .createComponentPopupBuilder(popupPanel, getPreferredFocusedComponent())
                    .setCancelButton(new IconButton("Close", AllIcons.Actions.Close))
                    .setTitle("Test connection")
                    .createPopup()
                    .showInCenterOf(contentPanel);
        }
    }

    private void validateConnection(
            JPanel popupPanel,
            JComponent contentPanel) {
        ExecutorService executorService = ServiceManager.getService(ExecutorService.class);
        showLoading();
        executorService.runInBackground(
                this::executeOkQuery,
                (status) -> connectionSuccessful(popupPanel, contentPanel),
                (exception) -> connectionFailed(exception, popupPanel, contentPanel),
                ModalityState.current()
        );
    }

    private String executeOkQuery() {
        DataSourceApi dataSource = constructDataSource();
        DatabaseManagerService databaseManager = ServiceManager.getService(DatabaseManagerService.class);
        GraphDatabaseApi db = databaseManager.getDatabaseFor(dataSource);
        GraphQueryResult result = db.execute("RETURN 'ok'");

        Object value = result.getRows().get(0).getValue(result.getColumns().get(0));

        if (value.equals("ok")) {
            return "ok";
        } else {
            throw new RuntimeException("Unexpected test query output: " + value);
        }
    }

    private void connectionSuccessful(
            JPanel popupPanel,
            JComponent contentPanel) {
        hideLoading();
        JLabel connectionSuccessful = new JLabel("Connection successful!", AllIcons.Process.State.GreenOK, JLabel.LEFT);
        popupPanel.add(connectionSuccessful, BorderLayout.CENTER);

        createPopup(popupPanel, contentPanel);
    }

    private void connectionFailed(
            Exception exception,
            JPanel popupPanel,
            JComponent contentPanel) {
        hideLoading();

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

        createPopup(popupPanel, contentPanel);
    }
}
