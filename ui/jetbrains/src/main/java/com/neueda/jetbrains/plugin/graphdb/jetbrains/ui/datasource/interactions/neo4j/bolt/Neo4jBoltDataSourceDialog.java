package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.neo4j.bolt;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltConfiguration;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.DataSourceDialog;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.util.Validation.validation;

public class Neo4jBoltDataSourceDialog extends DataSourceDialog {

    public static final int THICKNESS = 10;
    public static final int HEIGHT = 150;
    private final DataSourcesComponent dataSourcesComponent;
    private final DatabaseManagerService databaseManager;
    private DataSourceApi dataSourceToEdit;

    private Data data;

    private JPanel content;
    private JBTextField dataSourceNameField;
    private JBTextField hostField;
    private JBTextField userField;
    private JBPasswordField passwordField;
    private JBTextField portField;
    private JButton testConnectionButton;

    public Neo4jBoltDataSourceDialog(Project project, DataSourcesView dataSourcesView, DataSourceApi dataSourceToEdit) {
        this(project, dataSourcesView);
        this.dataSourceToEdit = dataSourceToEdit;
    }

    public Neo4jBoltDataSourceDialog(Project project, DataSourcesView dataSourcesView) {
        super(project);
        Disposer.register(project, myDisposable);
        init();

        databaseManager = ServiceManager.getService(DatabaseManagerService.class);
        dataSourcesComponent = dataSourcesView.getComponent();

        testConnectionButton.addActionListener(e -> {
            JPanel popupPanel = new JPanel(new BorderLayout());
            popupPanel.setBorder(JBUI.Borders.empty(THICKNESS));

            ValidationInfo validationInfo = doValidate();
            if (validationInfo != null) {
                JLabel connectionFailed = new JLabel("Connection failed: " + validationInfo.message, AllIcons.Process.State.RedExcl, JLabel.LEFT);
                popupPanel.add(connectionFailed, BorderLayout.CENTER);
            } else {
                try {
                    DataSourceApi dataSource = constructDataSource();
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
                    .createComponentPopupBuilder(popupPanel, this.getPreferredFocusedComponent())
                    .setTitle("Test connection")
                    .createPopup()
                    .showInCenterOf(this.getContentPanel());
        });
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        if (StringUtils.isBlank(dataSourceNameField.getText())) {
            return validation("Data source name must not be empty", dataSourceNameField);
        }
        if (StringUtils.isBlank(hostField.getText())) {
            return validation("Host must not be empty", hostField);
        }
        if (!StringUtils.isNumeric(portField.getText())) {
            return validation("Port must be numeric", portField);
        }

        extractData();

        if (dataSourcesComponent.getDataSourceContainer().isDataSourceExists(data.dataSourceName)) {
            if (!(dataSourceToEdit != null && dataSourceToEdit.getName().equals(data.dataSourceName))) {
                return validation(String.format("Data source [%s] already exists", data.dataSourceName), dataSourceNameField);
            }
        }

        return null;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        if (dataSourceToEdit != null) {
            Map<String, String> conf = dataSourceToEdit.getConfiguration();
            String host = conf.get(Neo4jBoltConfiguration.HOST);
            String port = conf.get(Neo4jBoltConfiguration.PORT);
            String user = conf.get(Neo4jBoltConfiguration.USER);
            String password = conf.get(Neo4jBoltConfiguration.PASSWORD);

            dataSourceNameField.setText(dataSourceToEdit.getName());
            hostField.setText(host);
            portField.setText(port);
            userField.setText(user);
            passwordField.setText(password);
        }
        return content;
    }

    @Override
    public DataSourceApi constructDataSource() {
        extractData();

        Map<String, String> configuration = new HashMap<>();
        configuration.put(Neo4jBoltConfiguration.HOST, data.host);
        configuration.put(Neo4jBoltConfiguration.PORT, data.port);
        configuration.put(Neo4jBoltConfiguration.USER, data.user);
        configuration.put(Neo4jBoltConfiguration.PASSWORD, data.password);

        return dataSourcesComponent.getDataSourceContainer().createDataSource(
                dataSourceToEdit,
                DataSourceType.NEO4J_BOLT,
                data.dataSourceName,
                configuration
        );
    }

    private void extractData() {
        data = new Data();
        data.dataSourceName = dataSourceNameField.getText();
        data.host = hostField.getText();
        data.port = portField.getText();
        data.user = userField.getText();
        data.password = String.valueOf(passwordField.getPassword()); // TODO: use password API
    }

    private static final class Data {
        private String dataSourceName;
        private String host;
        private String port;
        private String user;
        private String password;
    }
}
