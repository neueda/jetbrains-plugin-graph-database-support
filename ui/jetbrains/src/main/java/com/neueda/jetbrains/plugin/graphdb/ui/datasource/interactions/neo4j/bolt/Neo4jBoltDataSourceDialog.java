package com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions.neo4j.bolt;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.jgoodies.common.base.Strings;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSource;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.component.datasource.type.Neo4jBoltDataSource;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.DataSourcesToolWindow;
import com.neueda.jetbrains.plugin.graphdb.ui.datasource.interactions.DataSourceDialog;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Map;

import static com.neueda.jetbrains.plugin.graphdb.ui.util.Validation.validation;

public class Neo4jBoltDataSourceDialog extends DataSourceDialog {

    private final DataSourcesComponent dataSourcesComponent;

    private Data data;

    private JPanel content;
    private JBTextField dataSourceNameField;
    private JBTextField hostField;
    private JBTextField userField;
    private JBPasswordField passwordField;
    private JBTextField portField;
    private JButton testConnectionButton; // TODO: implement

    public Neo4jBoltDataSourceDialog(@Nullable Project project, DataSourcesToolWindow window) {
        super(project);
        init();

        dataSourcesComponent = window.getComponent();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        if (Strings.isBlank(dataSourceNameField.getText())) {
            return validation("Data source name must not be empty", dataSourceNameField);
        }
        if (Strings.isBlank(hostField.getText())) {
            return validation("Host must not be empty", hostField);
        }
        if (!StringUtils.isNumeric(portField.getText())) {
            return validation("Port must be numeric", portField);
        }

        extractData();

        if (dataSourcesComponent.isDataSourceExists(data.dataSourceName)) {
            return validation(String.format("Data source [%s] already exists", data.dataSourceName), dataSourceNameField);
        }

        return null;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return content;
    }

    @Override
    public DataSource constructDataSource() {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put(Neo4jBoltDataSource.HOST, data.host);
        configuration.put(Neo4jBoltDataSource.PORT, data.port);
        configuration.put(Neo4jBoltDataSource.USER, data.user);
        configuration.put(Neo4jBoltDataSource.PASSWORD, data.password);

        return new DataSource(DataSourceType.NEO4J_BOLT, data.dataSourceName, configuration);
    }

    private void extractData() {
        data = new Data();
        data.dataSourceName = dataSourceNameField.getText();
        data.host = hostField.getText();
        data.port = Integer.getInteger(portField.getText());
        data.user = userField.getText();
        data.password = String.valueOf(passwordField.getPassword()); // TODO: use password API
    }

    public static final class Data {
        public String dataSourceName;
        public String host;
        public Integer port;
        public String user;
        public String password;
    }
}
